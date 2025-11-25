package Demo.Service;

import Demo.DAO.ProductsDAO;
import Demo.DAO.ProductsImagesDAO;
import Demo.Enity.Notification;
import Demo.Enity.ProductsImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductsImagesServiceImplement implements ProductsImagesService {

    @Autowired
    private ProductsImagesDAO productsImagesDAO;
    @Autowired
    private ProductsDAO productsDAO;
    @Override
    public ResponseEntity<?> create(ProductsImages productsImages) {
        try{
            productsDAO.findById(productsImages.getProducts().getProductsId())
                    .orElseThrow(() -> new RuntimeException("Product không tồn tại"));
            productsImagesDAO.create(productsImages);
            return ResponseEntity.ok("Lưu Product Image thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));
        }
    }

    @Override
    public ResponseEntity<?> update(ProductsImages productsImages) {
        try{
            productsImagesDAO.findById(productsImages.getImageId())
                    .orElseThrow(() -> new RuntimeException("Product không tồn tại"));
            productsImagesDAO.update(productsImages);
            return ResponseEntity.ok("Cập nhật Product Image thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));
        }
    }

    @Override
    public ResponseEntity<?> delete(int productsImages) {
        try{
            productsImagesDAO.findById(productsImages)
                    .orElseThrow(() -> new RuntimeException("Product không tồn tại"));
            productsImagesDAO.delete(productsImages);
            return ResponseEntity.ok("Xóa Product Image thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));
        }
    }

    @Override
    public Page<ProductsImages> findByProductsId(int productsId, Pageable pageable) {
        try {
            productsDAO.findById(productsId)
                    .orElseThrow(() -> new RuntimeException("Product không tồn tại"));
            return productsImagesDAO.findByProductsId(productsId, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty(pageable);
        }
    }

}
