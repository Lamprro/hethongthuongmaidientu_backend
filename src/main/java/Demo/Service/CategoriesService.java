package Demo.Service;

import Demo.Enity.Categories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CategoriesService {
    public Page<Categories> findAll(Pageable pageable);
    public Page<Categories> findByName(String name, Pageable pageable);
    public ResponseEntity<?> create (Categories categories);
    public ResponseEntity<?> update (Categories categories);
    public ResponseEntity<?> findById (int id);
}
