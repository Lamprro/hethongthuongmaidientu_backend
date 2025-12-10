package Demo.DAO;

import Demo.Enity.Orders;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class OrdersDAOImplement implements OrdersDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public void create(Orders orders) {
        entityManager.persist(orders);
    }

    @Override
    public void update(Orders orders) {
        entityManager.merge(orders);
    }

    @Override
    public Optional<Orders> findById(int id) {
        List<Orders> result = entityManager.createQuery("SELECT a FROM Orders a WHERE a.ordersId=:ordersId",Orders.class)
                .setParameter("ordersId",id)
                .getResultList();
        return result.isEmpty()?Optional.empty():Optional.of(result.get(0));
    }

    @Override
    public Page<Orders> findAll(Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(a) FROM Orders a",Long.class)
                .getSingleResult();

        List<Orders> result = entityManager.createQuery("SELECT a FROM Orders a",Orders.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return new PageImpl<>(result,pageable,total);
    }

    @Override
    public void delete(int ordersId) {
        entityManager.createQuery("DELETE FROM Orders a WHERE a.ordersId=:ordersId")
                .setParameter("ordersId",ordersId)
                .executeUpdate();
    }

    @Override
    public Page<Orders> findByUsersId(int usersId,Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(a) FROM Orders a WHERE a.users.usersId= :usersId",Long.class)
                .setParameter("usersId",usersId)
                .getSingleResult();

        List<Orders> result = entityManager.createQuery("SELECT a FROM Orders a WHERE a.users.usersId= :usersId",Orders.class)
                .setParameter("usersId",usersId)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return new PageImpl<>(result,pageable,total);
    }

    @Override
    public Page<Orders> findByStoresId(int storesId,Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(a) FROM Orders a WHERE a.stores.storesId= :storesId",Long.class)
                .setParameter("storesId",storesId)
                .getSingleResult();

        List<Orders> result = entityManager.createQuery("SELECT a FROM Orders a WHERE a.stores.storesId= :storesId",Orders.class)
                .setParameter("storesId",storesId)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return new PageImpl<>(result,pageable,total);
    }
}
