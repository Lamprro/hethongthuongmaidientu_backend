package Demo.DAO;

import Demo.Enity.CartsItems;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class  CartsItemsDAOImplement implements CartsItemsDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public void create(CartsItems cartsItems) {
        entityManager.createNativeQuery("INSERT INTO carts_items (carts_id, products_stores_id, quantity)VALUES (:cartsId, :productsId, :quantity)")
                .setParameter("cartsId", cartsItems.getCarts().getCarts_id())
                .setParameter("productsId", cartsItems.getProductsStores().getProductsStoresId())
                .setParameter("quantity", cartsItems.getQuantity())
                .executeUpdate();
    }
    @Override
    public void update(CartsItems cartsItems) {
        entityManager.createQuery("UPDATE CartsItems a SET a.quantity=:quantity WHERE a.cartsItemsId = :cartsItemsId")
                .setParameter("quantity", cartsItems.getQuantity())
                .setParameter("cartsItemsId", cartsItems.getCartsItemsId())
                .executeUpdate();
    }

    @Override
    public void delete(int cartsItemsId) {
        entityManager.createQuery("DELETE FROM CartsItems a WHERE a.cartsItemsId = :cartsItemsId")
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

    @Override
    public Page<CartsItems> findByProductsStoresProductsNames(String name,int cartsId,Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(a) FROM CartsItems a WHERE LOWER(a.productsStores.products.productsName) LIKE LOWER(:productsName) AND a.carts.cartsId=:cartsId ", Long.class)
                .setParameter("productsName","%"+name+"%")
                .setParameter("cartsId",cartsId)
                .getSingleResult();
        List<CartsItems> result = entityManager.createQuery("SELECT a FROM CartsItems a WHERE LOWER(a.productsStores.products.productsName) LIKE LOWER(:productsName) AND a.carts.cartsId=:cartsId",CartsItems.class)
                .setParameter("productsName","%"+name+"%")
                .setParameter("cartsId",cartsId)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return new PageImpl<>(result,pageable,total);
    }

    @Override
    public Page<CartsItems> findByProductsStoresStoresName(String name,int cartsId,Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(a) FROM CartsItems a WHERE LOWER(a.productsStores.stores.storesName) LIKE LOWER(:storesName) AND a.carts.cartsId=:cartsId", Long.class)
                .setParameter("storesName","%"+name+"%")
                .setParameter("cartsId",cartsId)
                .getSingleResult();
        List<CartsItems> result = entityManager.createQuery("SELECT a FROM CartsItems a WHERE LOWER(a.productsStores.stores.storesName) LIKE LOWER(:storesName) AND a.carts.cartsId=:cartsId",CartsItems.class)
                .setParameter("storesName","%"+name+"%")
                .setParameter("cartsId",cartsId)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return new PageImpl<>(result,pageable,total);
    }
}
