package knz.mathknigt.database.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Table(name = "personality")
@Entity
@ToString
@RequiredArgsConstructor
@Getter
public class Personality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long        id;

    @Column(name = "first_name", nullable = false, updatable = false)
    private String      first_name;

    @Column(name = "second_name", nullable = false, updatable = false)
    private String      second_name;

    @Column(name = "patronymic", nullable = false, updatable = false)
    private String      patronymic;

    @Column(name = "nickname", nullable = false, updatable = false)
    private String      nickname;

    @Column(name = "birthdate", nullable = false, updatable = false)
    private String      birthdate;

    @OneToOne(mappedBy = "personality", cascade = CascadeType.ALL)
    private User        user;

    public Personality(@NonNull String first_name, @NonNull String second_name, @NonNull String patronymic
            , @NonNull String nickname, @NonNull String birthdate){
        this.first_name     = first_name;
        this.second_name    = second_name;
        this.patronymic     = patronymic;
        this.nickname       = nickname;
        this.birthdate      = birthdate;
    }

    public Personality(@NonNull String first_name, @NonNull String second_name, @NonNull String patronymic
            , @NonNull String nickname, @NonNull Date birthdate){
        this.first_name     = first_name;
        this.second_name    = second_name;
        this.patronymic     = patronymic;
        this.nickname       = nickname;
        this.birthdate      = birthdate.toString();
    }
}
