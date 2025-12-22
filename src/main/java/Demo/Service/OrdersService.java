package Demo.Service;


import Demo.Enity.Orders;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface OrdersService {
    public ResponseEntity<?> create(Orders orders);
    public ResponseEntity<?> update(int ordersId,int status);
    public ResponseEntity<?> delete(int ordersId);
    public Page<Orders> findById(int id);
    public Page<Orders> findByUsersId(int id, Pageable pageable);
    public Page<Orders> findByStoresId(int id, Pageable pageable);
}
