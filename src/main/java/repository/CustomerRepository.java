package repository;

import entity.Customer;
import org.hibernate.SessionFactory;

import java.util.List;

public class CustomerRepository extends GenericRepositoryImpl<Customer, Long> {
    private SessionFactory sessionFactory = SessionFactoryConnection.getInstance();

    public Customer findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var a = session.find(Customer.class, id);
            session.getTransaction().commit();
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
}
