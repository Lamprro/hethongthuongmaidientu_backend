package Demo.DAO;

import Demo.Enity.Categories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoriesDAO {
    public void save(Categories categories);
    public void update(Categories categories);
    public Categories findById(int id);
    public Page<Categories> findByName(String name, Pageable pageable);

}
