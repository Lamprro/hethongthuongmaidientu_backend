package Demo.DAO;

import Demo.Enity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OrdersDAO {
    public void create(Orders orders);
    public void update(Orders orders);
    public Optional<Orders> findById(int id);
    public Page<Orders> findAll(Pageable pageable);
    public void delete(int ordersId);
    public Page<Orders> findByUsersId(int usersId,Pageable pageable);
    public Page<Orders> findByStoresId(int storesId,Pageable pageable);
}
