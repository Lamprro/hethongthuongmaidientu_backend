package Demo.DAO;

import Demo.Enity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsersDAO {
    public void create (Users users);
    public void update (Users users);
    public Users findById(int id);
    public Page<Users> findByUsersName (String name, Pageable pageable);
    public Page<Users> findAll(Pageable pageable);
}
