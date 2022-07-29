package uz.ml.delivering_rest.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponseDTO {
    private String accessToken;
    private String refreshToken;
}
