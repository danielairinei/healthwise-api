package com.pula.dto.request;

import com.pula.entity.Preference;

import java.util.List;
import java.util.UUID;

public record UserPreferenceDTO(
        UUID userId,
        List<Preference> preferences
) {
}
