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
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private SubService subService;
    @OneToOne
    private Experts expert;

    public Order(Customer customer, SubService subService, Experts expert) {
        this.customer = customer;
        this.subService = subService;
        this.expert = expert;
    }
}
