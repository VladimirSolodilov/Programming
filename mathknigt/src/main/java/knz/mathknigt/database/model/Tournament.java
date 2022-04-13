package knz.mathknigt.database.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "tournament")
@Entity
@ToString
@Getter
@RequiredArgsConstructor
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long    id;

    @Column(name = "title", nullable = false, updatable = false, unique = true)
    private String  title;

    @Column(name = "start_date", nullable = false, updatable = false)
    private String  start_date;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stage> stages;

    public Tournament(String title){
        this.title      = title;
        this.start_date = new Date().toString();
        this.stages     = new ArrayList<>();
    }

    public void addStage(@NonNull Stage stage){
        try {
            if (this.stages.contains(stage)) throw new IllegalArgumentException();
            this.stages.add(stage);
            stage.setTournament(this);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void removeStage(@NonNull Stage stage){
        try {
            if (!this.stages.contains(stage)) throw new IllegalArgumentException();
            this.stages.remove(stage);
            stage.setTournament(null);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
