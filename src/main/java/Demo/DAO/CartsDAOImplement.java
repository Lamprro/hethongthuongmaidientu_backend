package Demo.DAO;

import Demo.Enity.Carts;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class CartsDAOImplement implements CartsDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public void save(Carts carts) {
        entityManager.persist(carts);
    }

    @Override
    public void update(Carts carts) {
        entityManager.merge(carts);
    }

    @Override
    public Optional<Carts> findById(int id) {
        List<Carts> result = entityManager.createQuery("SELECT a FROM Carts a WHERE a.cartsId=:cartsId",Carts.class)
                .setParameter("cartsId",id)
                .getResultList();
        return result.isEmpty()? Optional.empty(): Optional.of(result.get(0));
    }

    @Override
    public Page<Carts> findAll(Pageable pageable) {
        Long total=entityManager.createQuery("SELECT COUNT(a) FROM Carts a", Long.class).getSingleResult();

        List<Carts> result = entityManager.createQuery("SELECT a FROM Carts a ",Carts.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return new PageImpl<>(result,pageable,total);
    }
}
