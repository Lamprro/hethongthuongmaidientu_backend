package Demo.Enity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="stores")
public class Stores {
    @Id
    @Column(name = "stores_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storesId;

    @Column(name = "stores_name")
    private String storesName;

    @Column(name = "stores_address")
    private String storesAddress;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private int status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne(cascade =  {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="users_id")
    private Users users;

    public Stores(int storesId, String storesName, String storesAddress, String email, int status, LocalDateTime createdAt, Users users) {
        this.storesId = storesId;
        this.storesName = storesName;
        this.storesAddress = storesAddress;
        this.email = email;
        this.status = status;
        this.createdAt = createdAt;
        this.users = users;
    }

    public Stores() {
    }

    public int getStoreId() {
        return storesId;
    }

    public void setStoreId(int storeId) {
        this.storesId = storeId;
    }

    public String getStoresName() {
        return storesName;
    }

    public void setStoresName(String storesName) {
        this.storesName = storesName;
    }

    public String getStoresAddress() {
        return storesAddress;
    }

    public void setStoresAddress(String storesAddress) {
        this.storesAddress = storesAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}