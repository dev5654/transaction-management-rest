package uz.ml.delivering_rest.dto.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserLoginDTO {
    private String username;
    private String password;
}
