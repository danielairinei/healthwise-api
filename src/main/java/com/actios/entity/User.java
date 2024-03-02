package com.actios.entity;

import com.actios.util.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.type.descriptor.jdbc.UUIDJdbcType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "app_user")
@Data
@EqualsAndHashCode(of = "id")
@RequiredArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phoneNumber", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "itemsDonated")
    private int itemsDonated;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'CLIENT'")
    private UserRole role;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "preference",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "preference_id")
    )
    private List<Preference> preferences;

    public User(UUID id,
                String username,
                String password,
                String email,
                String phoneNumber,
                String firstName,
                String lastName,
                UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.itemsDonated = 0;
        this.role = role;
        this.preferences = new ArrayList<>();
    }
}
