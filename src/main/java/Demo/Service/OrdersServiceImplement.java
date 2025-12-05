package Demo.Service;

import Demo.DAO.OrdersDAO;
import Demo.Enity.Notification;
import Demo.Enity.Orders;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.criteria.Order;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImplement implements OrdersService {

    @Autowired
    private OrdersDAO ordersDAO;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    @Transactional
    public ResponseEntity<?> create(Orders orders) {
        try{
            ordersDAO.create(orders);
            return ResponseEntity.ok("Lưu Order thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));
        }

    }

    @Override
    @Transactional
    public ResponseEntity<?> update(Orders orders) {
        try{
            ordersDAO.findById(orders.getOrdersId())
                    .orElseThrow(() -> new RuntimeException("Order không tồn tại"));
            ordersDAO.update(orders);
            return ResponseEntity.ok("Cập nhật Order thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(int ordersId) {
        try{
            Orders orders1 = ordersDAO.findById(ordersId)
                    .orElseThrow(() -> new RuntimeException("Order không tồn tại"));
            ordersDAO.delete(ordersId);
            return ResponseEntity.ok("Xóa Order thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));
        }
    }

    @Override
    public Page<Orders> findById(int id) {
        try{
            Orders orders = ordersDAO.findById(id)
                    .orElseThrow(() -> new RuntimeException("Order không tồn tại"));
            return new PageImpl<>(
                    List.of(orders),
                    PageRequest.of(0, 1),
                    1
            );
        }catch (Exception e){
            e.printStackTrace();
            return Page.empty();
        }
    }

    @Override
    public Page<Orders> findByUsersId(int id, Pageable pageable) {
        return ordersDAO.findByUsersId(id, pageable);
    }

    @Override
    public Page<Orders> findByStoresId(int id, Pageable pageable) {
        return ordersDAO.findByStoresId(id,pageable);
    }
}
