package Demo.DAO;

import Demo.Enity.Accounts;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AccountsDAOImplement implements AccountsDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public void save(Accounts accounts) {
        entityManager.persist(accounts);
    }

    @Override
    public void update(Accounts accounts) {
        entityManager.merge(accounts);
    }

    @Override
    public Page<Accounts> findAll(Pageable pageable) {
        // Query tổng số bản ghi
        Long total = entityManager.createQuery("SELECT COUNT(a) FROM Accounts a",Long.class).getSingleResult();

        // Query lay ket qua theo trang
        List<Accounts> accountsList=entityManager.createQuery("SELECT a FROM Accounts a",Accounts.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return new PageImpl<>(accountsList,pageable,total);
    }

    @Override
    public Accounts findByUsername(String username) {
            List<Accounts> result = entityManager.createQuery("SELECT a FROM Accounts a WHERE a.userName= :userName",Accounts.class)
                    .setParameter("userName",username)
                    .getResultList();
            return result.isEmpty()? null:result.get(0);
    }
}
