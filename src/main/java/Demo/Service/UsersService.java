package Demo.Service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface UsersService {
    public ResponseEntity<?> updateProfile (JsonNode jsonNode);
}
