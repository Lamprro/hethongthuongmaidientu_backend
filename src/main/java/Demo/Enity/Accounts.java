package Demo.Enity;

import jakarta.persistence.*;

@Entity
@Table(name="accounts")
public class Accounts {
    @Id
    @Column(name="accounts_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountsId;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="users_id")
    private Users users;

    public Accounts(int accountsId, String username, Users users, String password) {
        this.accountsId = accountsId;
        this.username = username;
        this.users = users;
        this.password = password;
    }

    public Accounts() {
    }

    public int getAccountsId() {
        return accountsId;
    }

    public void setAccountsId(int accountsId) {
        this.accountsId = accountsId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
