package Demo.Controller;

import Demo.Enity.Stores;
import Demo.Enity.Users;
import Demo.Service.UsersService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/update_profile")
    public ResponseEntity<?> updateProfile (@RequestBody JsonNode jsonNode){
        try {
            Users users = objectMapper.treeToValue(jsonNode,Users.class);
            return usersService.updateProfile(users);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }
}
