package Demo.Controller;

import Demo.Enity.CartsItems;
import Demo.Service.CartsItemsService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/carts_items")
public class CartsItemsController {
    @Autowired
    private CartsItemsService cartsItemsService;
    @Autowired
    private ObjectMapper objectMapper;
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody JsonNode jsonNode){
        try {
            int productsStoresId = jsonNode.get("productsStoresId").asInt();
            int cartsId = jsonNode.get("cartsId").asInt();
            CartsItems cartsItems = objectMapper.treeToValue(jsonNode,CartsItems.class);
            return cartsItemsService.create(cartsItems,productsStoresId,cartsId);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }

    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody JsonNode jsonNode){
        try {
            int cartsItemsId = jsonNode.get("cartsItemsId").asInt();
            int quantity = jsonNode.get("quantity").asInt();
            return cartsItemsService.update(cartsItemsId,quantity);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody JsonNode jsonNode){
        try {
            int cartsItemsId = objectMapper.readValue(jsonNode.get("cartsItemsId").traverse(),Integer.class);
            return cartsItemsService.delete(cartsItemsId);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }
    @PostMapping ("/get/{cartsId}")
    public Page<CartsItems> findByCartsId(@PathVariable int cartsId, Pageable pageable){
        try {
            return cartsItemsService.findByCartsId(cartsId,pageable);
        }catch (Exception e){
            e.printStackTrace();
            return Page.empty();
        }
    }

    @PostMapping("/search/{cartsId}")
    public Page<CartsItems> search(@PathVariable String cartsId,@RequestBody JsonNode jsonNode, Pageable pageable){
        try {
            String text = objectMapper.readValue(jsonNode.get("text").traverse(),String.class);
            return cartsItemsService.search(text,cartsId,pageable);
        }catch (Exception e){
            e.printStackTrace();
            return Page.empty();
        }
    }

}
