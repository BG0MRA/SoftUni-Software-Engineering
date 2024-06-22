package com.dictionaryapp.service;

import com.dictionaryapp.config.UserSession;
import com.dictionaryapp.model.dto.UserLoginDTO;
import com.dictionaryapp.model.dto.UserRegisterDTO;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserSession userSession;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserSession userSession) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userSession = userSession;
    }

    public boolean register(UserRegisterDTO data) {
        if (userRepository.existsByUsername(data.getUsername())) {
            return false;
        }

        boolean isUsernameOrEmailTaken = userRepository.findByUsernameOrEmail(data.getUsername(), data.getEmail()).isPresent();

        if (isUsernameOrEmailTaken) {
            return false;
        }

        if (!data.getPassword().equals(data.getConfirmPassword())) {
            return false;
        }


        User user = modelMapper.map(data, User.class);
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        userRepository.save(user);

        return true;
    }

    public boolean login(UserLoginDTO data) {
        Optional<User> userOptional = userRepository.findByUsername(data.getUsername());

        if (userOptional.isEmpty()) {
            return false;
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(data.getPassword(), user.getPassword())) {
            return false;
        }

        userSession.login(user);


        return true;
    }
}
