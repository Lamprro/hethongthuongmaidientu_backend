package Demo.DAO;

import Demo.Enity.CartsItems;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CartsItemsDAO {
    public void create (CartsItems cartsItems);
    public void update (CartsItems cartsItems);
    public void delete (int id);
    public Optional<CartsItems> findById(int id);
    public Page<CartsItems> findByCartsId(int id, Pageable pageable);
    public Page<CartsItems> findByProductsStoresProductsNames(String name,int cartsId,Pageable pageable);
    public Page<CartsItems> findByProductsStoresStoresName(String name,int cartsId,Pageable pageable);
}
