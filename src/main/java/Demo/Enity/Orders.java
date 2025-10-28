package Demo.Enity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name="orders")
public class Orders {
    @Id
    @Column(name="orders_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ordersId;

    @Column(name="total_amount")
    private Double totalAmount;

    @Column(name="payment_method")
    private String paymentMethod;

    @Column(name="status")
    private String status;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="shipping_address")
    private String shippingAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="users_id")
    private Users users;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="stores_id")
    private Stores stores;

    public Orders(int ordersId, Double totalAmount, String paymentMethod, String status, LocalDateTime createdAt, String shippingAddress, Stores stores, Users users) {
        this.ordersId = ordersId;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.createdAt = createdAt;
        this.shippingAddress = shippingAddress;
        this.stores = stores;
        this.users = users;
    }

    public Orders() {
    }

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Stores getStores() {
        return stores;
    }

    public void setStores(Stores stores) {
        this.stores = stores;
    }
}
