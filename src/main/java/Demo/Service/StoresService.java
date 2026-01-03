package Demo.Service;

import Demo.Enity.Stores;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface StoresService {
    public ResponseEntity<?> create(Stores stores);
    public ResponseEntity<?> update(Stores stores);
    public Page<Stores> findByStoresName (String name, Pageable pageable);
    public ResponseEntity<?> findByUsersId(int usersId);
    public Page<Stores> findAll(Pageable pageable);

}
