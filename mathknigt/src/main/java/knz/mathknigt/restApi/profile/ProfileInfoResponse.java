package knz.mathknigt.restApi.profile;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class ProfileInfoResponse {
    @NonNull private String status;
    @NonNull private String key;
    @NonNull private String first_name;
    @NonNull private String second_name;
    @NonNull private String patronymic;
    @NonNull private String nickname;
    @NonNull private String birthdate;
    @NonNull private String grade;
    @NonNull private String physical_value;
    @NonNull private String mental_value;
    @NonNull private String role_id;

    public ProfileInfoResponse(){
        this.status     = "Bad request";
        this.key        = "400";
        first_name      = "NoN";
        second_name     = "NoN";
        patronymic      = "NoN";
        nickname        = "NoN";
        birthdate       = "NoN";
        grade           = "0";
        physical_value  = "0";
        mental_value    = "0";
        role_id         = "0";
    }
    public ProfileInfoResponse(String status, String key){
        this.status     = status;
        this.key        = key;
        first_name      = "NoN";
        second_name     = "NoN";
        patronymic      = "NoN";
        nickname        = "NoN";
        birthdate       = "NoN";
        grade           = "0";
        physical_value  = "0";
        mental_value    = "0";
        role_id         = "0";
    }
    public ProfileInfoResponse(String first_name, String second_name, String patronymic, String nickname
            , String birthdate, String grade, String physical_value, String mental_value, String role_id){
        status              = "Ok";
        key                 = "200";
        this.first_name     = first_name;
        this.second_name    = second_name;
        this.patronymic     = patronymic;
        this.nickname       = nickname;
        this.birthdate      = birthdate;
        this.grade          = grade;
        this.physical_value = physical_value;
        this.mental_value   = mental_value;
        this.role_id        = role_id;
    }
    public ProfileInfoResponse(String first_name, String second_name, String patronymic, String nickname
            , String birthdate, String grade, String physical_value, String mental_value){
        status              = "Ok";
        key                 = "200";
        this.first_name     = first_name;
        this.second_name    = second_name;
        this.patronymic     = patronymic;
        this.nickname       = nickname;
        this.birthdate      = birthdate;
        this.grade          = grade;
        this.physical_value = physical_value;
        this.mental_value   = mental_value;
        this.role_id        = "1";
    }
}
