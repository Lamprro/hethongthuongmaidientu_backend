package Demo.DAO;

import Demo.Enity.CartsItems;
import Demo.Enity.Orders;
import Demo.Enity.Products;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public class ProductsDAOImplement implements ProductsDAO {
    @Autowired
    EntityManager entityManager;

    @Override
    public void create(Products products) {
        entityManager.persist(products);
    }

    @Override
    public void update(Products products) {
        entityManager.merge(products);
    }

    @Override
    public Page<Products> findByProductsNameAndCategoriesName(
            String productsName,
            List<String> categoriesName,
            Pageable pageable) {

        StringBuilder jpql = new StringBuilder("SELECT DISTINCT p FROM Products p LEFT JOIN p.categories c WHERE 1=1 ");
        StringBuilder jpqlCount = new StringBuilder("SELECT COUNT(DISTINCT p) FROM Products p LEFT JOIN p.categories c WHERE 1=1 ");

        if (productsName != null && !productsName.trim().isEmpty()) {
            jpql.append(" AND p.productsName LIKE :name ");
            jpqlCount.append(" AND p.productsName LIKE :name ");
        }

        if (categoriesName != null && !categoriesName.isEmpty()) {
            jpql.append(" AND c.categoriesName IN :categories ");
            jpqlCount.append(" AND c.categoriesName IN :categories ");
        }

        // Query count
        var queryCount = entityManager.createQuery(jpqlCount.toString(), Long.class);
        // Query data
        var queryData = entityManager.createQuery(jpql.toString(), Products.class);

        // Set parameters
        if (productsName != null && !productsName.trim().isEmpty()) {
            queryCount.setParameter("name", "%" + productsName + "%");
            queryData.setParameter("name", "%" + productsName + "%");
        }
        if (categoriesName != null && !categoriesName.isEmpty()) {
            queryCount.setParameter("categories", categoriesName);
            queryData.setParameter("categories", categoriesName);
        }

        Long total = queryCount.getSingleResult();

        List<Products> result = queryData
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result, pageable, total);
    }

    @Override
    public Optional<Products> findById(int id) {
        List<Products> result = entityManager.createQuery("SELECT a FROM Orders a WHERE a.ordersId=:ordersId",Products.class)
                .setParameter("ordersId",id)
                .getResultList();
        return result.isEmpty()? Optional.empty():Optional.of(result.get(0));
    }

}
