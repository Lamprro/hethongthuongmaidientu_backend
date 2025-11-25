package Demo.DAO;

import Demo.Enity.ProductsReviews;
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
        entityManager.merge(productsReviews); // thiếu dấu chấm phẩy trong bản cũ
    }

    @Override
    public void delete(int productsReviews) {
        ProductsReviews result = entityManager.find(ProductsReviews.class, productsReviews);
        if (result != null) {
            entityManager.remove(result);
        }
    }

    @Override
    public Page<ProductsReviews> findByProductsStoresId(int id, Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(a) FROM ProductsReviews a WHERE a.productsStores.productsStoresId = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();

        List<ProductsReviews> result = entityManager.createQuery(
                        "SELECT a FROM ProductsReviews a WHERE a.productsStores.productsStoresId = :id",ProductsReviews.class)
                .setParameter("id", id)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result, pageable, total);
    }

    @Override
    public Page<ProductsReviews> findByRatingAndProductsStoresId(double rating,int id, Pageable pageable) {
        Long total = entityManager.createQuery(
                        "SELECT COUNT(a) FROM ProductsReviews a WHERE a.rating = :rating AND a.productsStores.productsStoresId = :productsStoresId", Long.class)
                .setParameter("rating", rating)
                .setParameter("productsStoresId",id)
                .getSingleResult();

        List<ProductsReviews> result = entityManager.createQuery(
                        "SELECT a FROM ProductsReviews a WHERE a.rating = :rating AND a.productsStores.productsStoresId = :productsStoresId", ProductsReviews.class)
                .setParameter("rating", rating)
                .setParameter("productsStoresId",id)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result, pageable, total);
    }

    @Override
    public Optional<ProductsReviews> findById(int productsReviewsId) {
        List<ProductsReviews> result = entityManager.createQuery("SELECT a FROM ProductsReviews a WHERE a.productsReviewsId =:productsReviewsId",ProductsReviews.class)
                .setParameter("productsReviewsId",productsReviewsId)
                .getResultList();
        return result.isEmpty()? Optional.empty():Optional.of(result.get(0));
    }
}
