package Demo.Controller;

import Demo.Enity.Carts;
import Demo.Service.CartsService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/accounts")
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

}
