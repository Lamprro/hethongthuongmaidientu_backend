package Demo.DAO;

import Demo.Enity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrdersDAO {
    public void create(Orders orders);
    public void update(Orders orders);
    public Orders findById(int id);
    public Page<Orders> findAll(Pageable pageable);
}
