package Demo.Service;

import Demo.DAO.StoresDAO;
import Demo.DAO.UsersDAO;
import Demo.Enity.Notification;
import Demo.Enity.Stores;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StoresServiceImplement implements StoresService {
    @Autowired
    private StoresDAO storesDAO;
    @Autowired
    private UsersDAO usersDAO;
    @Override
    public ResponseEntity<?> create(Stores stores) {
        try{
            storesDAO.create(stores);
            return ResponseEntity.ok("Lưu Store thành công");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(Stores stores) {
        try{
            usersDAO.findById(stores.getUsers().getUsersId())
                    .orElseThrow(() -> new RuntimeException("User không tồn tại"));
            storesDAO.update(stores);
            return ResponseEntity.ok("Cập nhật Store thành công");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));
        }
    }

    @Override
    public Page<Stores> findByStoresName(String name, Pageable pageable) {
        return storesDAO.findByStoresName(name,pageable);
    }
}
