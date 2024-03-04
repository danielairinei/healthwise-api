package com.actios.repository;

import com.actios.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    /*@Query("SELECT m FROM Measurement m " +
            "WHERE m.deviceId = :deviceId " +
            "AND m.timestamp BETWEEN :startTimestamp AND :endTimestamp")
    List<Measurement> findByDeviceIdAndTimestampBetween(
            @Param("deviceId") String deviceId,
            @Param("startTimestamp") long startTimestamp,
            @Param("endTimestamp") long endTimestamp
    );*/
}
