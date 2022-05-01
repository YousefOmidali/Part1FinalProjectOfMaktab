package repository;

import entity.Experts;
import org.hibernate.SessionFactory;

import java.util.List;

public class ExpertRepository extends GenericRepositoryImpl<Experts, Long>{
    private SessionFactory sessionFactory = SessionFactoryConnection.getInstance();

    public Experts findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var a = session.find(Experts.class, id);
            session.getTransaction().commit();
            return a;
        }
    }

    public List<Experts> findAll() {
        try (var session = sessionFactory.openSession();) {
            String hql = " FROM entity.Experts ";
            var query = session.createQuery(hql, Experts.class);
            return query.getResultList();
        }
    }
}
