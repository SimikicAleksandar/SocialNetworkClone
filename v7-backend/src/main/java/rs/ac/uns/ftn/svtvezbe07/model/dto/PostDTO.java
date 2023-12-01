package rs.ac.uns.ftn.svtvezbe07.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Post;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class PostDTO {

    private Long id;

    @NotBlank
    private String content;
    @NotNull
    private String user;

    @NotBlank
    private String creationDate;

    private int likes;
    private int dislikes;
    private int hearts;

}
