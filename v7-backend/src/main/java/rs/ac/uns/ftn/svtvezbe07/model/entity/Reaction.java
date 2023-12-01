package rs.ac.uns.ftn.svtvezbe07.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "reactions")
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "type")
    @Enumerated(EnumType.STRING)
    private ReactionType type;

    @Column(nullable = false, name = "timestamp")
    private LocalDate timestamp;

    @Column(name="user")
    private Long user;

    @Column(name="comment")
    private Long comment;

    @Column(name="post")
    private Long post;

}
