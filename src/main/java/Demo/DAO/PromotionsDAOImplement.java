package Demo.DAO;

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
}
