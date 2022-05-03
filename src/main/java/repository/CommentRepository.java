package repository;

import entity.Comment;
import org.hibernate.SessionFactory;

import java.util.List;

public class CommentRepository extends GenericRepositoryImpl<Comment, Long>{
    private SessionFactory sessionFactory = SessionFactoryConnection.getInstance();

    public Comment findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            var a = session.find(Comment.class, id);
            return a;
        }
    }

    public List<Comment> findAll() {
        try (var session = sessionFactory.openSession();) {
            String hql = " FROM entity.Comment   ";
            var query = session.createQuery(hql, Comment.class);
            return query.getResultList();
        }
    }
}
