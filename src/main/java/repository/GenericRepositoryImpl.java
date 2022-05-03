package repository;

import org.hibernate.SessionFactory;


public class GenericRepositoryImpl<T, ID> implements GenericRepository<T, ID> {

    protected SessionFactory sessionFactory = SessionFactoryConnection.getInstance();

    @Override
    public T save(T t) {
        try (var session = sessionFactory.openSession()) {
                session.save(t);
                return t;
        }
    }

    @Override
    public void update(T t) {
        try (var session = sessionFactory.openSession()) {
                session.update(t);
        }
    }

    @Override
    public void delete(T t) {
        try (var session = sessionFactory.openSession()) {
                session.delete(t);
        }
    }

    @Override
    public void deleteById(ID id) {
        try (var session = sessionFactory.openSession()) {
                session.delete(id);
        }
    }
}
