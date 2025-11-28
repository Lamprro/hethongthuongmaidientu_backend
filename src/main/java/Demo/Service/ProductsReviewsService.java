package Demo.Service;

import Demo.Enity.ProductsReviews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProductsReviewsService {
    public ResponseEntity<?> create(ProductsReviews productsReviews);
    public ResponseEntity<?> update(ProductsReviews productsReviews);
    public ResponseEntity<?> delete(int productsReviewsId);
    public Page<ProductsReviews> findByProductsStoresId(int productsId,Pageable pageable);
    public Page<ProductsReviews> findByRatingAndProductsId(double rating, int productsId, Pageable pageable);
}
