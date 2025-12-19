package Demo.Controller;

import Demo.Enity.ProductsReviews;
import Demo.Enity.ProductsStores;
import Demo.Service.ProductsStoresService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products_stores")
public class ProductsStoresController {
    @Autowired
    private ProductsStoresService productsStoresService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody JsonNode jsonNode){
        try {
            ProductsStores productsStores = objectMapper.treeToValue(jsonNode, ProductsStores.class);
            return productsStoresService.create(productsStores);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody JsonNode jsonNode){
        try {
            ProductsStores productsStores = objectMapper.treeToValue(jsonNode, ProductsStores.class);
            return productsStoresService.update(productsStores);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }

    @GetMapping("/products_id/{productsId}")
    public Page<ProductsStores> findByProductsId(@PathVariable int productsId, Pageable pageable){
        try {
            return productsStoresService.findByProductsId(productsId,pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty();
        }
    }

    @GetMapping("/products_id_and_stores_id/{storesId}/{productsId}")
    public Page<ProductsStores> findByProductsIdAndStoresId (@PathVariable("storesId") int storesId,@PathVariable("productsId") int productsId, Pageable pageable){
        try {
            return productsStoresService.findByProductsIdAndStoresId(productsId,storesId,pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductsStores> findById(@PathVariable int id) {
        return ResponseEntity.ok(productsStoresService.findById(id));
    }

}
