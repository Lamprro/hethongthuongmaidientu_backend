package Demo.Service;

import Demo.DAO.RolesDAO;
import Demo.DAO.UsersDAO;
import Demo.Enity.Notification;
import Demo.Enity.Roles;
import Demo.Enity.Users;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImplement implements UsersService{

    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RolesDAO rolesDAO;

    @Override
    @Transactional
    public ResponseEntity<?> updateProfile(Users usersRequest) {
        try {

            Users user = usersDAO.findById(usersRequest.getUsersId())
                    .orElseThrow(() -> new RuntimeException("User không tồn tại"));
            if(usersDAO.exsistEmails(user.getUsersEmail())){
                return ResponseEntity.badRequest().body(new Notification("Email đã tồn tại"));
            }


            user.setUsersName(usersRequest.getUsersName());
            user.setUsersAddress(usersRequest.getUsersAddress());
            user.setUsersEmail(usersRequest.getUsersEmail());
            user.setUsersPhone(usersRequest.getUsersPhone());

            usersDAO.update(user);

            return ResponseEntity.ok("Cập nhật thành công");
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }

    }
}
