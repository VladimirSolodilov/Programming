package knz.myprojectsite.database.model;

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
    @Column(name = "id", updatable = false)
    private Long        id;

    @Column(name = "email", nullable = false, unique = true, updatable = false)
    private String      email;

    @Column(name = "password", nullable = false, updatable = false)
    private String      password;

    @Setter
    @Column(name = "grade", nullable = false)
    private Long        grade;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Salt.class, orphanRemoval = true)
    @JoinColumn(name = "id_salt", nullable = false, updatable = false, referencedColumnName = "id")
    private Salt        salt;

    @Setter
    @ManyToOne(targetEntity = Role.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role", referencedColumnName = "id")
    private Role        role;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Personality.class, orphanRemoval = true)
    @JoinColumn(name = "id_personality", nullable = false, updatable = false, referencedColumnName = "id")
    private Personality personality;

    public User(String email, String password_hash
            , Salt salt, Role role, Personality personality) {
        this.email          = email;
        this.password       = password_hash;
        this.salt           = salt;
        this.role           = role;
        this.personality    = personality;
        this.grade          = 0L;
    }
}
