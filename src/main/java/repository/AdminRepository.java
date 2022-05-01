package repository;

import entity.Admin;
import org.hibernate.SessionFactory;

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
}
