package entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String signUpTime;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private SubService subService;
    @OneToOne
    private Experts expert;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public Order(String signUpTime, Customer customer, SubService subService, Experts expert, OrderStatus orderStatus) {
        this.signUpTime = signUpTime;
        this.customer = customer;
        this.subService = subService;
        this.expert = expert;
        this.orderStatus = orderStatus;
    }
}
