package Demo.Enity;

import jakarta.persistence.*;
import org.w3c.dom.Text;

import java.time.LocalDateTime;


@Entity
@Table(name="products_reviews")
public class ProductsReviews {
    @Id
    @Column(name="products_reviews_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productsReviewsId;

    @Column(name="rating")
    private double rating;

    @Column(name="comment")
    private String comment;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="products_stores_id")
    private ProductsStores productsStores;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="users_id")
    private Users users;

    public ProductsReviews(int productsReviewsId, double rating, String comment, LocalDateTime createdAt, ProductsStores productsStores, Users users) {
        this.productsReviewsId = productsReviewsId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
        this.productsStores = productsStores;
        this.users = users;
    }

    public ProductsReviews() {
    }

    public int getProductsReviewsId() {
        return productsReviewsId;
    }

    public void setProductsReviewsId(int productsReviewsId) {
        this.productsReviewsId = productsReviewsId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ProductsStores getProductsStores() {
        return productsStores;
    }

    public void setProductsStores(ProductsStores productsStores) {
        this.productsStores = productsStores;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
