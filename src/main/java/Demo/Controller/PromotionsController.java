package Demo.Controller;

import Demo.Enity.ProductsStores;
import Demo.Enity.Promotions;
import Demo.Service.PromotionsService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promotions")
public class PromotionsController {
    @Autowired
    private PromotionsService promotionsService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/create")
    public ResponseEntity<?> create (@RequestBody JsonNode jsonNode){
        try {
            Promotions promotions = objectMapper.treeToValue(jsonNode, Promotions.class);
            int storesId = promotions.getStores().getStoresId();
            double discount = promotions.getDiscountPercent();
            promotionsService.create(promotions);
            return promotionsService.updateProductsStoresPrice(storesId,discount);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update (@RequestBody JsonNode jsonNode){
        try {
            Promotions promotions = objectMapper.treeToValue(jsonNode, Promotions.class);
            int storesId = promotions.getStores().getStoresId();
            double discount = promotions.getDiscountPercent();
            promotionsService.update(promotions);
            return promotionsService.updateProductsStoresPrice(storesId,discount);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }

    @GetMapping("/stores_id/{storesId}")
    public Page<Promotions> findByStoresId (@PathVariable int storesId, Pageable pageable){
        try {
            return promotionsService.findByStoresId(storesId, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty();
        }
    }
}
