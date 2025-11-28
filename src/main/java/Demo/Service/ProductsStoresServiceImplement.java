package Demo.Service;

import Demo.DAO.ProductsDAO;
import Demo.DAO.ProductsStoresDAO;
import Demo.DAO.StoresDAO;
import Demo.Enity.Categories;
import Demo.Enity.Notification;
import Demo.Enity.ProductsStores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsStoresServiceImplement implements ProductsStoresService {
    @Autowired
    private ProductsStoresDAO productsStoresDAO;
    @Autowired
    private ProductsDAO productsDAO;
    @Autowired
    private StoresDAO storesDAO;
    @Override
    public ResponseEntity<?> create(ProductsStores productsStores) {
        try{
            productsStoresDAO.create(productsStores);
            return ResponseEntity.ok("Lưu Product store thành công");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));
        }
    }

    @Override
    public ResponseEntity<?> update(ProductsStores productsStores) {
        try{
            productsStoresDAO.findById(productsStores.getProductsStoresId())
                    .orElseThrow(() -> new RuntimeException("Product store không tồn tại"));
            productsStoresDAO.update(productsStores);
            return ResponseEntity.ok("Lưu Product store thành công");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));
        }
    }


    @Override
    public Page<ProductsStores> findByProductsId(int productsId, Pageable pageable) {
        try{
            productsDAO.findById(productsId)
                    .orElseThrow(() -> new RuntimeException("Product không tồn tại"));
            return productsStoresDAO.findByProductsId(productsId,pageable);
        }catch (Exception e){
            e.printStackTrace();
            return Page.empty(pageable);
        }
    }

    @Override
    public Page<ProductsStores> findByProductsIdAndStoresId(int productsId, int storesId, Pageable pageable) {
        try{
            productsDAO.findById(productsId)
                    .orElseThrow(() -> new RuntimeException("Product không tồn tại"));
            storesDAO.findById(storesId)
                    .orElseThrow(() -> new RuntimeException("Store không tồn tại"));
            return productsStoresDAO.findByProductsIdAndStoresId(productsId,storesId,pageable);
        }catch (Exception e){
            e.printStackTrace();
            return Page.empty(pageable);
        }
    }
}
