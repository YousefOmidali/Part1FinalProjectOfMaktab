package repository;

import entity.Wallet;
import org.hibernate.SessionFactory;

import java.util.List;

public class WalletRepository extends GenericRepositoryImpl <Wallet, Long>{
    private SessionFactory sessionFactory = SessionFactoryConnection.getInstance();

    public Wallet findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            var a = session.find(Wallet.class, id);
            return a;
        }
    }


    public List<Wallet> findAll() {
        try (var session = sessionFactory.openSession();) {
            String hql = " FROM entity.Wallet   ";
            var query = session.createQuery(hql, Wallet.class);
            return query.getResultList();
        }
    }
}
