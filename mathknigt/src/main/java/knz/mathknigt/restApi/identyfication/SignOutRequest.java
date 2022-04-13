package knz.mathknigt.restApi.identyfication;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignOutRequest {
    @NonNull private String time_sign_out;
}