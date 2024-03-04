package com.actios.service;

import com.actios.entity.Preference;
import com.actios.repository.PreferenceRepository;
import com.actios.util.ServiceUtils;
import com.actios.util.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PreferenceService {

    @Autowired
    private PreferenceRepository preferenceRepository;

    public List<Preference> getAllPreferences() {
        return preferenceRepository.findAll();
    }

    public Preference getPreferenceById(UUID id) {
        Optional<Preference> preference = preferenceRepository.findById(id);

        if (preference.isEmpty()) {
            throw new ResourceNotFoundException(Preference.class.getSimpleName() + " with id: " + id);
        }
        return preference.get();
    }

    public UUID addPreference(Preference preference) {
        preferenceRepository.save(preference);
        return preference.getId();
    }

    public Preference updatePreference(Preference preference) {
        Optional<Preference> existingPreferenceOptional = preferenceRepository.findById(preference.getId());

        if (existingPreferenceOptional.isPresent()) {
            Preference existingPreference = existingPreferenceOptional.get();
            ServiceUtils.updateFields(existingPreference, preference);
            return preferenceRepository.save(existingPreference);
        } else {
            throw new ResourceNotFoundException(Preference.class.getSimpleName() + " with id: " + preference.getId());
        }
    }

    public Boolean deletePreferenceById(UUID id) {
        if (!preferenceRepository.existsById(id)) {
            throw new UserNotFoundException(Preference.class.getSimpleName() + " with ID " + id + " not found");
        }
        preferenceRepository.deleteById(id);
        return true;
    }
}
