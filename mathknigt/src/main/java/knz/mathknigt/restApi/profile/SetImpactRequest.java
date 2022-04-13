package knz.mathknigt.restApi.profile;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SetImpactRequest {
    @NonNull private String newValue;
}
