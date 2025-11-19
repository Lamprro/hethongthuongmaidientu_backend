package Demo.DAO;

import Demo.Enity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UsersDAO {
    public void create (Users users);
    public void update (Users users);
    public Optional<Users> findById(int id);
    public Page<Users> findByUsersName (String name, Pageable pageable);
    public Page<Users> findAll(Pageable pageable);
    public boolean exsistEmails(String email);
    public Users findByEmails(String email);
}
