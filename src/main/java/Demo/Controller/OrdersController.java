package Demo.Controller;

import Demo.Enity.Orders;
import Demo.Service.OrdersDetailsService;
import Demo.Service.OrdersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private OrdersDetailsService ordersDetailsService;

    @GetMapping("/{id}")
    public Page<Orders> findById(@PathVariable int id){
        try{
            return ordersService.findById(id);
        }catch (Exception e){
            e.printStackTrace();
            return Page.empty();
        }
    }
    @PostMapping("/users_id/{id}")
    public Page<Orders> findByUsersId (@PathVariable int id, Pageable pageable) {
        try{
            return ordersService.findByUsersId(id,pageable);
        }catch (Exception e){
            e.printStackTrace();
            return Page.empty();
        }
    }
    @GetMapping("/stores_id/{id}")
    public Page<Orders> findByStoresId (@PathVariable int id, Pageable pageable) {
        try{
            return ordersService.findByStoresId(id,pageable);
        }catch (Exception e){
            e.printStackTrace();
            return Page.empty();
        }
    }
    @PutMapping("/update/{ordersId}")
    public ResponseEntity<?> updateStatus (@PathVariable int ordersId,@RequestParam int status) {
        try{
            ordersService.updateStatus(ordersId, status);
            return ResponseEntity.ok("Cập nhật trạng thái thành công");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }

    @PutMapping("/update_address/{ordersId}")
    public ResponseEntity<?> updateAddress (@PathVariable int ordersId,@RequestBody String shippingAddress) {
        try{
            ordersService.updateAddress(ordersId, shippingAddress);
            return ResponseEntity.ok("Cập nhật địa chỉ thành công");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }
    @GetMapping("/stats/{storesId}")
    public ResponseEntity<?> getRevenueStats(@PathVariable int storesId, @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start, @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end){
        LocalDateTime startAt = start.atStartOfDay();
        LocalDateTime endedAt = end.atTime(23, 59, 59);
        return ordersService.getTotalRevenueStats(storesId, startAt, endedAt);
    }

    @GetMapping("/stores_id/{storeId}/status/{status}")
    public ResponseEntity<Page<Orders>> getOrdersByStoreIdAndStatus(@PathVariable("storeId") int storeId, @PathVariable("status") int status,Pageable pageable) {
        try {
            Page<Orders> result = ordersService.findByOrdersIdAndStatus(storeId, status, pageable);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


}
