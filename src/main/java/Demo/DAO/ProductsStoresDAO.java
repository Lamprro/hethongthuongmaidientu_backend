package Demo.DAO;

import Demo.Enity.ProductsStores;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductsStoresDAO {
    public void create (ProductsStores productsStores);
    public void update (ProductsStores productsStores);
    public Page<ProductsStores> findByProductsId(int id, Pageable pageable);
    public Page<ProductsStores> findByStoresId(int id, Pageable pageable);
    public Page<ProductsStores> findAll(Pageable pageable);
}
