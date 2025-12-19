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
            Pageable pageable) {

        StringBuilder jpql = new StringBuilder(
                "SELECT p FROM Products p JOIN p.categories c WHERE 1=1 "
        );

        StringBuilder jpqlCount = new StringBuilder(
                "SELECT p FROM Products p JOIN p.categories c WHERE 1=1 "
        );

        // Lọc theo tên sản phẩm
        if (productsName != null && !productsName.trim().isEmpty()) {
            jpql.append(" AND p.productsName LIKE :name ");
            jpqlCount.append(" AND p.productsName LIKE :name ");
        }

        // Lọc product phải chứa TẤT CẢ category trong list
        if (categoriesName != null && !categoriesName.isEmpty()) {
            jpql.append(" AND c.categoriesName IN :categories ");
            jpqlCount.append(" AND c.categoriesName IN :categories ");

            jpql.append(" GROUP BY p HAVING COUNT(DISTINCT c.categoriesName) = :size ");
            jpqlCount.append(" GROUP BY p HAVING COUNT(DISTINCT c.categoriesName) = :size ");
        }

        // Tạo query
        var queryData = entityManager.createQuery(jpql.toString(), Products.class);
        var queryCount = entityManager.createQuery(jpqlCount.toString(), Products.class);

        // Set parameters
        if (productsName != null && !productsName.trim().isEmpty()) {
            queryData.setParameter("name", "%" + productsName + "%");
            queryCount.setParameter("name", "%" + productsName + "%");
        }

        if (categoriesName != null && !categoriesName.isEmpty()) {
            queryData.setParameter("categories", categoriesName);
            queryCount.setParameter("categories", categoriesName);

            queryData.setParameter("size", categoriesName.size());
            queryCount.setParameter("size", categoriesName.size());
        }

        // Lấy total (vì GROUP BY → không dùng getSingleResult)
        Long total = (long) queryCount.getResultList().size();

        // Lấy dữ liệu phân trang
        List<Products> result = queryData
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