package Demo.Controller;

import Demo.Enity.Categories;
import Demo.Enity.OrdersDetails;
import Demo.Service.OrdersDetailsService;
import Demo.Service.OrdersService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders_details")
public class OrdersDetailsController {
    @Autowired
    private OrdersDetailsService ordersDetailsService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody JsonNode jsonNode){
        try {
            String shippingAddress = jsonNode.get("shippingAddress").asText();
            int usersId = jsonNode.get("usersId").asInt();
            JsonNode listNode = jsonNode.get("ordersDetails");
            List<OrdersDetails> ordersDetailsList =
                    objectMapper.readValue(listNode.traverse(), new TypeReference<List<OrdersDetails>>() {});
            return ordersDetailsService.create(ordersDetailsList,usersId,shippingAddress);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }

    }

    @GetMapping("/orders_id/{ordersId}")
    public Page<OrdersDetails> findByOrdersId(@PathVariable int ordersId, Pageable pageable){
        try {
            return ordersDetailsService.findByOrdersId(ordersId,pageable);
        }catch (Exception e){
            e.printStackTrace();
            return Page.empty();
        }
    }

    @GetMapping("/products_id/{productsId}")
    public Page<OrdersDetails> findByProductsId(@PathVariable int productsId,Pageable pageable){
        try {
            return ordersDetailsService.findByProductsId(productsId,pageable);
        }catch (Exception e){
            e.printStackTrace();
            return Page.empty();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable int id){
        try{
            return ordersDetailsService.delete(id);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }

}
