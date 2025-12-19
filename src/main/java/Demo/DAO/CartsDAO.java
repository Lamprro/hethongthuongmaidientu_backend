package Demo.DAO;

import Demo.Enity.Carts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CartsDAO {
    public void save(Carts carts);
    public void update(Carts carts);
    public Optional<Carts> findById(int id);
    public Page<Carts> findAll(Pageable pageable);
    public Optional<Carts> findByUsersId(int id);
}
