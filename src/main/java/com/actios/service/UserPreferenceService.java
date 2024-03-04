package com.actios.service;

import com.actios.entity.Preference;
import com.actios.repository.PreferenceRepository;
import com.actios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPreferenceService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PreferenceRepository preferenceRepository;
}
