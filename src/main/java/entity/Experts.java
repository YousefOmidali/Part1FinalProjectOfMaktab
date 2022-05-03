package entity;

import exceptions.FileIsTooBig;
import lombok.*;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Experts extends Users {

    private Long likes;
    @Lob
    @Column(name = "IMAGE")
    private Blob image;
    @OneToMany
    private List<SubService> subService;
    @OneToOne
    private Wallet wallet;


    @SneakyThrows
    public Experts(String firstname, String lastname, String email, String username, String password, Status status, String signUpTime, Long likes, SubService subService, Wallet wallet, Blob image) {
        super(firstname, lastname, email, username, password, status, signUpTime);
        this.likes = likes;
        if ((image.length() / 1024) <= 300)
            this.image = image;
        else
            throw new FileIsTooBig("file is too big! (upto 300kb & jpg)");
//        this.subService = subService;
        this.wallet = wallet;
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
//                "subService=" + getSubService() +
                "likes=" + getLikes() +
                "image=" + getImage() +
                '}';
    }
}
