package Demo.Service;

import Demo.DAO.*;
import Demo.Enity.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrdersDetailsServiceImplement implements OrdersDetailsService {
    @Autowired
    private OrdersDAO ordersDAO;
    @Autowired
    private ProductsStoresDAO productsStoresDAO;
    @Autowired
    private OrdersDetailsDAO ordersDetailsDAO;
    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private StoresDAO storesDAO;
    @Override
    @Transactional
    public ResponseEntity<?> create(List<OrdersDetails> ordersDetails, int userId) {
        try{
            Users users = usersDAO.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User không tồn tại"));
            Map<Integer,List<OrdersDetails>> groupByStore =
                    ordersDetails.stream()
                            .collect(Collectors.groupingBy(
                                    p -> p.getProductsStores().getStores().getStoreId()
                            ));
            List<Orders> result = new ArrayList<>();
            for(Integer storedId:groupByStore.keySet()){
                Stores stores = storesDAO.findById(storedId)
                        .orElseThrow(() -> new RuntimeException("Store không tồn tại"));
                Orders orders = new Orders();
                orders.setUsers(users);
                orders.setStatus(0);
                orders.setCreatedAt(LocalDateTime.now());
                orders.setPaymentMethod("MONEY");
                orders.setStores(stores);
                orders.setShippingAddress(users.getUsersAddress());
                ordersDAO.create(orders);
                double total =0;
                for(OrdersDetails o: groupByStore.get(storedId)){
                    o.setOrders(orders);
                    total+=o.getSubsTotal();
                    ProductsStores productsStores = productsStoresDAO.findById(o.getProductsStores().getProductsStoresId())
                            .orElseThrow(() -> new RuntimeException("Product store không tồn tại"));
                    if((productsStores.getQuantity()-o.getQuantity())<=0){
                        return ResponseEntity.ok("Đã hết hàng của Product store này");
                    }
                    else{
                        productsStores.setQuantity(productsStores.getQuantity()-o.getQuantity());
                    ordersDetailsDAO.create(o);
                    }
                }
                // các thuộc tính default
                orders.setTotalAmount(total);
                ordersDAO.update(orders);
            }
            return ResponseEntity.ok("Lưu Order và OrdersDetails thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Notification("Lỗi hệ thống"));

        }

    }

    @Override
    public Page<OrdersDetails> findByOrdersId(int ordersId, Pageable pageable) {
        return ordersDetailsDAO.findByOrdersId(ordersId,pageable);
    }

    @Override
    public Page<OrdersDetails> findByProductsId(int productsId, Pageable pageable) {
        return ordersDetailsDAO.findByProductsId(productsId,pageable);
    }
}
