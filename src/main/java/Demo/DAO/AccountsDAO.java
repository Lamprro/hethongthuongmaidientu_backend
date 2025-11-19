package Demo.DAO;

import Demo.Enity.Accounts;
import Demo.Enity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AccountsDAO {
    public void save(Accounts accounts);
    public void update(Accounts accounts);
    public Page<Accounts> findAll(Pageable pageable);
    public Accounts findByUsername(String username);
    public boolean existsUsername(String username);
    public Optional<Accounts> findById(int id);

}
