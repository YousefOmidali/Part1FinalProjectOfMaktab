package entity;

import exceptions.InvalidPassword;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@MappedSuperclass
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    private String username;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String signUpTime;

    public Users(String firstname, String lastname, String email, String username, String password, Status status, String signUpTime) {
        CheckPassword checkPassword = new CheckPassword();
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        if (checkPassword.checkPasswordCondition(password))
            this.password = password;
        else
            throw new InvalidPassword("Invalid password! ");
        this.status = status;
        this.signUpTime = signUpTime;
    }

    public Users(String username, String password) {
        CheckPassword checkPassword = new CheckPassword();
        this.username = username;
        if (checkPassword.checkPasswordCondition(password))
            this.password = password;
        else
            throw new InvalidPassword("Invalid password! ");
    }
}
