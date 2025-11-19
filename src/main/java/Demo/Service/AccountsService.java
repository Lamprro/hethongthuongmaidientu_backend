package Demo.Service;

import Demo.Enity.Accounts;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface AccountsService {
    public ResponseEntity<?> create(JsonNode accountJson);

    public ResponseEntity<?> changeUsernamePassword(JsonNode accountsJson);
}
