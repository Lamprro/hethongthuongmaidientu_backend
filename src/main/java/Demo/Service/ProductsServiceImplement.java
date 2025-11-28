package Demo.Service;

import Demo.DAO.ProductsDAO;
import Demo.Enity.Notification;
import Demo.Enity.Products;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServiceImplement implements ProductsService {
    @Autowired
    private ProductsDAO productsDAO;

    @Override
    @Transactional
    public ResponseEntity<?> create(Products products) {
        try{
            productsDAO.create(products);
            return ResponseEntity.ok("Lưu Product thành công");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(Products products) {
        try{
            productsDAO.findById(products.getProductsId())
                            .orElseThrow(() -> new RuntimeException("Product không tồn tại"));
            productsDAO.update(products);
            return ResponseEntity.ok("Lưu Product thành công");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));
        }
    }

    @Override
    public Page<Products> searching(String productsName, List<String> categoriesName, Pageable pageable) {
        try {
            if (productsName == null) productsName = "";
            if (categoriesName == null) categoriesName = List.of();

            return productsDAO.findByProductsNameAndCategoriesName(productsName, categoriesName, pageable);

        } catch (Exception e) {
            e.printStackTrace();
            // Trả về trang rỗng để không crash API
            return Page.empty(pageable);
        }
    }


}
