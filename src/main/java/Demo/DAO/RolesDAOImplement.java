package Demo.DAO;

import Demo.Enity.Roles;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RolesDAOImplement implements RolesDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void create(Roles roles) {
        entityManager.persist(roles);
    }

    @Override
    public void update(Roles roles) {
        entityManager.merge(roles);
    }

    @Override
    public Roles findById(int id) {
        return entityManager.find(Roles.class, id);
    }

    @Override
    public Page<Roles> findByNames(String roleName, Pageable pageable) {
        Long total = entityManager.createQuery(
                        "SELECT COUNT(r) FROM Roles r WHERE LOWER(r.roleName) LIKE LOWER(:roleName)",
                        Long.class)
                .setParameter("roleName", "%" + roleName + "%")
                .getSingleResult();

        List<Roles> result = entityManager.createQuery(
                        "SELECT r FROM Roles r WHERE LOWER(r.roleName) LIKE LOWER(:roleName)",
                        Roles.class)
                .setParameter("roleName", "%" + roleName + "%")
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result, pageable, total);
    }

    @Override
    public Page<Roles> findAll(Pageable pageable) {
        Long total = entityManager.createQuery(
                        "SELECT COUNT(r) FROM Roles r",
                        Long.class)
                .getSingleResult();

        List<Roles> result = entityManager.createQuery(
                        "SELECT r FROM Roles r ORDER BY r.roleName ASC",
                        Roles.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result, pageable, total);
    }
}
