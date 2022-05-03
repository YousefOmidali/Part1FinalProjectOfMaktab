package repository;

import entity.SubService;
import org.hibernate.SessionFactory;

import java.util.List;

public class SubServiceRepository extends GenericRepositoryImpl<SubService,Long>{
    private SessionFactory sessionFactory = SessionFactoryConnection.getInstance();

    public SubService findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            var a = session.find(SubService.class, id);
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
}
