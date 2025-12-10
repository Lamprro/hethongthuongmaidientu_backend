package Demo.Service;

import Demo.DAO.ProductsStoresDAO;
import Demo.DAO.PromotionsDAO;
import Demo.Enity.ProductsStores;
import Demo.Enity.Promotions;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionScheduler {

    @Autowired
    private PromotionsDAO promotionsDAO;

    @Autowired
    private ProductsStoresDAO productsStoresDAO;

    @Scheduled(fixedRate = 60000) // chạy mỗi 60 giây
    @Transactional
    public void checkExpiredPromotions() {

        List<Promotions> promotions = promotionsDAO.findExpiredPromotions();

        for (Promotions promo : promotions) {

            List<ProductsStores> list = productsStoresDAO.findByStoresId(promo.getStores().getStoresId(), Pageable.unpaged()).getContent();

            for (ProductsStores ps : list) {
                ps.setPrice(ps.getOriginalPrice()); // trả về giá gốc
                productsStoresDAO.update(ps);
            }

            promotionsDAO.update(promo);
        }
    }
}
