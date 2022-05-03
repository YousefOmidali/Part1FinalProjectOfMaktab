package repository;

import entity.Admin;
import entity.Customer;
import entity.Experts;
import exceptions.FileIsTooBig;
import org.hibernate.SessionFactory;

import javax.imageio.ImageIO;
import javax.persistence.criteria.Predicate;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExpertRepository extends GenericRepositoryImpl<Experts, Long> {
    private SessionFactory sessionFactory = SessionFactoryConnection.getInstance();

    public Experts findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            var a = session.find(Experts.class, id);
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

    public List<Experts> suggestions() {
        try (var session = sessionFactory.openSession();) {
            String hql = " FROM entity.Experts e ORDER BY e.likes";
            var query = session.createQuery(hql, Experts.class);
            return query.getResultList();
        }
    }

    public Experts login(String username, String password) {
        var session = sessionFactory.openSession();
        String hql = " FROM entity.Experts a " +
                " WHERE a.username = :username " +
                " AND a.password = :password ";
        var query = session.createQuery(hql, Experts.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getSingleResult();
    }

    public List<Experts> gridSearch(
            String firstName,
            String lastName,
            String email,
            String username
    ) {
        try (var session = sessionFactory.openSession()) {
            var cb = session.getCriteriaBuilder();
            var contactQuery = cb.createQuery(Experts.class); // result query
            var root = contactQuery.from(Experts.class); // select query

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

    public static byte[] getImage(String address) {
        File file = new File(address);
        System.out.println(file.exists());
        if (file.exists()) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                var byteOutStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", byteOutStream);
                return byteOutStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
