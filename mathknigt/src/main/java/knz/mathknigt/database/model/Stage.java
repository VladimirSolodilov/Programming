package knz.mathknigt.database.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Table(name = "stage")
@Entity
@ToString
@Getter
@RequiredArgsConstructor
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long        id;

    @Column(name = "grade", nullable = false, updatable = false)
    private Long        grade;

    @Column(name = "end_date", updatable = false, nullable = false)
    private String      end_date;

    @Column(name = "participants_count", nullable = false)
    private Long        participants_count;

    @Setter
    @ManyToOne(targetEntity = Tournament.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tournament", referencedColumnName = "id")
    private Tournament  tournament;

    @OneToMany(mappedBy = "stage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User>  users;

    public void addUser(User user){
        this.users.add(user);
        user.setStage(this);
    }

    public void removeUser(User user){
        this.users.remove(user);
        user.setStage(null);
    }
}
