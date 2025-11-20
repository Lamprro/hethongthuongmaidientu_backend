package Demo.DAO;

import Demo.Enity.ProductsStores;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ProductsStoresDAOImplement implements ProductsStoresDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void create(ProductsStores productsStores) {
        entityManager.persist(productsStores);
    }

    @Override
    public void update(ProductsStores productsStores) {
        entityManager.merge(productsStores);
    }

    @Override
    public Optional<ProductsStores> findById(int id) {
        List<ProductsStores> result = entityManager.createQuery("SELECT a FROM ProductsStores a WHERE a.productsStoresId = :id",ProductsStores.class)
                .setParameter("id",id)
                .getResultList();
        return result.isEmpty()?Optional.empty():Optional.of(result.get(0));
    }

    @Override
    public Page<ProductsStores> findByProductsId(int id, Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(a) FROM ProductsStores a WHERE a.products.productsId = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();

        List<ProductsStores> result = entityManager.createQuery("SELECT a FROM ProductsStores a WHERE a.products.productsId = :id", ProductsStores.class)
                .setParameter("id", id)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result, pageable, total);
    }

    @Override
    public Page<ProductsStores> findByStoresId(int id, Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(a) FROM ProductsStores a WHERE a.stores.storesId = :id",Long.class)
                .setParameter("id", id)
                .getSingleResult();

        List<ProductsStores> result = entityManager.createQuery(
                        "SELECT a FROM ProductsStores a WHERE a.stores.storesId = :id ",
                        ProductsStores.class)
                .setParameter("id", id)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result, pageable, total);
    }

    @Override
    public Page<ProductsStores> findAll(Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(a) FROM ProductsStores a", Long.class)
                .getSingleResult();

        List<ProductsStores> result = entityManager.createQuery("SELECT a FROM ProductsStores a ", ProductsStores.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result, pageable, total);
    }
}
