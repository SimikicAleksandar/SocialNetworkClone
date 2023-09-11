package com.example.swt_kwt_aleksandar_simikic.Model;

import com.example.swt_kwt_aleksandar_simikic.Model.ReactionType;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reacts")
public class React {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "reactSequenceGenerator")
    @SequenceGenerator(name = "reactSequenceGenerator", sequenceName = "reactSequence", allocationSize = 1)
    private Long id;

    @Column(name = "createdAt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL")
    private Timestamp createdAt;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReactionType type;

    @ManyToOne
    @JoinColumn(name = "fk_comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "fk_post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "fk_reacted_by_id", nullable = false)
    private User reactedBy;
}
