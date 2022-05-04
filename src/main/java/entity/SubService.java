package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class SubService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String description;
    private Long basePrice;
    @ManyToOne
    private Service service;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "SubServices_Experts",
            joinColumns = {@JoinColumn(name = "subservice_id")},
            inverseJoinColumns = {@JoinColumn(name = "experts_id")})
    private Set<Experts> experts = new HashSet<>();

    @Override
    public String toString() {
        return "SubService{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", basePrice=" + basePrice +
                ", service=" + service +
                '}';
    }
}
