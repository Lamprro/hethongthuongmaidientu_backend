package Demo.Service;

import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface CartsService {
    public ResponseEntity<?> create(int usersId);
    public ResponseEntity<?> update(int cartsId,LocalDateTime time);
}
