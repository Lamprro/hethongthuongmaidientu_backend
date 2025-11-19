package Demo.Service;

import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface CartsService {
    public void create(int usersId);
    public void update(int cartsId,LocalDateTime time);
}
