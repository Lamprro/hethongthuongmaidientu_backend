package Demo.DAO;

import Demo.DAO.DTO.CustomerReportDTO;
import Demo.Enity.Accounts;
import Demo.Enity.Users;
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
public class UsersDAOImplement implements UsersDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void create(Users users) {
        entityManager.persist(users);
    }

    @Override
    public void update(Users users) {
        entityManager.merge(users);
    }

    @Override
    public Optional<Users> findById(int id) {
        List<Users> result = entityManager.createQuery("SELECT u FROM Users u WHERE u.usersId = :id", Users.class)
                .setParameter("id", id)
                .getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    @Override
    public Page<Users> findByUsersName(String name, Pageable pageable) {
        // Đếm tổng số bản ghi
        Long total = entityManager.createQuery("SELECT COUNT(u) FROM Users u WHERE LOWER(u.usersName) LIKE LOWER(:name)", Long.class)
                .setParameter("name", "%" + name + "%")
                .getSingleResult();

        // Lấy danh sách người dùng theo trang
        List<Users> result = entityManager.createQuery(
                        "SELECT u FROM Users u WHERE LOWER(u.usersName) LIKE LOWER(:name)", Users.class)
                .setParameter("name", "%" + name + "%")
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result, pageable, total);
    }

    @Override
    public Page<Users> findAll(Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(u) FROM Users u", Long.class)
                .getSingleResult();

        List<Users> result = entityManager.createQuery("SELECT u FROM Users u ", Users.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result, pageable, total);
    }

    @Override
    public boolean exsistEmails(String email) {
        List<Users> result = entityManager.createQuery("SELECT a FROM Users a WHERE a.usersEmail = :usersEmail",Users.class)
                .setParameter("usersEmail",email)
                .getResultList();
        return result.isEmpty()?false:true;
    }

    @Override
    public Users findByEmails(String email) {
        List<Users> result = entityManager.createQuery("SELECT a FROM Users a WHERE a.usersEmail = :usersEmail",Users.class)
                .setParameter("usersEmail",email)
                .getResultList();
        return result.isEmpty()?null:result.get(0);
    }

    @Override
    public Page<Users> findAllUsersSeller(Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(u) FROM Users u WHERE u.roles.rolesId=2", Long.class)
                .getSingleResult();

        List<Users> result = entityManager.createQuery("SELECT u FROM Users u WHERE u.roles.rolesId=2 ", Users.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result, pageable, total);
    }

    @Override
    public Page<Users> findCustomer(Pageable pageable) {
        Long total = entityManager.createQuery("SELECT COUNT(u) FROM Users u WHERE u.roles.rolesId=3", Long.class)
                .getSingleResult();
        List<Users> result = entityManager.createQuery("SELECT u FROM Users u WHERE u.roles.rolesId=3 ", Users.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return new PageImpl<>(result, pageable, total);
    }


}
