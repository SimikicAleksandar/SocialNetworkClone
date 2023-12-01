package rs.ac.uns.ftn.svtvezbe07.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "text")
    private String text;

    @Column(nullable = false, name = "timestamp")
    private LocalDate timestamp;
    @Column(name = "deleted", nullable = false)
    private boolean isDeleted;

    @OneToMany( fetch = FetchType.LAZY)
    private Set<Reaction> reactionSet= new HashSet<Reaction>();
}
