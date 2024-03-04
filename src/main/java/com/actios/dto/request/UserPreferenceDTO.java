package com.actios.dto.request;

import com.actios.entity.Preference;

import java.util.List;
import java.util.UUID;

public record UserPreferenceDTO(
        UUID userId,
        List<Preference> preferences
) {
}
