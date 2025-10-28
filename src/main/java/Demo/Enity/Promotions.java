package Demo.Enity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="promotions")
public class Promotions {
    @Id
    @Column(name="promotions_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int promotionsId;

    @Column(name="promotions_name")
    private String promotionsName;

    @Column(name="discount_percent")
    private Double discountPercent;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="ended_at")
    private LocalDateTime endedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="stores_id")
    private Stores stores;

    public Promotions(int promotionsId, String promotionsName, Double discountPercent, LocalDateTime createdAt, LocalDateTime endedAt, Stores stores) {
        this.promotionsId = promotionsId;
        this.promotionsName = promotionsName;
        this.discountPercent = discountPercent;
        this.createdAt = createdAt;
        this.endedAt = endedAt;
        this.stores = stores;
    }

    public Promotions() {
    }

    public int getPromotionsId() {
        return promotionsId;
    }

    public void setPromotionsId(int promotionsId) {
        this.promotionsId = promotionsId;
    }

    public String getPromotionsName() {
        return promotionsName;
    }

    public void setPromotionsName(String promotionsName) {
        this.promotionsName = promotionsName;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }

    public Stores getStores() {
        return stores;
    }

    public void setStores(Stores stores) {
        this.stores = stores;
    }
}
