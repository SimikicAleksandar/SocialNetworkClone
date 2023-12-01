package rs.ac.uns.ftn.svtvezbe07.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Reaction;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostReactionsDTO {

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
