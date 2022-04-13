package knz.mathknigt.database.model;

import knz.mathknigt.configs.ConfigConstants;
import lombok.*;

import javax.persistence.*;

@Table(name = "impact_set")
@Entity
@ToString
@AllArgsConstructor
@Getter
public class ImpactSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long    id;

    @Column(name = "physical_value", nullable = false)
    private Long    physical_value;

    @Column(name = "mental_value", nullable = false)
    private Long    mental_value;

    @OneToOne(mappedBy = "impact_set", cascade = CascadeType.ALL)
    private User    user;

    public void setPhysical_value(@NonNull Long inphysical_value){
        try {
            if (inphysical_value < ConfigConstants.MINIMALPHYSICALIMPACTVALUE
            || inphysical_value > ConfigConstants.MAXIMALPHYSICALIMPACTVALUE) throw new Exception();
            this.physical_value = inphysical_value;
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void setMental_value(@NonNull Long inMental_value){
        try {
            if (inMental_value < ConfigConstants.MINIMALMENTALIMPACTVALUE
            || inMental_value > ConfigConstants.MAXIMALMENTALIMPACTVALUE) throw new Exception();
            this.physical_value = inMental_value;
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public ImpactSet(){
        this.physical_value = 1L;
        this.mental_value   = 1L;
    }
}
