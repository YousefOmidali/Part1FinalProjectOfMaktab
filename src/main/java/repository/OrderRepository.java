package repository;

import entity.Order;
import entity.OrderStatus;
import org.hibernate.SessionFactory;

import java.util.List;

public class OrderRepository extends GenericRepositoryImpl<Order, Long> {
    private SessionFactory sessionFactory = SessionFactoryConnection.getInstance();

    public Order findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var a = session.find(Order.class, id);
            session.getTransaction().commit();
            return a;
        }
    }

    public List<Order> findAll() {
        try (var session = sessionFactory.openSession();) {
            String hql = " FROM entity.Order   ";
            var query = session.createQuery(hql, Order.class);
            return query.getResultList();
        }
    }
    public List<Order> findAllOfAnExpert(Long id) {
        try (var session = sessionFactory.openSession();) {
            String hql = " FROM entity.Order o where o.expert.id =: id and o.orderStatus =: status";
            var query = session.createQuery(hql, Order.class);
            query.setParameter("id", id);
            query.setParameter("status", OrderStatus.NotFinished);
            return query.getResultList();
        }
    }
}
