package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Customer extends Users {
    @OneToOne
    private Wallet wallet;
    @OneToMany(mappedBy = "customer")
    private List<Comment> comments;
//    @OneToMany(mappedBy = "customer")
//    private List<Order> orders;


    public Customer(String firstname, String lastname, String email, String username, String password, Status status, String signUpTime, Wallet wallet, List<Comment> comments) {
        super(firstname, lastname, email, username, password, status, signUpTime);
        this.wallet = wallet;
        this.comments = comments;
    }

    public Customer(String username, String password) {
        super(username, password);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + getId() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", firstname='" + getFirstname() + '\'' +
                ", lastname='" + getLastname() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", signUptime='" + getSignUpTime() + '\'' +
                ", wallet=" + wallet +
                ", comment=" + comments +
//                ", orders=" + orders +
                '}';
    }
}
