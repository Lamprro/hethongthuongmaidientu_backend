package Demo.Enity;

import jakarta.persistence.*;

@Entity
@Table(name="roles")
public class Roles {
    @Id
    @Column(name="roles_id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int rolesId;

    @Column(name="role_name")
    private String roleName;

    public Roles(int rolesId, String roleName) {
        this.rolesId = rolesId;
        this.roleName = roleName;
    }

    public Roles() {
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getRolesId() {
        return rolesId;
    }

    public void setRolesId(int rolesId) {
        this.rolesId = rolesId;
    }
}
