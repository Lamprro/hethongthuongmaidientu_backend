package Demo.DAO;

import Demo.Enity.Categories;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriesDAOImplement implements CategoriesDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public void save(Categories categories) {
        entityManager.persist(categories);
    }

    @Override
    public void update(Categories categories) {
        entityManager.merge(categories);
    }

    @Override
    public Optional<Categories> findById(int id) {
        List<Categories> result = entityManager.createQuery("SELECT a FROM Categories a WHERE a.categoriesId=:categoriesId",Categories.class)
                .setParameter("categoriesId",id)
                .getResultList();
        return result.isEmpty()? Optional.empty():Optional.of(result.get(0));
    }

    @Override
    public Page<Categories> findByName(String name, Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(a) FROM Categories a WHERE LOWER(a.categoriesName) LIKE LOWER(:categoriesName) ",Long.class)
                .setParameter("categoriesName","%"+name+"%")
                .getSingleResult();

        List<Categories> result = entityManager.createQuery("SELECT a FROM Categories a WHERE LOWER(a.categoriesName) LIKE LOWER(:categoriesName)",Categories.class)
                .setParameter("categoriesName","%"+name+"%")
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return new PageImpl<>(result,pageable,total);
    }

    @Override
    public Page<Categories> findAll(Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(a) FROM Categories a  ",Long.class)
                .getSingleResult();

        List<Categories> result = entityManager.createQuery("SELECT a FROM Categories a ",Categories.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return new PageImpl<>(result,pageable,total);
    }
}
