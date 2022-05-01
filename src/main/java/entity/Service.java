package entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    private String description;
//    @OneToMany(mappedBy = "Service")
//    private List<SubService> subService;

}
