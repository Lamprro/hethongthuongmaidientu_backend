package Demo.Service;

import Demo.Enity.ProductsImages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProductsImagesService {
    public ResponseEntity<?> create(ProductsImages productsImages);
    public ResponseEntity<?> update(ProductsImages productsImages);
    public ResponseEntity<?> delete(int productsImages);
    public Page<ProductsImages> findByProductsId(int productsId, Pageable pageable);
}
