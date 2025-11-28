package Demo.Service;

import Demo.DAO.RolesDAO;
import Demo.Enity.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RolesServiceImplement implements RolesService {
    @Autowired
    private RolesDAO rolesDAO;

    @Override
    public Page<Roles> findAll(Pageable pageable) {
        return rolesDAO.findAll(pageable);
    }
}
