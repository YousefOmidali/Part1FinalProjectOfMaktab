package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@Entity
public class Admin extends Users {


    public Admin(String firstname, String lastname, String email, String username, String password, Status status, String signUpTime) {
        super(firstname, lastname, email, username, password, status, signUpTime);
    }


    public Admin(Long id, String firstname, String lastname, String email, String username, String password, Status status, String signUpTime) {
        super(id, firstname, lastname, email, username, password, status, signUpTime);
    }

    public String toString() {
        return "Admin{" +
                "id='" + getId() + '\'' +
                "username='" + getUsername() + '\'' +
                "password='" + getPassword() + '\'' +
                "firstname='" + getFirstname() + '\'' +
                "lastname='" + getLastname() + '\'' +
                "email='" + getEmail() + '\'' +
                "status='" + getStatus() + '\'' +
                "signUptime='" + getSignUpTime() + '\'' +
                '}';
    }
}
