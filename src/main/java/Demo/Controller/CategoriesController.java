package Demo.Controller;

import Demo.Enity.CartsItems;
import Demo.Enity.Categories;
import Demo.Service.CategoriesService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/findAll")
    public Page<?> findAll(Pageable pageable){
        return categoriesService.findAll(pageable);
    }

    @PostMapping("/search")
    public Page<?> findByName(@RequestBody JsonNode jsonNode, Pageable pageable){
        String name = jsonNode.get("categoriesName").asText();
        return categoriesService.findByName(name,pageable);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody JsonNode jsonNode){
        try {
            Categories categories = objectMapper.treeToValue(jsonNode, Categories.class);
            return categoriesService.create(categories);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }

    }


}
