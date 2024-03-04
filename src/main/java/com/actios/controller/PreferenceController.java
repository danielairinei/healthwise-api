package com.actios.controller;

import com.actios.entity.Preference;
import com.actios.service.PreferenceService;
import com.actios.util.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = Constants.API_ROOT + "/preference")
public class PreferenceController {

    @Autowired
    private PreferenceService preferenceService;

    @GetMapping()
    public ResponseEntity<List<Preference>> getAllPreferences() {
        List<Preference> preferences = preferenceService.getAllPreferences();
        return new ResponseEntity<>(preferences, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Preference> getPreferenceById(@PathVariable("id") UUID id) {
        Preference preference = preferenceService.getPreferenceById(id);
        return new ResponseEntity<>(preference, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> addPreference(@Valid @RequestBody Preference preference) {
        UUID id = preferenceService.addPreference(preference);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Preference> updatePreference(@Valid @RequestBody Preference preference) {
        Preference updatedPreference = preferenceService.updatePreference(preference);
        return new ResponseEntity<>(updatedPreference, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deletePreference(@PathVariable("id") UUID id) {
        Boolean isDeleted = preferenceService.deletePreferenceById(id);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }
}
