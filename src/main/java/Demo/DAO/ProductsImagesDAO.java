package Demo.DAO;

import Demo.Enity.ProductsImages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductsImagesDAO {
    public void create(ProductsImages productsImages);
    public void update(ProductsImages productsImages);
    public Page<ProductsImages> findByProductsId(int id, Pageable pageable);
    public Page<ProductsImages> findAll(Pageable pageable);
    public void delete(int id);
}
