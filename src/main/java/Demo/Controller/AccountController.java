package Demo.Controller;

import Demo.JWT.JwtService;
import Demo.Security.JwtResponse;
import Demo.Security.LoginRequest;
import Demo.Service.AccountSecurityService;
import Demo.Service.AccountsService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountSecurityService accountSecurityService;

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/create_users_accounts")
    public ResponseEntity<?> save (@RequestBody JsonNode jsonNode){
        try{
            return accountsService.create(jsonNode);
        }catch(Exception e ){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/get_information/{accountsId}")
    public ResponseEntity<?> findByAccountsId(@PathVariable int accountsId){
        try{
            return accountsService.findById(accountsId);
        }catch(Exception e ){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/change_password")
    public ResponseEntity<?> changeUsernamePassword(@RequestBody JsonNode jsonNode){
        return accountsService.changeUsernamePassword(jsonNode);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate (@RequestBody LoginRequest loginRequest){
        try{
            Authentication authentication = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            if (authentication.isAuthenticated()) {
                final String jwtToken = jwtService.genarateToken(loginRequest.getUsername());
                return ResponseEntity.ok(new JwtResponse(jwtToken));
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Tên đăng nhập hoặc mật khẩu không đúng!");
        }
        return ResponseEntity.badRequest().body("Lỗi đăng nhập");
    }
}
