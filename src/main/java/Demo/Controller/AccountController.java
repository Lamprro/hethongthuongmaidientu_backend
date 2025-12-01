package Demo.Controller;

import Demo.Service.AccountSecurityService;
import Demo.Service.AccountsService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountSecurityService accountSecurityService;

    @Autowired
    private AccountsService accountsService;

    @PostMapping(path="/create_user_account")
    public ResponseEntity<?> save (@RequestBody JsonNode jsonNode){
        try{
            return accountsService.create(jsonNode);
        }catch(Exception e ){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping(path="/get_information/{accountsId}")
    public ResponseEntity<?> findByAccountsId(@PathVariable int accountsId){
        try{
            return accountsService.findById(accountsId);
        }catch(Exception e ){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping
}
