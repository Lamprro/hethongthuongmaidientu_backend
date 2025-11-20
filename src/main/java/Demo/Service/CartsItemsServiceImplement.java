package Demo.Service;

import Demo.DAO.CartsDAO;
import Demo.DAO.CartsItemsDAO;
import Demo.DAO.ProductsStoresDAO;
import Demo.Enity.Carts;
import Demo.Enity.CartsItems;
import Demo.Enity.Notification;
import Demo.Enity.ProductsStores;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartsItemsServiceImplement implements CartsItemsService {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CartsItemsDAO cartsItemsDAO;
    @Autowired
    private CartsDAO cartsDAO;
    @Autowired
    private ProductsStoresDAO productsStoresDAO;

    @Override
    public ResponseEntity<?> create(JsonNode jsonNode) {
        try {
            // Chuyển JSON thành object
            CartsItems cartsItems = objectMapper.treeToValue(jsonNode, CartsItems.class);

            // Lấy sản phẩm
            int productsStoresId = jsonNode.get("productsStoresId").asInt();
            ProductsStores productsStores = productsStoresDAO.findById(productsStoresId)
                    .orElseThrow(() -> new RuntimeException("ProductsStores không tồn tại"));

            // Lấy giỏ hàng
            int cartsId = jsonNode.get("cartsId").asInt();
            Carts carts = cartsDAO.findById(cartsId)
                    .orElseThrow(() -> new RuntimeException("Carts không tồn tại"));

            cartsItems.setCarts(carts);
            cartsItems.setProductsStores(productsStores);

            // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
            List<CartsItems> existingItems = cartsItemsDAO.findByCartsId(carts.getCarts_id(), Pageable.unpaged()).getContent();
            boolean existed = false;

            for (CartsItems item : existingItems) {
                if (item.getProductsStores().getProductsStoresId() == productsStoresId) {
                    // Cộng dồn số lượng
                    item.setQuantity(item.getQuantity() + cartsItems.getQuantity());
                    cartsItemsDAO.update(item);
                    existed = true;
                    break;
                }
            }

            // Nếu chưa tồn tại, tạo mới
            if (!existed) {
                cartsItemsDAO.create(cartsItems);
            }

            return ResponseEntity.ok("Thêm mới sản phẩm vào giỏ thành công");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));
        }
    }

    @Override
    public ResponseEntity<?> update(JsonNode jsonNode) {
        try {
            int cartsItemsId = jsonNode.get("idCart").asInt();
            int quantity = jsonNode.get("quantity").asInt();

            CartsItems cartsItems = cartsItemsDAO.findById(cartsItemsId)
                    .orElseThrow(() -> new RuntimeException("CartsItems không tồn tại"));

            cartsItems.setQuantity(quantity);
            cartsItemsDAO.update(cartsItems);

            return ResponseEntity.ok("Cập nhật CartsItems thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));
        }
    }

    @Override
    public ResponseEntity<?> delete(int cartsItemsId) {
        try {
            // Kiểm tra tồn tại
            CartsItems cartsItems = cartsItemsDAO.findById(cartsItemsId)
                    .orElseThrow(() -> new RuntimeException("CartsItems không tồn tại"));

            cartsItemsDAO.delete(cartsItemsId);
            return ResponseEntity.ok("Xóa CartsItems thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));
        }
    }
}
