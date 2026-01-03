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
    public Page<Products> findByProductsNameAndCategoriesName(
            String productsName,
            List<String> categoriesName,
            Pageable pageable
    ) {

        // ---------- COUNT ----------
        String countJpql = """
        SELECT COUNT(DISTINCT p.productsId)
        FROM Products p
        WHERE (:name IS NULL OR p.productsName LIKE :name)
        AND (
            :categories IS NULL OR
            p.productsId IN (
                SELECT p2.productsId
                FROM Products p2
                JOIN p2.categories c2
                WHERE c2.categoriesName IN :categories
                GROUP BY p2.productsId
                HAVING COUNT(DISTINCT c2.categoriesName) = :size
            )
        )
    """;

        Long total = entityManager.createQuery(countJpql, Long.class)
                .setParameter("name",
                        productsName == null || productsName.isBlank()
                                ? null
                                : "%" + productsName + "%")
                .setParameter("categories",
                        categoriesName == null || categoriesName.isEmpty()
                                ? null
                                : categoriesName)
                .setParameter("size",
                        categoriesName == null ? 0 : categoriesName.size())
                .getSingleResult();


        // ---------- DATA ----------
        String dataJpql = """
        SELECT DISTINCT p
        FROM Products p
        WHERE (:name IS NULL OR p.productsName LIKE :name)
        AND (
            :categories IS NULL OR
            p.productsId IN (
                SELECT p2.productsId
                FROM Products p2
                JOIN p2.categories c2
                WHERE c2.categoriesName IN :categories
                GROUP BY p2.productsId
                HAVING COUNT(DISTINCT c2.categoriesName) = :size
            )
        )
    """;

        List<Products> result = entityManager.createQuery(dataJpql, Products.class)
                .setParameter("name",
                        productsName == null || productsName.isBlank()
                                ? null
                                : "%" + productsName + "%")
                .setParameter("categories",
                        categoriesName == null || categoriesName.isEmpty()
                                ? null
                                : categoriesName)
                .setParameter("size",
                        categoriesName == null ? 0 : categoriesName.size())
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