package Demo.Service;
import Demo.Enity.Accounts;
import org.springframework.security.core.userdetails.UserDetailsService;
public interface AccountSecurityService extends UserDetailsService{
    public Accounts findByUsername (String username);
}
