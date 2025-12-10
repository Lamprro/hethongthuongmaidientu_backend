package Demo.Enity;

import jakarta.persistence.*;

@Entity
@Table(name="products_images")
public class ProductsImages {
    @Id
    @Column(name="image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageId;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name="public_id", nullable = false)
    private String publicId;

    @ManyToOne()
    @JoinColumn(name="products_id")
    private Products products;

    public ProductsImages() {
    }

    public ProductsImages(int imageId, String publicId, String imageUrl, Products products) {
        this.imageId = imageId;
        this.publicId = publicId;
        this.imageUrl = imageUrl;
        this.products = products;
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

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }
}
