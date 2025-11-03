package Demo.Enity;

import jakarta.persistence.*;

@Entity
@Table(name="product_images")
public class ProductsImages {
    @Id
    @Column(name="image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageId;

    @Column(name="image_url")
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="products_id")
    private Products products;

    public ProductsImages(int imageId, String imageUrl, Products products) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
        this.products = products;
    }

    public ProductsImages() {
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }
}
