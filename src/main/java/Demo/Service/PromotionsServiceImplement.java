package Demo.Service;

import Demo.DAO.PromotionsDAO;
import Demo.DAO.StoresDAO;
import Demo.Enity.Notification;
import Demo.Enity.Promotions;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionsServiceImplement implements PromotionsService{
    @Autowired
    private PromotionsDAO promotionsDAO;
    @Autowired
    private StoresDAO storesDAO;
    @Override
    @Transactional
    public ResponseEntity<?> create(Promotions promotions) {
        try{
            promotionsDAO.create(promotions);
            return ResponseEntity.ok("Lưu Promotion store thành công");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(Promotions promotions) {
        try{
            storesDAO.findById(promotions.getStores().getStoreId())
                    .orElseThrow(() -> new RuntimeException("Store không tồn tại"));
            promotionsDAO.update(promotions);
            return ResponseEntity.ok("Cập nhật Promotion thành công");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateProductsStoresPrice(int storesId,double discount) {
        try{
            storesDAO.findById(storesId)
                    .orElseThrow(() -> new RuntimeException("Store không tồn tại"));
            promotionsDAO.updateProductsStoresPrice(storesId,discount);
            return ResponseEntity.ok("Cập nhật giá Product store thành công");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));
        }
    }

    @Override
    public Page<Promotions> findByStoresId(int storesId, Pageable pageable) {
        try {
            storesDAO.findById(storesId)
                    .orElseThrow(() -> new RuntimeException("Store không tồn tại"));
            return promotionsDAO.findByStoresId(storesId,pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty(pageable);
        }
    }


}
