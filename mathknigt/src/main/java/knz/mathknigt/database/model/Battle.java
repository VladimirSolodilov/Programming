package knz.mathknigt.database.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "battle")
@Entity
@ToString
@AllArgsConstructor
@Getter
public class Battle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long        id;

    @OneToMany(mappedBy = "battle", cascade = CascadeType.ALL)
    private List<User>  users;

    @Column(name = "rounds", nullable = false)
    private Long        rounds;

    @Setter
    @Column(name = "problem_left")
    private Long        problem_left;

    @Setter
    @Column(name = "problem_right")
    private Long        problem_right;

    @Setter
    @Column(name = "resolve_left")
    private Long        resolve_left;

    @Setter
    @Column(name = "resolve_right")
    private Long        resolve_right;

    @Setter
    @Column(name = "stamina_left")
    private Long        stamina_left;

    @Setter
    @Column(name = "stamina_right")
    private Long        stamina_right;

    public void addUser(@NonNull User user){
        try {
            if (this.users.contains(user) || user.getBattle() != null) throw new IllegalArgumentException();
            if (this.users.size() > 1) throw new Exception();
            this.users.add(user);
            user.setBattle(this);
        }catch (IllegalArgumentException exception){
            exception.printStackTrace();
            System.out.println("this user already in battle!");
        }catch (Exception exception){
            exception.printStackTrace();
            System.out.println("Battle already is full!");
        }
    }

    public void removeUser(User user){
        try {
            if (!this.users.contains(user)) throw new IllegalArgumentException();
            this.users.remove(user);
            user.setBattle(null);
        }catch (IllegalArgumentException exception){
            exception.printStackTrace();
            System.out.println("this user already leave this battle!");
        }
    }

    public Battle(){
        this.users  = new ArrayList<>();
        this.rounds = 0L;
    }
}
