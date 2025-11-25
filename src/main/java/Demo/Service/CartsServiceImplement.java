package Demo.Service;

import Demo.DAO.CartsDAO;
import Demo.DAO.UsersDAO;
import Demo.Enity.Carts;
import Demo.Enity.Users;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class CartsServiceImplement implements CartsService{
    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private CartsDAO cartsDAO;

    @Override
    @Transactional
    public void create(int usersId) {
        Users users = usersDAO.findById(usersId)
                .orElseThrow(()-> new RuntimeException("User không tồn tại"));
        Carts carts = new Carts();
        carts.setCreatedAt(LocalDateTime.now());
        carts.setUsers(users);
        cartsDAO.save(carts);
    }

    @Override
    @Transactional
    public void update(int cartsId,LocalDateTime time) {
        Carts carts = cartsDAO.findById(cartsId)
                .orElseThrow(()-> new RuntimeException("Cart không tồn tại"));
        carts.setCreatedAt(time);
        cartsDAO.update(carts);
    }
}
