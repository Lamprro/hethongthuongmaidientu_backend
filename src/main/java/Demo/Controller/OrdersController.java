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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/stores_id/{id}")
    public Page<Orders> findByStoresId (@PathVariable int id, Pageable pageable) {
        try{
            return ordersService.findByStoresId(id,pageable);
        }catch (Exception e){
            e.printStackTrace();
            return Page.empty();
        }
    }
    @PutMapping("/update/{ordersId}")
    public ResponseEntity<?> update (@PathVariable int ordersId,@RequestParam int status) {
        try{
            ordersService.update(ordersId, status);
            return ResponseEntity.ok("Cập nhật trạng thái thành công");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }


}
