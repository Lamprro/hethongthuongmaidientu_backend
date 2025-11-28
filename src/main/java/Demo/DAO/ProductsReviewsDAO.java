package Demo.DAO;

import Demo.Enity.ProductsReviews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ProductsReviewsDAO {
    public void create (ProductsReviews productsReviews);
    public void update (ProductsReviews productsReviews);
    public void delete (int productsReviews);
    public Page<ProductsReviews> findByProductsStoresId(int id, Pageable pageable);
    public Page<ProductsReviews> findByRatingAndProductsStoresId(double rating,int id, Pageable pageable);
    public Optional<ProductsReviews> findById(int productsReviewsId);
    public void updateAverageRating(int productsStoresId);
}
