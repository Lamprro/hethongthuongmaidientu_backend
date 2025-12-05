package Demo.DAO;

import Demo.Enity.OrdersDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrdersDetailsDAO {
    public void create(OrdersDetails ordersDetails);
    public void update(OrdersDetails ordersDetails);
    public void deleteByOrdersId(int ordersId);
    public Page<OrdersDetails> findByOrdersId(int id, Pageable pageable);
    public Page<OrdersDetails> findByProductsId(int id,Pageable pageable);
}
