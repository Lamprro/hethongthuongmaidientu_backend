package Demo.Service;

import Demo.Enity.Categories;
import Demo.Enity.ProductsStores;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductsStoresService {
    public ResponseEntity<?> create(ProductsStores productsStores);
    public ResponseEntity<?> update(ProductsStores productsStores);
    public Page<ProductsStores> findByProductsId(int productsId,Pageable pageable);
    public Page<ProductsStores> findByProductsIdAndStoresId(int productsId, int storesId, Pageable pageable);
}
