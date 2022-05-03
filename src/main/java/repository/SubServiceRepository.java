package repository;

import entity.Admin;
import entity.SubService;
import org.hibernate.SessionFactory;

import java.util.List;

public class SubServiceRepository extends GenericRepositoryImpl<SubService,Long>{
    private SessionFactory sessionFactory = SessionFactoryConnection.getInstance();

    public SubService findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var a = session.find(SubService.class, id);
            session.getTransaction().commit();
            return a;
        }
    }

    public List<SubService> findAll() {
        try (var session = sessionFactory.openSession();) {
            String hql = " FROM entity.SubService   ";
            var query = session.createQuery(hql, SubService.class);
            return query.getResultList();
        }
    }
    public List<SubService> findAllInAService(Long id) {
        try (var session = sessionFactory.openSession();) {
            String hql = " FROM entity.SubService s where s.service.id =: id  ";
            var query = session.createQuery(hql, SubService.class);
            query.setParameter("id", id);
            return query.getResultList();
        }
    }
}
