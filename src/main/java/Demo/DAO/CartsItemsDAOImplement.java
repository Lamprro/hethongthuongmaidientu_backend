package Demo.DAO;

import Demo.Enity.CartsItems;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CartsItemsDAOImplement implements CartsItemsDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void create(CartsItems cartsItems) {
        entityManager.persist(cartsItems);
    }

    @Override
    public void update(CartsItems cartsItems) {
        entityManager.merge(cartsItems);
    }

    @Override
    public void delete(int cartsItemsId) {
        entityManager.createQuery(
                        "DELETE FROM CartsItems a WHERE a.cartsItemsId = :cartsItemsId")
                .setParameter("cartsItemsId", cartsItemsId)
                .executeUpdate();
    }

    @Override
    public Optional<CartsItems> findById(int id) {
        List<CartsItems> result = entityManager.createQuery("SELECT a FROM CartsItems a WHERE a.cartsItemsId=:cartsItemsId ",CartsItems.class)
                .setParameter("cartsItemsId",id)
                .getResultList();
        return result.isEmpty()? Optional.empty():Optional.of(result.get(0));
    }

    @Override
    public Page<CartsItems> findByCartsId(int id, Pageable pageable) {
        if (pageable.isUnpaged()) {
            List<CartsItems> result = entityManager.createQuery(
                            "SELECT a FROM CartsItems a WHERE a.carts.cartsId=:cartsId",
                            CartsItems.class)
                    .setParameter("cartsId", id)
                    .getResultList();

            return new PageImpl<>(result);
        }
        Long total = entityManager.createQuery("SELECT COUNT(a) FROM CartsItems a WHERE a.carts.cartsId=:cartsId", Long.class)
                .setParameter("cartsId",id)
                .getSingleResult();
        List<CartsItems> result = entityManager.createQuery("SELECT a FROM CartsItems a WHERE a.carts.cartsId=:cartsId ",CartsItems.class)
                .setParameter("cartsId",id)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return new PageImpl<>(result,pageable,total);
    }
}
