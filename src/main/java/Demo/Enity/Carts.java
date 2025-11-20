package Demo.Enity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="carts")
public class Carts {
    @Id
    @Column(name="carts_id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int cartsId;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="users_id")
    private Users users;

    public Carts(int cartsId, LocalDateTime createdAt, Users users) {
        this.cartsId = cartsId;
        this.createdAt = createdAt;
        this.users = users;
    }

    public Carts() {
    }

    public int getCarts_id() {
        return cartsId;
    }

    public void setCarts_id(int carts_id) {
        this.cartsId = carts_id;
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
