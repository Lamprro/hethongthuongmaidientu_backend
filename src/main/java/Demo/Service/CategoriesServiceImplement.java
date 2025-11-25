package Demo.Service;

import Demo.DAO.CategoriesDAO;
import Demo.Enity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriesServiceImplement implements CategoriesService {
    @Autowired
    private CategoriesDAO categoriesDAO;


    @Override
    public Page<Categories> findAll(Pageable pageable) {
        return categoriesDAO.findAll(pageable);
    }

    @Override
    public Page<Categories> findByName(String name, Pageable pageable) {
        return categoriesDAO.findByName(name, pageable);
    }

    @Override
    public ResponseEntity<?> create(Categories categories) {
        try{

            categoriesDAO.save(categories);
            return ResponseEntity.ok("Lưu Categories thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));

        }
    }

    @Override
    public ResponseEntity<?> update(Categories categories) {
        try{
            categoriesDAO.findById(categories.getCategoriesId())
                    .orElseThrow(()-> new RuntimeException("Categories không tồn tại"));
            categoriesDAO.update(categories);
            return ResponseEntity.ok("Cập nhật Categories thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));

        }
    }

    @Override
    public ResponseEntity<?> findById(int id) {
        try{
            Optional<Categories> categories = categoriesDAO.findById(id);
            if(categories.isEmpty()){
                return ResponseEntity.badRequest().body("Không tồn tại Categorie");
            }
            return ResponseEntity.ok(categories.get()   );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);

        }
    }
}
