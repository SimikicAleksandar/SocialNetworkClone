package rs.ac.uns.ftn.svtvezbe07.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Roles;
import rs.ac.uns.ftn.svtvezbe07.model.entity.User;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    @NotBlank
    private String email;
    @NotBlank
    private LocalDateTime lastLogin;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;

    private Roles role;


    public UserDTO(User createdUser) {
        this.id = createdUser.getId();
        this.username = createdUser.getUsername();
        this.password = createdUser.getPassword();
        this.email = createdUser.getEmail();
        this.role = createdUser.getRole();
        this.lastLogin = createdUser.getLastLogin();
        this.firstname = createdUser.getFirstname();
        this.lastname = createdUser.getLastname();
    }
}
