package Demo.DAO;

import Demo.Enity.ProductsReviews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductsReviewsDAO {
    public void create (ProductsReviews productsReviews);
    public void update (ProductsReviews productsReviews);
    public void delete (ProductsReviews productsReviews);
    public Page<ProductsReviews> findByProductsStoresId(int id, Pageable pageable);
    public Page<ProductsReviews> findByRating(double rating, Pageable pageable);
}
