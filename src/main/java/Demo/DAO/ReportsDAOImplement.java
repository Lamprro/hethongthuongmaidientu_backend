package Demo.DAO;

import Demo.Enity.Reports;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ReportsDAOImplement implements ReportsDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void create(Reports reports) {
        entityManager.persist(reports);
    }

    @Override
    public void update(Reports reports) {
        entityManager.merge(reports);
    }

    @Override
    public Page<Reports> findById(int id, Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(a) FROM Reports a WHERE a.reportsId = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();

        List<Reports> result = entityManager.createQuery("SELECT a FROM Reports a WHERE a.reportsId = :id", Reports.class)
                .setParameter("id", id)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result, pageable, total);
    }

    @Override
    public Page<Reports> findByUsersId(int id, Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(a) FROM Reports a WHERE a.users.usersId = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();

        List<Reports> result = entityManager.createQuery("SELECT a FROM Reports a WHERE a.users.usersId = :id ", Reports.class)
                .setParameter("id", id)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result, pageable, total);
    }

    @Override
    public Page<Reports> findAll(Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(a) FROM Reports a",Long.class)
                .getSingleResult();

        List<Reports> result = entityManager.createQuery("SELECT a FROM Reports a ", Reports.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result, pageable, total);
    }
}
