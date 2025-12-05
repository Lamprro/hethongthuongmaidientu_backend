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
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    @Transactional
    public ResponseEntity<?> create(CartsItems cartsItems, int productsStoresId, int cartsId) {
        try {
            ProductsStores productsStores = productsStoresDAO.findById(productsStoresId)
                    .orElseThrow(() -> new RuntimeException("ProductsStores không tồn tại"));

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
    @Transactional
    public ResponseEntity<?> update(int cartsItemsId, int quantity) {
        try {
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
    @Transactional
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

    @Override
    public Page<CartsItems> findByCartsId(int cartsId, Pageable pageable) {
        return cartsItemsDAO.findByCartsId(cartsId,pageable);
    }

    @Override
    public Page<CartsItems> search(String text, String getCartsId, Pageable pageable) {
        if(getCartsId.isEmpty()||getCartsId.trim().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Thiếu trường cartsId");
        }
        int cartsId;
        try{
            cartsId = Integer.parseInt(getCartsId);
            if(text.isEmpty()||text.trim().isEmpty()){
                return cartsItemsDAO.findByCartsId(cartsId,pageable);
            }
            Page<CartsItems> findByName = cartsItemsDAO.findByProductsStoresProductsNames(text,cartsId,pageable);
            if(findByName.getTotalElements()==0){
                return cartsItemsDAO.findByProductsStoresStoresName(text,cartsId,pageable);
            }
            return findByName;
        }catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Lỗi hệ thống");
        }
    }
}
