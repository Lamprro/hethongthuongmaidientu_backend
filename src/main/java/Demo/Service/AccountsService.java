package Demo.Service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface AccountsService {
    public ResponseEntity<?> create(JsonNode accountJson);
    public ResponseEntity<?> update(JsonNode accountJson);
    public ResponseEntity<?> changPassword(JsonNode accountJson);
}
