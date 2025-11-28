package Demo.Service;

import Demo.Enity.Roles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RolesService {
    public Page<Roles> findAll(Pageable pageable);
}
