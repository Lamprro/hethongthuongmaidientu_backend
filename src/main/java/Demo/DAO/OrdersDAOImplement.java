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
import java.time.LocalDateTime;
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
        entityManager.createQuery(
                        "UPDATE Orders a SET a.totalAmount=:totalAmount WHERE a.ordersId = :ordersId")
                .setParameter("totalAmount", orders.getTotalAmount())
                .setParameter("ordersId", orders.getOrdersId())
                .executeUpdate();
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

    @Override
    public double getTotalRevenueStats(int storesId, LocalDateTime startAt, LocalDateTime endedAt) {
        Double result = entityManager.createQuery("SELECT COALESCE (SUM(p.totalAmount),0) FROM Orders p WHERE p.stores.storesId = :storesId AND p.createdAt BETWEEN :startAt AND :endedAt AND p.status=4",Double.class)
                .setParameter("storesId",storesId)
                .setParameter("startAt",startAt)
                .setParameter("endedAt",endedAt)
                .getSingleResult();
        return result;
    }

    @Override
    public long countOrdersByTime(int storesId, LocalDateTime startAt, LocalDateTime endedAt) {
        Long result = entityManager.createQuery("SELECT COUNT(p) FROM Orders p WHERE p.stores.storesId = :storesId AND p.createdAt BETWEEN :startAt AND :endedAt AND p.status=4",Long.class)
                .setParameter("storesId",storesId)
                .setParameter("startAt",startAt)
                .setParameter("endedAt",endedAt)
                .getSingleResult();
        return result;
    }

    @Override
    public List<Orders> findOrdersByTime(int storesId, LocalDateTime startAt, LocalDateTime endedAt) {

        List<Orders> result = entityManager.createQuery("SELECT p FROM Orders p WHERE p.stores.storesId = :storesId AND p.createdAt BETWEEN :startAt AND :endedAt AND p.status=4",Orders.class)
                .setParameter("storesId",storesId)
                .setParameter("startAt",startAt)
                .setParameter("endedAt",endedAt)
                .getResultList();
        return result;
    }

    @Override
    public Page<Orders> findOrdersByStatusAndStoresId(int storesId, int status, Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(p) FROM Orders p WHERE p.stores.storesId = :storesId AND p.status=:status",Long.class)
                .setParameter("storesId",storesId)
                .setParameter("status",status)
                .getSingleResult();

        List<Orders> result = entityManager.createQuery("SELECT p FROM Orders p WHERE p.stores.storesId = :storesId AND p.status=:status",Orders.class)
                .setParameter("storesId",storesId)
                .setParameter("status",status)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return new PageImpl<>(result,pageable,total);
    }

    @Override
    public Long getTotalOrdersByUsersId(int usersId) {
        Long result = entityManager.createQuery("SELECT COUNT(p) FROM Orders p WHERE p.users.usersId = :usersId",Long.class)
                .setParameter("usersId",usersId)
                .getSingleResult();
        return result;
    }

    @Override
    public Double getTotalSpentByOrdersId(int usersId) {
        Double result = entityManager.createQuery("SELECT COALESCE (SUM(p.totalAmount),0) FROM Orders p WHERE p.users.usersId = :usersId",Double.class)
                .setParameter("usersId",usersId)
                .getSingleResult();
        return result;
    }
}
