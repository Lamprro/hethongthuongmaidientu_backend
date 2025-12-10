package Demo.Controller;

import Demo.DAO.ProductsDAO;
import Demo.Enity.Categories;
import Demo.Enity.OrdersDetails;
import Demo.Enity.Products;
import Demo.Service.ProductsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductsService productsService;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody JsonNode jsonNode) {
        try {
            Products products = objectMapper.treeToValue(jsonNode, Products.class);
            JsonNode categoriesNode = jsonNode.get("categories");
            List<Categories> categories =
                    objectMapper.readValue(categoriesNode.traverse(), new TypeReference<List<Categories>>() {
                    });
            List<Integer> id = new ArrayList<>();
            for (Categories a : categories) {
                id.add(a.getCategoriesId());
            }
            return productsService.create(products,id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody JsonNode jsonNode) {
        try {
            Products products = objectMapper.treeToValue(jsonNode, Products.class);
            return productsService.update(products);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }

    @PostMapping("/searching")
    public Page<Products> searching(@RequestBody JsonNode jsonNode, Pageable pageable) {
        try {
            String productsName = jsonNode.get("productsName").asText();
            JsonNode categoriesNode = jsonNode.get("categories");
            List<Categories> categoriesName =
                    objectMapper.readValue(categoriesNode.traverse(), new TypeReference<List<Categories>>() {
                    });
            List<String> string = new ArrayList<>();
            for (Categories a : categoriesName) {
                string.add(a.getCategoriesName());
            }
            return productsService.searching(productsName, string, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty();
        }
    }

}
