package Demo.Service;

import Demo.DAO.DTO.CustomerReportDTO;
import Demo.Enity.Users;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UsersService {
    public ResponseEntity<?> updateProfile (Users usersRequest);
    public ResponseEntity<?> findByUsersId (int usersId);
    public Page<CustomerReportDTO> getCustomerReport(Pageable pageable);
}
