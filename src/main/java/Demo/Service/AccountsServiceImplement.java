package Demo.Service;

import Demo.DAO.AccountsDAO;
import Demo.DAO.RolesDAO;
import Demo.DAO.UsersDAO;
import Demo.Enity.Accounts;
import Demo.Enity.Notification;
import Demo.Enity.Roles;
import Demo.Enity.Users;
import Demo.JWT.JwtService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AccountsServiceImplement implements AccountsService{

    @Autowired
    private AccountsDAO accountsDAO;

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RolesDAO rolesDAO;

    @Autowired
    private CartsService cartsService;



    @Override
    @Transactional
    public ResponseEntity<?> create(JsonNode accountsJson) {
        try{
            Accounts accounts = objectMapper.treeToValue(accountsJson,Accounts.class);
            Users users = objectMapper.treeToValue(accountsJson, Users.class);
            Roles rolesRequest = objectMapper.treeToValue(accountsJson, Roles.class);
            Roles roles = rolesDAO.findById(rolesRequest.getRolesId());
            if(usersDAO.exsistEmails(users.getUsersEmail())){
                return ResponseEntity.badRequest().body(new Notification("Email đã tồn tại"));
            }
            // Kiểm tra xem user có tồn tại hay chưa
            if(accountsDAO.existsUsername(accounts.getUsername())){
                return ResponseEntity.badRequest().body(new Notification("Username đã tồn tại"));
            }

            users.setRoles(roles);
            usersDAO.create(users);
            accounts.setUsers(users);


            // Mã hóa mật khẩu
            String encodePassword = passwordEncoder.encode(accounts.getPassword());
            accounts.setPassword(encodePassword);

            // Lưu vào database
            accountsDAO.save(accounts);
            if(roles.getRolesId()==1){
                cartsService.create(users.getUsersId());
            }

            return ResponseEntity.ok("Đăng kí thành công");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));
        }

    }


    @Override
    @Transactional
    public ResponseEntity<?> changeUsernamePassword(JsonNode accountJson) {
        try{
            Accounts accountRequest = objectMapper.treeToValue(accountJson, Accounts.class);
            Accounts accounts = accountsDAO.findById(accountRequest.getAccountsId())
                    .orElseThrow(()-> new RuntimeException("Account không tồn tại"));
            accounts.setUsername(accountRequest.getUsername());
            // Kiểm tra xem user có tồn tại hay chưa
            if(accountsDAO.existsUsername(accounts.getUsername())){
                return ResponseEntity.badRequest().body(new Notification("Username đã tồn tại"));
            }
            accounts.setPassword(accountRequest.getPassword());
            accountsDAO.update(accounts);
            return ResponseEntity.ok("Cập nhật thông tin tài khoản thành công");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }

    @Override
    public ResponseEntity<?> findById(int accountsId) {
        try{
            Accounts accounts = accountsDAO.findById(accountsId)
                    .orElseThrow(()-> new RuntimeException("Account không tồn tại"));
            return ResponseEntity.ok(accounts);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }
}
