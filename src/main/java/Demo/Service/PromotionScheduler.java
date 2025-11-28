package Demo.Service;

import Demo.DAO.ProductsStoresDAO;
import Demo.DAO.PromotionsDAO;
import Demo.Enity.Promotions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PromotionScheduler {

    @Autowired
    private PromotionsDAO promotionsDAO;

    @Autowired
    private ProductsStoresDAO productsStoresDAO;

    @Scheduled(fixedRate = 60000) // chạy mỗi 60 giây
    public void checkExpiredPromotions() {

        List<Promotions> promotions = promotionsDAO.findExpiredPromotions();

        for (Promotions promo : promotions) {

            List<ProductsStores> list = productsStoresDAO.findByStoresId(promo.getStores().getStoresId());

            for (ProductsStores ps : list) {
                ps.setPrice(ps.getOriginalPrice()); // trả về giá gốc
                productsStoresDAO.update(ps);
            }

            promo.setActive(false); // đánh dấu promo đã xử lý
            promotionsDAO.update(promo);
        }
    }
}
