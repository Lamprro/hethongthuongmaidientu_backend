package Demo.DAO;

import Demo.Enity.ProductsStores;
import Demo.Enity.Promotions;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PromotionsDAOImplement implements PromotionsDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void create(Promotions promotions) {
        entityManager.persist(promotions);
    }

    @Override
    public void update(Promotions promotions) {
        entityManager.merge(promotions);
    }

    @Override
    public Page<Promotions> findByStoresId(int id, Pageable pageable) {
        if (pageable.isUnpaged()) {
            List<Promotions> result = entityManager.createQuery(
                            "SELECT a FROM Promotions a WHERE a.stores.storesId = :id", Promotions.class)
                    .setParameter("id", id)
                    .getResultList();

            return new PageImpl<>(result); // không cần pageable, không cần total
        }

        Long total = entityManager.createQuery("SELECT COUNT(a) FROM Promotions a WHERE a.stores.storesId = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();

        List<Promotions> result = entityManager.createQuery("SELECT a FROM Promotions a WHERE a.stores.storesId = :id ", Promotions.class)
                .setParameter("id", id)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result, pageable, total);
    }

    @Override
    public Promotions findById(int id) {
        List<Promotions> result = entityManager.createQuery("SELECT a FROM Promotions a WHERE a.promotionsId = :id", Promotions.class)
                .setParameter("id", id)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Page<Promotions> findAll(Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(a) FROM Promotions a", Long.class)
                .getSingleResult();

        List<Promotions> result = entityManager.createQuery("SELECT a FROM Promotions a ORDER BY a.createdAt DESC", Promotions.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result, pageable, total);
    }

    @Override
    public void updateProductsStoresPrice(int storesId,double discount) {
        List<ProductsStores> productsStoresList = entityManager.createQuery("SELECT a FROM ProductsStores a WHERE a.stores.storesId=:storesId", ProductsStores.class)
                .setParameter("storesId",storesId)
                .getResultList();

        for(ProductsStores i : productsStoresList){
            i.setPrice(i.getOriginalPrice()*(100-discount)/100);
            entityManager.merge(i);
        }
    }
    @Override
    public List<Promotions> findExpiredPromotions() {
        return entityManager.createQuery(
                "SELECT p FROM Promotions p WHERE p.endedAt < CURRENT_TIMESTAMP ",
                Promotions.class
        ).getResultList();
    }

    @Override
    public List<Promotions> findExistPromotions() {
        return entityManager.createQuery(
                "SELECT p FROM Promotions p WHERE p.endedAt > CURRENT_TIMESTAMP ",
                Promotions.class
        ).getResultList();
    }


}
