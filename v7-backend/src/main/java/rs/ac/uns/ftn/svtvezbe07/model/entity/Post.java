package rs.ac.uns.ftn.svtvezbe07.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "postsTable")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, name = "postId")
    private Long id;

    @Column(nullable = false)
    private Long user;
    @Column(nullable = false, name = "content")
    private String content;

    @Column(nullable = false, name = "creationDate")
    private LocalDateTime creationDate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Reaction> reactions = new HashSet<Reaction>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Comment> comments = new HashSet<Comment>();

    @Column(nullable = true)
    private Boolean deleted;


}
