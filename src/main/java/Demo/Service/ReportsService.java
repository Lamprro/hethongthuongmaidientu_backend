package Demo.Service;

import Demo.Enity.Reports;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ReportsService {
    public ResponseEntity<?> create(Reports reports);
    public ResponseEntity<?> update(Reports reports);
    public Page<Reports> findByUsersId(int usersId, Pageable pageable);
}
