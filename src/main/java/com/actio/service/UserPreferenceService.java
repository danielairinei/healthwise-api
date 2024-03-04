package com.actio.service;

import com.actio.repository.PreferenceRepository;
import com.actio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPreferenceService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PreferenceRepository preferenceRepository;
}
