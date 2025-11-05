package Demo.DAO;

import Demo.Enity.Orders;
import Demo.Enity.OrdersDetails;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrdersDetailsDAOImplement implements OrdersDetailsDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public void create(OrdersDetails ordersDetails) {
        entityManager.persist(ordersDetails);
    }

    @Override
    public void update(OrdersDetails ordersDetails) {
        entityManager.merge(ordersDetails);
    }

    @Override
    public Page<OrdersDetails> findByOrdersId(int id, Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(a) FROM OrdersDetails a WHERE a.orders.ordersId = :ordersId",Long.class)
                .setParameter("ordersId",id)
                .getSingleResult();

        List<OrdersDetails> result = entityManager.createQuery("SELECT a FROM OrdersDetails a WHERE a.orders.ordersId=:ordersId",OrdersDetails.class)
                .setParameter("ordersId",id)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result,pageable,total);
    }
}
