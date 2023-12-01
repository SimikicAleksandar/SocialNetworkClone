package rs.ac.uns.ftn.svtvezbe07.model.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "reason")
    private ReportReason reportReason;
    @Column(nullable = false)
    private LocalDate timestamp;
//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "users_username", nullable = false)
//    @Column(name = "by user")
//    private User byUser;

    @Column (name = "accepted")
    private Boolean accepted;
}
