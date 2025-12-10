package Demo.Controller;

import Demo.Enity.Categories;
import Demo.Enity.Products;
import Demo.Enity.ProductsReviews;
import Demo.Service.ProductsReviewsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products_reviews")
public class ProductsReviewsController {
    @Autowired
    private ProductsReviewsService productsReviewsService;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody JsonNode jsonNode){
        try {
            ProductsReviews productsReviews = objectMapper.treeToValue(jsonNode, ProductsReviews.class);
            return productsReviewsService.create(productsReviews);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody JsonNode jsonNode){
        try {
            ProductsReviews productsReviews = objectMapper.treeToValue(jsonNode, ProductsReviews.class);
            return productsReviewsService.update(productsReviews);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }
    @DeleteMapping("/delete/{productsReviewsId}")
    public ResponseEntity<?> delete(@PathVariable int productsReviewsId){
        try {
            return productsReviewsService.delete(productsReviewsId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }
    @GetMapping("/find_products_stores_id/{productsStoresId}")
    public Page<?> findByProductsStoresId(@PathVariable int productsStoresId, Pageable pageable){
        try {
            return productsReviewsService.findByProductsStoresId(productsStoresId,pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty();
        }
    }
    @GetMapping("/find_rating_products_stores_id/{productsStoresId}/{rating}")
    public Page<?> findByRatingAndProductsStoresId(@PathVariable("productsStoresId") int productsStoresId,@PathVariable("rating") double rating ,Pageable pageable){
        try {
            return productsReviewsService.findByRatingAndProductsId(rating,productsStoresId,pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty();
        }
    }

}
