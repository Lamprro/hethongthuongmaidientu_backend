package Demo.Controller;

import Demo.Enity.Reports;
import Demo.Enity.Stores;
import Demo.Service.StoresService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stores")
public class StoresController {
    @Autowired
    private StoresService storesService;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody JsonNode jsonNode){
        try {
            Stores stores = objectMapper.treeToValue(jsonNode, Stores.class);
            return storesService.create(stores);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody JsonNode jsonNode){
        try {
            Stores stores = objectMapper.treeToValue(jsonNode, Stores.class);
            return storesService.update(stores);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }

    @GetMapping("/stores_name/{storesName}")
    public Page<Stores> findByStoresName(@PathVariable String storesName, Pageable pageable){
        try {
            return storesService.findByStoresName(storesName,pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty();
        }
    }
    @GetMapping("/users/{usersId}")
    public ResponseEntity<?> findByUsersId (@PathVariable int usersId){
        try {
            return ResponseEntity.ok().body(storesService.findByUsersId(usersId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }
    @GetMapping("/find_all")
    public Page<Stores> findAll(Pageable pageable){
        try{
            return storesService.findAll(pageable);
        }catch (Exception e){
            e.printStackTrace();
            return Page.empty(pageable);
        }
    }

}
