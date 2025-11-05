package Demo.DAO;

import Demo.Enity.Orders;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public Orders findById(int id) {
        List<Orders> result = entityManager.createQuery("SELECT a FROM Orders a WHERE a.ordersId=:ordersId",Orders.class)
                .setParameter("ordersId",id)
                .getResultList();
        return result.isEmpty()?null:result.get(0);
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
}
