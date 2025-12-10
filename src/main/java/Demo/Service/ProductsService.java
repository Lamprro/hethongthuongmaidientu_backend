package Demo.Service;

import Demo.Enity.Products;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductsService {
    public ResponseEntity<?> create(Products products,List<Integer> categoriesId);
    public ResponseEntity<?> update(Products products);
    public Page<Products> searching(String productsName, List<String> categoriesName, Pageable pageable);
}
