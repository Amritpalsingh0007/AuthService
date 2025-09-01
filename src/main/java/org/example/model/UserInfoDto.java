package org.example.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.example.eventProducer.UserInfoEvent;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoDto {

    private String userId;

    @NotEmpty(message = "Username is required")
    @Size(min=3, message="Username must be of length 3 to 20 character long",max=20)
    @Pattern(regexp = "^[a-zA-Z0-9]{3,20}$", message = "Username should only consists of letters and digits")
    private String username;

    @NotEmpty(message = "Password is required")
    @Size(min=8, max=20, message="Password must be of length 8 to 20 character long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$", message = "Password should consists of atleast one digit, small and capital letter and a special character")
    private String password;

    @Pattern(regexp = "^[a-z]{3,20}$")
    private String firstName;

    @Pattern(regexp="^[a-z]{3,20}$")
    private String lastName;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @Email(message="Email address is not valid!")
    private String email;

     public UserInfoEvent convertToUserInfoEvent(){
        return UserInfoEvent.builder()
                .userId(userId)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }
}
