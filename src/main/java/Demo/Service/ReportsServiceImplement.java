package Demo.Service;

import Demo.DAO.ReportsDAO;
import Demo.DAO.StoresDAO;
import Demo.DAO.UsersDAO;
import Demo.Enity.Notification;
import Demo.Enity.Reports;
import Demo.Enity.Stores;
import Demo.Enity.Users;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReportsServiceImplement implements ReportsService {
    @Autowired
    private ReportsDAO reportsDAO;
    @Autowired
    private UsersDAO usersDAO;

    @Override
    @Transactional
    public ResponseEntity<?> create(Reports reports) {
        try{
            Users users = usersDAO.findById(reports.getUsers().getUsersId())
                    .orElseThrow(() -> new RuntimeException("User không tồn tại"));
            reports.setUsers(users);
            reportsDAO.create(reports);
            return ResponseEntity.ok("Báo cáo report thành công");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(Reports reports) {
        try{
            usersDAO.findById(reports.getUsers().getUsersId())
                    .orElseThrow(() -> new RuntimeException("User không tồn tại"));
            reportsDAO.update(reports);
            return ResponseEntity.ok("Cập nhật report thành công");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));
        }
    }

    @Override
    public Page<Reports> findByUsersId(int usersId, Pageable pageable) {
        usersDAO.findById(usersId)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));
        return reportsDAO.findByUsersId(usersId,pageable);
    }
}
