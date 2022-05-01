package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Experts extends Users {
//    @Lob
//    @Column(name = "photo", columnDefinition = "BLOB")
//    private byte[] photo;
    private Long likes;
    @ManyToOne
    private SubService subService;

//    @OneToMany(mappedBy = "experts")
//    private List<SubService> subService;

    public Experts(String firstname, String lastname, String email, String username, String password, Status status, String signUpTime, byte[] photo, Long likes) {
        super(firstname, lastname, email, username, password, status, signUpTime);
//        this.photo = photo;
        this.likes = likes;
    }

    public String toString() {
        return "Expert{" +
                "id='" + getId() + '\'' +
                "username='" + getUsername() + '\'' +
                "password='" + getPassword() + '\'' +
                "firstname='" + getFirstname() + '\'' +
                "lastname='" + getLastname() + '\'' +
                "email='" + getEmail() + '\'' +
                "status='" + getStatus() + '\'' +
                "signUptime='" + getSignUpTime() + '\'' +
//                "photo=" + Arrays.toString(photo) +
                "subService=" + getSubService() +
                "likes=" + getLikes() +
                '}';
    }
}
