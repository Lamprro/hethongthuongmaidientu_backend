package Demo.Enity;

import jakarta.persistence.*;

@Entity
@Table(name="carts_items")
public class CartsItems {
    @Id
    @Column(name="carts_items_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartsItemsId;

    @Column(name="quantity")
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="carts_id")
    private Carts carts;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="products_stores_id")
    private ProductsStores productsStores;

    public CartsItems(int cartsItemsId, int quantity, Carts carts, ProductsStores productsStores) {
        this.cartsItemsId = cartsItemsId;
        this.quantity = quantity;
        this.carts = carts;
        this.productsStores = productsStores;
    }

    public CartsItems() {
    }

    public int getCartsItemsId() {
        return cartsItemsId;
    }

    public void setCartsItemsId(int cartsItemsId) {
        this.cartsItemsId = cartsItemsId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Carts getCarts() {
        return carts;
    }

    public void setCarts(Carts carts) {
        this.carts = carts;
    }

    public ProductsStores getProductsStores() {
        return productsStores;
    }

    public void setProductsStores(ProductsStores productsStores) {
        this.productsStores = productsStores;
    }
}

