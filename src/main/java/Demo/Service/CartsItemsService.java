package Demo.Service;

import Demo.Enity.CartsItems;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CartsItemsService {
    public ResponseEntity<?> create (CartsItems cartsItems, int productsStoresId, int cartsId);
    public ResponseEntity<?> update (int cartsItemsId, int quantity);
    public ResponseEntity<?> delete (int cartsItemsId);
    public Page<CartsItems> findByCartsId(int cartsId, Pageable pageable);
    public Page<CartsItems> search(String text, String getCartsId, Pageable pageable);
}
