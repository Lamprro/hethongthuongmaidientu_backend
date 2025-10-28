package Demo.Enity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
public class Users {
    @Id
    @Column(name="users_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usersId;

    @Column(name="users_name")
    private String usersName;

    @Column(name="users_email")
    private String usersEmail;

    @Column(name="users_phone")
    private String usersPhone;

    @Column(name="users_address")
    private String usersAddress;

    @Column(name="status")
    private String status;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="roles_id")
    private Roles roles;

    public Users(int usersId, String usersName, String usersEmail, String usersAddress, String usersPhone, LocalDateTime createdAt, String status, Roles roles) {
        this.usersId = usersId;
        this.usersName = usersName;
        this.usersEmail = usersEmail;
        this.usersAddress = usersAddress;
        this.usersPhone = usersPhone;
        this.createdAt = createdAt;
        this.status = status;
        this.roles = roles;
    }

    public Users() {
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    public String getUsersPhone() {
        return usersPhone;
    }

    public void setUsersPhone(String usersPhone) {
        this.usersPhone = usersPhone;
    }

    public String getUsersEmail() {
        return usersEmail;
    }

    public void setUsersEmail(String usersEmail) {
        this.usersEmail = usersEmail;
    }

    public String getUsersAddress() {
        return usersAddress;
    }

    public void setUsersAddress(String usersAddress) {
        this.usersAddress = usersAddress;
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

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
}
