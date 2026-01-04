package Demo.DAO;

import Demo.Enity.Promotions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PromotionsDAO {
    public void create(Promotions promotions);
    public void update (Promotions promotions);
    public Page<Promotions> findByStoresId(int id , Pageable pageable);
    public Promotions findById (int id);
    public Page<Promotions> findAll(Pageable pageable);
    public void updateProductsStoresPrice(int storesId,double discount);
    public List<Promotions> findExpiredPromotions();
    public List<Promotions> findExistPromotions();
}
