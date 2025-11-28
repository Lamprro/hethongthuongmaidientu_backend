package Demo.Service;

import Demo.DAO.ProductsReviewsDAO;
import Demo.DAO.ProductsStoresDAO;
import Demo.DAO.UsersDAO;
import Demo.Enity.Notification;
import Demo.Enity.ProductsImages;
import Demo.Enity.ProductsReviews;
import Demo.Enity.ProductsStores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductsReviewsServiceImplement implements ProductsReviewsService {
    @Autowired
    private ProductsReviewsDAO productsReviewsDAO;
    @Autowired
    private ProductsStoresDAO productsStoresDAO;
    @Autowired
    private UsersDAO usersDAO;


    @Override
    public ResponseEntity<?> create(ProductsReviews productsReviews) {
        try{
            productsStoresDAO.findById(productsReviews.getProductsStores().getProductsStoresId())
                    .orElseThrow(() -> new RuntimeException("Product store không tồn tại"));
            usersDAO.findById(productsReviews.getUsers().getUsersId())
                    .orElseThrow(() -> new RuntimeException("User không tồn tại"));
            productsReviewsDAO.create(productsReviews);
            productsReviewsDAO.updateAverageRating(productsReviews.getProductsStores().getProductsStoresId());
            return ResponseEntity.ok("Tạo Product Review thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));
        }
    }

    @Override
    public ResponseEntity<?> update(ProductsReviews productsReviews) {
        try{
            productsReviewsDAO.findById(productsReviews.getProductsReviewsId())
                    .orElseThrow(() -> new RuntimeException("Product review không tồn tại"));
            productsReviewsDAO.update(productsReviews);
            productsReviewsDAO.updateAverageRating(productsReviews.getProductsStores().getProductsStoresId());
            return ResponseEntity.ok("Cập nhật Product Review thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));
        }
    }

    @Override
    public ResponseEntity<?> delete(int productsReviewsId) {
        try{
            ProductsReviews productsReviews=productsReviewsDAO.findById(productsReviewsId)
                    .orElseThrow(() -> new RuntimeException("Product review không tồn tại"));
            ProductsStores productsStores = productsReviews.getProductsStores();
                    productsReviewsDAO.delete(productsReviewsId);
            productsReviewsDAO.updateAverageRating(productsStores.getProductsStoresId());
            return ResponseEntity.ok("Xóa Product Review thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));
        }
    }

    @Override
    public Page<ProductsReviews> findByProductsStoresId(int productsStoresId, Pageable pageable) {
        try {
            productsStoresDAO.findById(productsStoresId)
                    .orElseThrow(() -> new RuntimeException("Product store không tồn tại"));
            return productsReviewsDAO.findByProductsStoresId(productsStoresId, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty(pageable);
        }
    }

    @Override
    public Page<ProductsReviews> findByRatingAndProductsId(double rating, int productsId, Pageable pageable) {
        try {
            productsStoresDAO.findById(productsId)
                    .orElseThrow(() -> new RuntimeException("Product store không tồn tại"));
            return productsReviewsDAO.findByRatingAndProductsStoresId(rating,productsId, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty(pageable);
        }
    }


}
