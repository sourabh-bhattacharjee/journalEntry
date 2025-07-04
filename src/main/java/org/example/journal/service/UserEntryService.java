package org.example.journal.service;

import org.bson.types.ObjectId;
import org.example.journal.entity.Users;
import org.example.journal.repository.UserEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Component
public class UserEntryService {

    @Autowired
    private UserEntryRepository userEntryRepository;


    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Users> findAll() {
        return userEntryRepository.findAll();
    }

    public void save(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userEntryRepository.save(user);
    }

    public Optional<Users> findById(ObjectId id) {
        return userEntryRepository.findById(id);
    }
    public void delete(ObjectId id) {
        userEntryRepository.deleteById(id);
    }
    public void deleteByUsername(String username) {
        userEntryRepository.deleteByuserName(username);
    }

    public Users findByuserName(String userName) {
        return userEntryRepository.findByuserName(userName);
    }


}


// controller ---> service ---> repository