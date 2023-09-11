package com.example.swt_kwt_aleksandar_simikic.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "postSequenceGenerator")
    @SequenceGenerator(name = "postSequenceGenerator", sequenceName = "postSequence", allocationSize = 1)
    private Long id;

    @Column(name = "createdAt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL")
    private Timestamp createdAt;

    // Nema smisla da postoji ukoliko je content prazan
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "image_path")
    private String image;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", nullable = false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "fk_group_id")
    private Group containedBy;
}
