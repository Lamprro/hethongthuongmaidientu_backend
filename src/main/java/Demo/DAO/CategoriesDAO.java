package Demo.DAO;

import Demo.Enity.Categories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoriesDAO {
    public void save(Categories categories);
    public void update(Categories categories);
    public Optional<Categories> findById(int id);
    public Page<Categories> findByName(String name, Pageable pageable);
    public Page<Categories> findAll(Pageable pageable);
}
