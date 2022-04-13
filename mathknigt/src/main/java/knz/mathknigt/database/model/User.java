package knz.mathknigt.database.model;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "user")
@Entity
@ToString
@AllArgsConstructor
@Getter
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Role", updatable = false)
    private Long        id;

    @Column(name = "Email", nullable = false, unique = true, updatable = false)
    private String      email;

    @Column(name = "Password_hash", nullable = false, updatable = false)
    private String      password;

    @Setter
    @Column(name = "grade", nullable = false)
    private Long        grade;
    @Setter
    @ManyToOne(targetEntity = Role.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role", referencedColumnName = "id")
    private Role        role;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Personality.class, orphanRemoval = true)
    @JoinColumn(name = "id_personality", nullable = false, updatable = false, referencedColumnName = "id")
    private Personality personality;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User>   rivals;
    public User(String email, String password_hash, Role role, Personality personality) {
        this.email          = email;
        this.password       = password_hash;
        this.role           = role;
        this.personality    = personality;
        this.rivals         = new HashSet<>();
        this.grade          = 0L;
    }
}
