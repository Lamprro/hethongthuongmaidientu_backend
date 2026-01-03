package Demo.Service;

import Demo.DAO.DTO.CustomerReportDTO;
import Demo.DAO.OrdersDAO;
import Demo.DAO.RolesDAO;
import Demo.DAO.StoresDAO;
import Demo.DAO.UsersDAO;
import Demo.Enity.Notification;
import Demo.Enity.Roles;
import Demo.Enity.Users;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImplement implements UsersService{

    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RolesDAO rolesDAO;
    @Autowired
    private StoresDAO storesDAO;
    @Autowired
    private OrdersDAO ordersDAO;

    @Override
    @Transactional
    public ResponseEntity<?> updateProfile(Users usersRequest) {
        try {

            Users user = usersDAO.findById(usersRequest.getUsersId())
                    .orElseThrow(() -> new RuntimeException("User không tồn tại"));
            if(usersDAO.exsistEmails(usersRequest.getUsersEmail())&&!usersRequest.getUsersEmail().equals(user.getUsersEmail())){
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

    @Override
    public ResponseEntity<?> findByUsersId(int usersId) {
        try{
            return ResponseEntity.ok().body(usersDAO.findById(usersId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }

    @Override
    public Page<CustomerReportDTO> getCustomerReport(Pageable pageable) {
        Page<Users> customersPage = usersDAO.findCustomer(pageable);
        List<CustomerReportDTO> reports = customersPage.getContent()
                .stream()
                .map(user -> {

                    Long totalOrders = ordersDAO.getTotalOrdersByUsersId(user.getUsersId());
                    Double totalSpent = ordersDAO.getTotalSpentByOrdersId(user.getUsersId());

                    return new CustomerReportDTO(
                            user,
                            totalOrders,
                            totalSpent
                    );
                })
                .toList();
        return new PageImpl<>(
                reports,
                pageable,
                customersPage.getTotalElements()
        );
    }

}
