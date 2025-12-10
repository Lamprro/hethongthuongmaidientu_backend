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

    @Column(name="originalPrice")
    private Double originalPrice;

    @Column(name="price")
    private Double price;

    @Column(name="quantity")
    private int quantity;

    @Column(name="average_rating")
    private double averageRating;

    @Column(name="status")
    private int status;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne()
    @JoinColumn(name="products_id")
    private Products products;

    @ManyToOne()
    @JoinColumn(name="stores_id")
    private Stores stores;

    public ProductsStores() {
    }

    public ProductsStores(int productsStoresId, Double originalPrice, Double price, int quantity, double averageRating, int status, LocalDateTime createdAt, LocalDateTime updatedAt, Products products, Stores stores) {
        this.productsStoresId = productsStoresId;
        this.originalPrice = originalPrice;
        this.price = price;
        this.quantity = quantity;
        this.averageRating = averageRating;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.products = products;
        this.stores = stores;
    }

    public int getProductsStoresId() {
        return productsStoresId;
    }

    public void setProductsStoresId(int productsStoresId) {
        this.productsStoresId = productsStoresId;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
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

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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

    public ProductsStores(Double originalPrice, int quantity, Double price, double averageRating, int status, LocalDateTime createdAt, LocalDateTime updatedAt, Products products, Stores stores) {
        this.originalPrice = originalPrice;
        this.quantity = quantity;
        this.price = price;
        this.averageRating = averageRating;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.products = products;
        this.stores = stores;
    }
}
