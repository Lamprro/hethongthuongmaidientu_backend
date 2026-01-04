package Demo.DAO;

import Demo.Enity.CartsItems;
import Demo.Enity.Orders;
import Demo.Enity.Products;
import Demo.Enity.ProductsStores;
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
    public Page<Products> findByProductsNameAndCategoriesName(String productsName, List<String> categoriesName, Pageable pageable) {
        boolean hasCategory = categoriesName != null && !categoriesName.isEmpty();
        boolean hasName = productsName != null && !productsName.isBlank();
        String countJpql;
        if (hasCategory) {
            countJpql = """
            SELECT COUNT(DISTINCT p.productsId)
            FROM Products p
            JOIN p.categories c
            WHERE (:name IS NULL OR p.productsName LIKE :name)
            AND c.categoriesName IN :categories
            GROUP BY p.productsId
            HAVING COUNT(DISTINCT c.categoriesName) = :size
        """;
        } else {
            countJpql = """
            SELECT COUNT(p)
            FROM Products p
            WHERE (:name IS NULL OR p.productsName LIKE :name)
        """;
        }
        var countQuery = entityManager.createQuery(countJpql, Long.class);
        countQuery.setParameter(
                "name",
                hasName ? "%" + productsName + "%" : null
        );
        if (hasCategory) {
            countQuery.setParameter("categories", categoriesName);
            countQuery.setParameter("size", categoriesName.size());
        }
        Long total = hasCategory
                ? (long) countQuery.getResultList().size()
                : countQuery.getSingleResult();

        String dataJpql;
        if (hasCategory) {
            dataJpql = """
            SELECT DISTINCT p
            FROM Products p
            JOIN p.categories c
            WHERE (:name IS NULL OR p.productsName LIKE :name)
            AND c.categoriesName IN :categories
            GROUP BY p
            HAVING COUNT(DISTINCT c.categoriesName) = :size
        """;
        } else {
            dataJpql = """
            SELECT p
            FROM Products p
            WHERE (:name IS NULL OR p.productsName LIKE :name)
        """;
        }
        var dataQuery = entityManager.createQuery(dataJpql, Products.class);
        dataQuery.setParameter(
                "name",
                hasName ? "%" + productsName + "%" : null
        );
        if (hasCategory) {
            dataQuery.setParameter("categories", categoriesName);
            dataQuery.setParameter("size", categoriesName.size());
        }
        List<Products> result = dataQuery
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result, pageable, total);
    }


    @Override
    public Optional<Products> findById(int id) {
        List<Products> result = entityManager.createQuery("SELECT a FROM Products a WHERE a.productsId=:ordersId",Products.class)
                .setParameter("ordersId",id)
                .getResultList();
        return result.isEmpty()? Optional.empty():Optional.of(result.get(0));
    }



}