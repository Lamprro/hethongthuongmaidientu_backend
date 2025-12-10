package Demo.Controller;

import Demo.Enity.Promotions;
import Demo.Enity.Reports;
import Demo.Service.ReportsService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReportsController {
    @Autowired
    private ReportsService reportsService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/create")
    public ResponseEntity<?> create (@RequestBody JsonNode jsonNode){
        try {
            Reports reports = objectMapper.treeToValue(jsonNode, Reports.class);
            return reportsService.create(reports);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update (@RequestBody JsonNode jsonNode){
        try {
            Reports reports = objectMapper.treeToValue(jsonNode, Reports.class);
            return reportsService.update(reports);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }

    @GetMapping("/users_id/{usersId}")
    public Page<Reports> findByUsersId(@PathVariable int usersId, Pageable pageable){
        try {
            return reportsService.findByUsersId(usersId,pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty();
        }
    }
}
