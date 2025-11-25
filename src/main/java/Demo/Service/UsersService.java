package Demo.Service;

import Demo.Enity.Users;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface UsersService {
    public ResponseEntity<?> updateProfile (Users usersRequest);
}
