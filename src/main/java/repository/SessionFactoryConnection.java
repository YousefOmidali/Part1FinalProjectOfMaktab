package repository;

import entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactoryConnection {
    private SessionFactoryConnection(){}
    private static class LazyHolder{
        static SessionFactory INSTANCE;

        static {
            var registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

            INSTANCE = new MetadataSources(registry)
                    .addAnnotatedClass(Admin.class)
                    .addAnnotatedClass(Comment.class)
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(Experts.class)
//                    .addAnnotatedClass(Worker.class)
                    .addAnnotatedClass(Order.class)
                    .addAnnotatedClass(Service.class)
                    .addAnnotatedClass(SubService.class)
                    .addAnnotatedClass(Status.class)
                    .addAnnotatedClass(Users.class)
                    .addAnnotatedClass(Wallet.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }
    }
    public static SessionFactory getInstance(){
        return LazyHolder.INSTANCE;
    }
}
