package com.example.swt_kwt_aleksandar_simikic.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "postSequenceGenerator")
    @SequenceGenerator(name = "postSequenceGenerator", sequenceName = "postSequence", allocationSize = 1)
    private Long id;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy. hh:mm")
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "imagePath")
    private String image ;


    @ManyToOne
    @JoinColumn(name = "fk_userId", nullable = false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "fk_groupId")
    private Group containedBy;
}
