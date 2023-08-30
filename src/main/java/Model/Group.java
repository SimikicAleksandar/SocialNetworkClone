package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "groupSequenceGenerator")
    @SequenceGenerator(name = "groupSequenceGenerator", sequenceName = "groupSequence", allocationSize = 1)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NOW()", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "suspended", nullable = false)
    private boolean suspended = false;

    @Column(name = "suspensionReason")
    private String suspensionReason;

    @ManyToOne
    @JoinColumn(name = "fk_userId", nullable = false)
    private User creatorGroup;

}
