package rs.ac.uns.ftn.svtvezbe07.model.dto;

import rs.ac.uns.ftn.svtvezbe07.model.entity.Comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CommentDTO {

    private Long id;

    @NotBlank
    private String text;
    @NotNull
    private LocalDate timestamp;

    @NotBlank
    private boolean isDeleted;

    public CommentDTO (Comment comment) {
        this.id = comment.getId();
        this.text = comment.getText();
        this.timestamp = comment.getTimestamp();
        this.isDeleted = comment.isDeleted();
    }

    public CommentDTO(){}

}
