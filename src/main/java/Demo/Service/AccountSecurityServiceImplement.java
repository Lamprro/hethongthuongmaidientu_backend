package Demo.Service;

import Demo.DAO.AccountsDAO;
import Demo.DAO.RolesDAO;
import Demo.DAO.UsersDAO;
import Demo.Enity.Accounts;
import Demo.Enity.Roles;
import Demo.Enity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.PasswordAuthentication;
import java.util.List;

@Service
public class AccountSecurityServiceImplement implements AccountSecurityService{

    @Autowired
    private AccountsDAO accountsDAO;
    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private RolesDAO rolesDAO;

    @Autowired
    @Lazy
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public Accounts findByUsername(String username) {
        return accountsDAO.findByUsername(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Accounts accounts = accountsDAO.findByUsername(username);

        if(accounts == null){
            throw new UsernameNotFoundException("Tên đăng nhập không tồn tại");
        }
        Users users= accounts.getUsers();
        Roles roles= users.getRoles();
        SimpleGrantedAuthority authority=new SimpleGrantedAuthority(roles.getRoleName());
        org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(accounts.getUsername(), accounts.getPassword(), List.of(authority));
        return userDetail;
    }
}
