package Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileDto {
    @Email
    private String email;
    @NotBlank(message = "Password can't be empty")
    private String password;
    @NotBlank(message = "Password can't be empty")
    private String newPassword;
    private String displayName;
    private String description;

}
