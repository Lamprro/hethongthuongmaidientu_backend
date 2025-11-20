package Demo.Service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface CartsItemsService {
    public ResponseEntity<?> create (JsonNode jsonNode);
    public ResponseEntity<?> update (JsonNode jsonNode);
    public ResponseEntity<?> delete (int cartsItemsId);
}
