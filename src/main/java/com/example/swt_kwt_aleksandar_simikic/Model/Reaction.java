package com.example.swt_kwt_aleksandar_simikic.Model;

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
@Table(name = "reacts")
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "reactSequenceGenerator")
    @SequenceGenerator(name = "reactSequenceGenerator", sequenceName = "reactSequence", allocationSize = 1)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NOW()", nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReactionType type;

    @ManyToOne
    @JoinColumn(name = "fk_commentId")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "fk_postId")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "fk_reactedById", nullable = false)
    private User reactedBy;
}
