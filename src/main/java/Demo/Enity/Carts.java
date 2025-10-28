package Demo.Enity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="carts")
public class Carts {
    @Id
    @Column(name="carts_id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int carts_id;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="users_id")
    private Users users;

    public Carts(int carts_id, LocalDateTime createdAt, Users users) {
        this.carts_id = carts_id;
        this.createdAt = createdAt;
        this.users = users;
    }

    public Carts() {
    }

    public int getCarts_id() {
        return carts_id;
    }

    public void setCarts_id(int carts_id) {
        this.carts_id = carts_id;
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
