package knz.mathknigt.database.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "role")
@Entity
@ToString
@Getter
@RequiredArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long        id;

    @Column(name = "role_name", nullable = false, updatable = false, unique = true)
    private String      role_name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User>  users  = new ArrayList<>();

    public void addUser(User user){
        this.users.add(user);
        user.setRole(this);
    }

    public void removeUser(User user){
        this.users.remove(user);
        user.setRole(null);
    }

    public Role(@NonNull String role_name){
        this.role_name = role_name;
    }
}
