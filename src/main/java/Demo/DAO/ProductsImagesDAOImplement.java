package Demo.DAO;

import Demo.Enity.ProductsImages;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductsImagesDAOImplement implements ProductsImagesDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public void create(ProductsImages productsImages) {
       entityManager.persist(productsImages);
    }

    @Override
    public void update(ProductsImages productsImages) {
        entityManager.merge(productsImages);
    }

    @Override
    public Page<ProductsImages> findByProductsId(int id, Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(a) FROM ProductsImages a WHERE a.products.productsId = :productsId",Long.class)
                .setParameter("productsId",id)
                .getSingleResult();

        List<ProductsImages> result = entityManager.createQuery("SELECT a FROM ProductsImages a WHERE a.products.productsId = :productsId",ProductsImages.class)
                .setParameter("productsId",id)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return new PageImpl<>(result,pageable,total);
    }

    @Override
    public Page<ProductsImages> findAll(Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(a) FROM ProductsImages a ",Long.class)
                .getSingleResult();

        List<ProductsImages> result = entityManager.createQuery("SELECT a FROM ProductsImages a ",ProductsImages.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result,pageable,total);
    }

    @Override
    public void delete(int id) {
        ProductsImages result = entityManager.find(ProductsImages.class,id);
        if(result != null){
            entityManager.remove(result);
        }
    }

    @Override
    public Optional<ProductsImages> findById(int id) {
        List<ProductsImages> result = entityManager.createQuery("SELECT a FROM ProductsImages a WHERE a.imageId = :imageId ",ProductsImages.class)
                .setParameter("imageId",id)
                .getResultList();

        return result.isEmpty()?Optional.empty():Optional.of(result.get(0));
    }
}
