package knz.mathknigt.restApi.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Knight {
    Long stamina;
    final Long physicalImpact;
    final Long mentalImpact;
}
