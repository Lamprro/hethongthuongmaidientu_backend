package Demo.DAO;

import Demo.Enity.Stores;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoresDAO {
    public void create (Stores stores);
    public void update (Stores stores);

    Stores findById(int id);

    public Page<Stores> findByStoresAddress(String address, Pageable pageable);
    public Page<Stores> findByStoresName(String name, Pageable pageable);

    Page<Stores> findAll(Pageable pageable);
}
