package Demo.Service;

import Demo.DAO.AccountsDAO;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImplement implements AccountsService{

    @Autowired
    private AccountsDAO accountsDAO;


    @Override
    public ResponseEntity<?> create(JsonNode accountJson) {

    }

    @Override
    public ResponseEntity<?> update(JsonNode accountJson) {
        return null;
    }

    @Override
    public ResponseEntity<?> changPassword(JsonNode accountJson) {
        return null;
    }
}
