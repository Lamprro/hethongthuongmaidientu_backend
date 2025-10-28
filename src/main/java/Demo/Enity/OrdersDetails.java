package Demo.Enity;

import jakarta.persistence.*;

@Entity
@Table(name="orders_details")
public class OrdersDetails {
    @Id
    @Column(name="orders_details_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ordersDetailsId;

    @Column(name="quantity")
    private int quantity;

    @Column(name="subs_total")
    private Double subsTotal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="orders_id")
    private Orders orders;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="products_stores_id")
    private ProductsStores productsStores;

    public OrdersDetails(int ordersDetailsId, int quantity, Double subsTotal, Orders orders, ProductsStores productsStores) {
        this.ordersDetailsId = ordersDetailsId;
        this.quantity = quantity;
        this.subsTotal = subsTotal;
        this.orders = orders;
        this.productsStores = productsStores;
    }

    public OrdersDetails() {
    }

    public int getOrdersDetailsId() {
        return ordersDetailsId;
    }

    public void setOrdersDetailsId(int ordersDetailsId) {
        this.ordersDetailsId = ordersDetailsId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getSubsTotal() {
        return subsTotal;
    }

    public void setSubsTotal(Double subsTotal) {
        this.subsTotal = subsTotal;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public ProductsStores getProductsStores() {
        return productsStores;
    }

    public void setProductsStores(ProductsStores productsStores) {
        this.productsStores = productsStores;
    }
}
