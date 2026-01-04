package Demo.DAO;

import Demo.Enity.ProductsReviews;
import Demo.Enity.ProductsStores;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductsReviewsDAOImplement implements ProductsReviewsDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void create(ProductsReviews productsReviews) {
        entityManager.persist(productsReviews);
    }

    @Override
    public void update(ProductsReviews productsReviews) {
        entityManager.merge(productsReviews);
    }

    @Override
    public void delete(int productsReviewsId) {
        ProductsReviews pr = entityManager.find(ProductsReviews.class, productsReviewsId);
        if (pr != null) {
            entityManager.remove(pr);
        }
    }

    @Override
    public Page<ProductsReviews> findByProductsStoresId(int id, Pageable pageable) {

        Long total = entityManager.createQuery("SELECT COUNT(a) FROM ProductsReviews a WHERE a.productsStores.productsStoresId = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();
        List<ProductsReviews> result = entityManager.createQuery("SELECT a FROM ProductsReviews a WHERE a.productsStores.productsStoresId = :id", ProductsReviews.class)
                .setParameter("id", id)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result, pageable, total);
    }


    @Override
    public Page<ProductsReviews> findByRatingAndProductsStoresId(double rating, int id, Pageable pageable) {

        Long total = entityManager.createQuery("SELECT COUNT(a) FROM ProductsReviews a " + "WHERE a.rating = :rating AND a.productsStores.productsStoresId = :id", Long.class)
                .setParameter("rating", rating)
                .setParameter("id", id)
                .getSingleResult();

        List<ProductsReviews> result = entityManager.createQuery("SELECT a FROM ProductsReviews a " + "WHERE a.rating = :rating AND a.productsStores.productsStoresId = :id", ProductsReviews.class)
                .setParameter("rating", rating)
                .setParameter("id", id)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result, pageable, total);
    }


    @Override
    public Optional<ProductsReviews> findById(int id) {
        ProductsReviews pr = entityManager.find(ProductsReviews.class, id);
        return Optional.ofNullable(pr);
    }


    @Override
    @Transactional
    public void updateAverageRating(int productsStoresId) {

        ProductsStores ps = entityManager.find(ProductsStores.class, productsStoresId);
        if (ps == null) {
            return;
        }

        Double avg = entityManager.createQuery("SELECT AVG(a.rating) FROM ProductsReviews a WHERE a.productsStores.productsStoresId = :id", Double.class)
                .setParameter("id", productsStoresId)
                .getSingleResult();

        if (avg == null) {
            avg = 0.0;
        }
        double rounded = Math.round(avg * 10.0) / 10.0;

        ps.setAverageRating(rounded);
        entityManager.merge(ps);
    }

    @Override
    public Page<ProductsReviews> findByProductsIdAndUsersId(int productsId, int usersId, Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(a) FROM ProductsReviews a WHERE a.productsStores.productsStoresId = :productsId AND a.users.usersId= :usersId", Long.class)
                .setParameter("productsId", productsId)
                .setParameter("usersId", usersId)
                .getSingleResult();

        List<ProductsReviews> result = entityManager.createQuery("SELECT a FROM ProductsReviews a WHERE a.productsStores.productsStoresId = :productsId AND a.users.usersId= :usersId", ProductsReviews.class)
                .setParameter("productsId", productsId)
                .setParameter("usersId", usersId)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result, pageable, total);
    }
}

