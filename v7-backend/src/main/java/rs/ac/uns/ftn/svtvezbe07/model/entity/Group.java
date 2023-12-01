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
@Table(name = "groupsTable")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupId", unique = true, nullable = false)
    private Long id;

    @Column(name = "groupName", unique = true, nullable = false)
    private String name;

    @Column(name = "groupDescription", nullable = false)
    private String description;

    @Column (name = "creationDate", nullable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private Long GroupAdmin;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private Set<Post> posts = new HashSet<Post>();

    @Column(name = "deleted")
    private Boolean deleted;

}
