package com.example.swt_kwt_aleksandar_simikic.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "commentSequenceGenerator")
    @SequenceGenerator(name = "commentSequenceGenerator", sequenceName = "commentSequence", allocationSize = 1)
    private Long id;

    @Column(name = "createdAt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL")
    private Timestamp createdAt;


    // Ukoliko je komentar prazan, nema smisla da postoji
    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", nullable = false)
    private User writtenBy;

    @ManyToOne
    @JoinColumn(name = "fk_post_id")
    private Post belongsTo;

    @ManyToOne
    @JoinColumn(name = "fk_comment_id")
    private Comment comment;
}
