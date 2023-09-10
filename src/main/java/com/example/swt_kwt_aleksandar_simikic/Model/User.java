package com.example.swt_kwt_aleksandar_simikic.Model;

import com.example.swt_kwt_aleksandar_simikic.Dto.CreateUserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "userSequenceGenerator")
    @SequenceGenerator(name = "userSequenceGenerator", sequenceName = "userSequence", allocationSize = 1)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NOW()", nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "lastLogin")
    private LocalDateTime lastLogin;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "profileImage")
    private String profileImagePath;

    @Column(name = "description")
    private String description;

    @Column(name = "displayName")
    private String displayName;

    @Column(name = "role", columnDefinition = "varchar(15) default 'USER'", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @ManyToOne
    @JoinColumn(name = "fk_friendId")
    private User friend ;

    public User(CreateUserDto createUserDto) {
        this.username = createUserDto.getUsername();
        this.firstName = createUserDto.getFirstName();
        this.lastName = createUserDto.getLastName();
        this.email = createUserDto.getEmail();
    }



}
