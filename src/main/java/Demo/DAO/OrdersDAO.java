package Demo.DAO;

import Demo.Enity.Orders;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrdersDAO {
    public void create(Orders orders);
    public void update(Orders orders);
    public Optional<Orders> findById(int id);
    public Page<Orders> findAll(Pageable pageable);
    public void delete(int ordersId);
    public Page<Orders> findByUsersId(int usersId,Pageable pageable);
    public Page<Orders> findByStoresId(int storesId,Pageable pageable);
    public double getTotalRevenueStats(int storesId, LocalDateTime startAt, LocalDateTime endedAt);
    public long countOrdersByTime(int storesId, LocalDateTime startAt, LocalDateTime endedAt);
    public List<Orders> findOrdersByTime(int storesId, LocalDateTime startAt, LocalDateTime endedAt);
    public Page<Orders> findOrdersByStatusAndStoresId(int storesId,int status,Pageable pageable);
    public Long getTotalOrdersByUsersId(int usersId );
    public Double getTotalSpentByOrdersId(int usersId);
}
