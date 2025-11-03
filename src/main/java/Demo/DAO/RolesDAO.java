package Demo.DAO;

import Demo.Enity.Roles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface RolesDAO {
    public void create (Roles roles);
    public void update (Roles roles);
    public Page<Roles> findAll(Pageable pageable);
    public Page<Roles> findByNames (String names,Pageable pageable);
    public Roles findById(int id);
}
