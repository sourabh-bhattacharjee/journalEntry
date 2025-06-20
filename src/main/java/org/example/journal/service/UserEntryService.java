package org.example.journal.service;

import org.bson.types.ObjectId;
import org.example.journal.entity.Users;
import org.example.journal.repository.UserEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class UserEntryService {

    @Autowired
    private UserEntryRepository userEntryRepository;

    public List<Users> findAll() {
        return userEntryRepository.findAll();
    }

    public void save(Users user) {
        userEntryRepository.save(user);
    }

    public Optional<Users> findById(ObjectId id) {
        return userEntryRepository.findById(id);
    }
    public void delete(ObjectId id) {
        userEntryRepository.deleteById(id);
    }

    public Users findByuserName(String userName) {
        return userEntryRepository.findByuserName(userName);
    }


}


// controller ---> service ---> repository