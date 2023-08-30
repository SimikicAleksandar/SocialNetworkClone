package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_groups")

public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "userGroupSequenceGenerator")
    @SequenceGenerator(name = "userGroupSequenceGenerator", sequenceName = "userGroupSequence", allocationSize = 1)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NOW()", nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @ManyToOne
    @JoinColumn(name = "fk_userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_groupId", nullable = false)
    private Group group;


    public UserGroup(User user, Group group) {
        this.user = user;
        this.group = group;
    }
}
