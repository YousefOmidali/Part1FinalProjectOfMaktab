package repository;

import entity.Admin;
import entity.Customer;
import org.hibernate.SessionFactory;

import javax.imageio.ImageIO;
import javax.persistence.criteria.Predicate;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository extends GenericRepositoryImpl<Admin, Long> {
    private SessionFactory sessionFactory = SessionFactoryConnection.getInstance();

    public Admin findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var a = session.find(Admin.class, id);
            session.getTransaction().commit();
            return a;
        }
    }

    public Admin login(String username, String password) {
        var session = sessionFactory.openSession();
        String hql = " FROM entity.Admin a " +
                " WHERE a.username = :username " +
                " AND a.password = :password ";
        var query = session.createQuery(hql, Admin.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getSingleResult();
    }

    public List<Admin> findAll() {
        var session = sessionFactory.openSession();
        String hql = " FROM entity.Admin ";
        var query = session.createQuery(hql, Admin.class);
        return query.getResultList();
    }

    public List<Admin> gridSearch(
            String firstName,
            String lastName,
            String email,
            String username
    ) {
        try (var session = sessionFactory.openSession()) {
            var cb = session.getCriteriaBuilder();
            var contactQuery = cb.createQuery(Admin.class); // result query
            var root = contactQuery.from(Admin.class); // select query

            contactQuery.select(root);
            List<Predicate> predicates = new ArrayList<>();

            if (firstName != null && !firstName.isEmpty()) {
                predicates.add(cb.equal(root.get("firstname"), firstName));
            }
            if (lastName != null && !lastName.isEmpty()) {
                predicates.add(cb.equal(root.get("lastname"), lastName));
            }
            if (email != null && !email.isEmpty()) {
                predicates.add(cb.equal(root.get("email"), email));
            }
            if (username != null && !username.isEmpty()) {
                predicates.add(cb.equal(root.get("username"), username));
            }

            contactQuery
                    .where(predicates.toArray(new Predicate[0]))
                    .orderBy(cb.asc(root.get("firstname")));
            return session.createQuery(contactQuery).list();
        }
    }

}
