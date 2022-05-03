package repository;

import entity.Admin;
import entity.Customer;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository extends GenericRepositoryImpl<Customer, Long> {
    private SessionFactory sessionFactory = SessionFactoryConnection.getInstance();

    public Customer findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            var a = session.find(Customer.class, id);
            return a;
        }
    }

    public List<Customer> findAll() {
        try (var session = sessionFactory.openSession();) {
            String hql = " FROM entity.Customer   ";
            var query = session.createQuery(hql, Customer.class);
            return query.getResultList();
        }
    }
    public Customer login(String username, String password) {
        var session = sessionFactory.openSession();
        String hql = " FROM entity.Customer a " +
                " WHERE a.username = :username " +
                " AND a.password = :password ";
        var query = session.createQuery(hql, Customer.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getSingleResult();
    }

    public List<Customer> gridSearch(
            String firstName,
            String lastName,
            String email,
            String username
    ) {
        try (var session = sessionFactory.openSession()) {
            var cb = session.getCriteriaBuilder();
            var contactQuery = cb.createQuery(Customer.class); // result query
            var root = contactQuery.from(Customer.class); // select query

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
