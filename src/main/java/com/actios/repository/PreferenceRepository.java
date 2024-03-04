package com.actios.repository;

import com.actios.entity.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PreferenceRepository extends JpaRepository<Preference, UUID> {
}
