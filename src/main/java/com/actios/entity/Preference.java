package com.actios.entity;

import com.actios.util.enums.PreferenceType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@RequiredArgsConstructor
public class Preference implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "type", nullable = false, unique = true)
    private PreferenceType type;

    @Column(name = "weight", nullable = false)
    private int weight;

    @ManyToMany(mappedBy = "preferences")
    private List<User> users;

    public Preference(UUID id, PreferenceType type, int weight) {
        this.id = id;
        this.type = type;
        this.weight = weight;
    }
}
