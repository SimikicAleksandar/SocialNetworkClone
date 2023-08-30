package Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReactPostDto {
    private Long reactedBy;
    private String reactionType;
    private Long post;
}
