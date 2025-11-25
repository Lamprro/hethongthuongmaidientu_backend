package Demo.Service;

import Demo.Enity.Orders;
import Demo.Enity.OrdersDetails;
import org.hibernate.query.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrdersDetailsService {
    public ResponseEntity<?> create(List<OrdersDetails> ordersDetails, int userId);
    public Page<OrdersDetails> findByOrdersId(int ordersId, Pageable pageable);
    public Page<OrdersDetails> findByProductsId(int productsId,Pageable pageable);
}
