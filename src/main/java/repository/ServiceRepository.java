package repository;

import entity.Service;
import org.hibernate.SessionFactory;

import java.util.List;

public class ServiceRepository extends GenericRepositoryImpl<Service, Long> {
    private SessionFactory sessionFactory = SessionFactoryConnection.getInstance();

    public Service findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            var a = session.find(Service.class, id);
            return a;
        }
    }

    public List<Service> findAll() {
        try (var session = sessionFactory.openSession();) {
            String hql = " FROM entity.Service   ";
            var query = session.createQuery(hql, Service.class);
            return query.getResultList();
        }
    }
}
