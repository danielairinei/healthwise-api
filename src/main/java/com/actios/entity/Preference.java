package com.actios.entity;

import com.actios.util.enums.PreferenceType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Preference implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private PreferenceType type;

    @Column(nullable = false)
    private int weight;

    @JsonIgnore
    @ManyToMany(mappedBy = "preferences", fetch = FetchType.EAGER)
    private List<User> users;
}
