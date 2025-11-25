package Demo.DAO;

import Demo.Enity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductsDAO {
    public void create(Products products);
    public void update(Products products);
    public Page<Products> findByProductsNameAndCategoriesName (String productsName, List<String> categoriesName, Pageable pageable);
    public Optional<Products> findById(int id);
}
