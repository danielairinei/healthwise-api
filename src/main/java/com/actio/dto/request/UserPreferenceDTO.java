package com.actio.dto.request;

import com.actio.entity.Preference;

import java.util.List;
import java.util.UUID;

public record UserPreferenceDTO(
        UUID userId,
        List<Preference> preferences
) {
}
