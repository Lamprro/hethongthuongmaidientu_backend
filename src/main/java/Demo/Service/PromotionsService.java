package Demo.Service;

import Demo.Enity.Promotions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PromotionsService {
    public ResponseEntity<?> create(Promotions promotions);
    public ResponseEntity<?> update(Promotions promotions);
    public ResponseEntity<?> updateProductsStoresPrice(int storesId,double discount);
    public Page<Promotions> findByStoresId(int storesId, Pageable pageable);
}
