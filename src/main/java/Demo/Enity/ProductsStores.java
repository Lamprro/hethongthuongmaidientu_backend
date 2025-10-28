package Demo.Enity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="products_stores")
public class ProductsStores {
    @Id
    @Column(name="products_stores_id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int productsStoresId;

    @Column(name="price")
    private Double price;

    @Column(name="quantity")
    private int quantity;

    @Column(name="status")
    private String status;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="products_id")
    private Products products;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="stores_id")
    private Stores stores;

    public ProductsStores(int productsStoresId, Double price, int quantity, String status, LocalDateTime createdAt, LocalDateTime updatedAt, Products products, Stores stores) {
        this.productsStoresId = productsStoresId;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.products = products;
        this.stores = stores;
    }

    public ProductsStores() {
    }

    public int getProductsStoresId() {
        return productsStoresId;
    }

    public void setProductsStoresId(int productsStoresId) {
        this.productsStoresId = productsStoresId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Stores getStores() {
        return stores;
    }

    public void setStores(Stores stores) {
        this.stores = stores;
    }
}
