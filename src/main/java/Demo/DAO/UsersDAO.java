package Demo.DAO;

import Demo.DAO.DTO.CustomerReportDTO;
import Demo.Enity.Users;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UsersDAO {
    public void create (Users users);
    public void update (Users users);
    public Optional<Users> findById(int id);
    public Page<Users> findByUsersName (String name, Pageable pageable);
    public Page<Users> findAll(Pageable pageable);
    public boolean exsistEmails(String email);
    public Users findByEmails(String email);
    public Page<Users> findAllUsersSeller(Pageable pageable);
    public Page<Users> findCustomer (Pageable pageable);
}
