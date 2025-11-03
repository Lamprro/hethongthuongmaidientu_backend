package Demo.DAO;

import Demo.Enity.CartsItems;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartsItemsDAO {
    public void create (CartsItems cartsItems);
    public void update (CartsItems cartsItems);
    public CartsItems findById(int id);
    public Page<CartsItems> findByCartsId(int id, Pageable pageable);
}
