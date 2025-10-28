package Demo.Enity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="reports")
public class Reports {
    @Id
    @Column(name="reports_id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int reportsId;

    @Column(name="comment")
    private String comment;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="users_id")
    private Users users;

    public Reports(int reportsId, String comment, LocalDateTime createdAt, Users users) {
        this.reportsId = reportsId;
        this.comment = comment;
        this.createdAt = createdAt;
        this.users = users;
    }

    public Reports() {
    }

    public int getReportsId() {
        return reportsId;
    }

    public void setReportsId(int reportsId) {
        this.reportsId = reportsId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
