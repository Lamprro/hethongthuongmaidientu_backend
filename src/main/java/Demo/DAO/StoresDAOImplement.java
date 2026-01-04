package Demo.DAO;

import Demo.Enity.Stores;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class StoresDAOImplement implements StoresDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void create(Stores stores) {
        entityManager.persist(stores);
    }

    @Override
    public void update(Stores stores) {
        entityManager.merge(stores);
    }


    @Override
    public Optional<Stores> findById(int id) {
        String jpql = "SELECT s FROM Stores s WHERE s.storesId = :id";
        try {
            Stores store = entityManager.createQuery(jpql, Stores.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(store);

        } catch (NoResultException e) {
            return Optional.empty();
        }
    }


    @Override
    public Page<Stores> findByStoresAddress(String address, Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(s) FROM Stores s WHERE LOWER(s.storesAddress) LIKE LOWER(:address)", Long.class)
                .setParameter("address", "%" + address + "%")
                .getSingleResult();

        List<Stores> result = entityManager.createQuery(
                        "SELECT s FROM Stores s WHERE LOWER(s.storesAddress) LIKE LOWER(:address)", Stores.class)
                .setParameter("address", "%" + address + "%")
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result, pageable, total);
    }

    @Override
    public Page<Stores> findByStoresName(String name, Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(s) FROM Stores s WHERE LOWER(s.storesName) LIKE LOWER(:name)", Long.class)
                .setParameter("name", "%" + name + "%")
                .getSingleResult();

        List<Stores> result = entityManager.createQuery("SELECT s FROM Stores s WHERE LOWER(s.storesName) LIKE LOWER(:name)", Stores.class)
                .setParameter("name", "%" + name + "%")
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result, pageable, total);
    }

    @Override
    public Page<Stores> findAll(Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(s) FROM Stores s", Long.class)
                .getSingleResult();

        List<Stores> result = entityManager.createQuery("SELECT s FROM Stores s ", Stores.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result, pageable, total);
    }

    @Override
    public Optional<Stores> findByUsersId(int usersId) {
        String jpql = "SELECT s FROM Stores s WHERE s.users.usersId = :usersId";

        try {
            Stores store = entityManager.createQuery(jpql, Stores.class)
                    .setParameter("usersId", usersId)
                    .getSingleResult();
            return Optional.of(store);

        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
