package Demo.Controller;

import Demo.Enity.Carts;
import Demo.Service.CartsService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/carts")
public class CartsController {
    @Autowired
    private CartsService cartsService;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/update")
    public ResponseEntity<?> update (@RequestBody JsonNode jsonNode){
        try {
            int usersId = objectMapper.readValue(jsonNode.get("usersId").traverse(), Integer.class);
            LocalDateTime localDatetime = objectMapper.readValue(jsonNode.get("localDateTime").traverse(), LocalDateTime.class);
            return cartsService.update(usersId,localDatetime);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }

    @GetMapping("/users_id/{usersId}")
    public ResponseEntity<?> findByUsersId (@PathVariable int usersId){
        try {
            return cartsService.findByUsersId(usersId);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }

}
