package Demo.DAO;

import Demo.Enity.Reports;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReportsDAO {
    public void create(Reports reports);
    public void update(Reports reports);
    public Page<Reports> findById(int id, Pageable pageable);
    public Page<Reports> findByUsersId(int id,Pageable pageable);
    public Page<Reports> findAll(Pageable pageable);
}
