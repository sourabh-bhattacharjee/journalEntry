package org.example.journal.controller;

import org.bson.types.ObjectId;
import org.example.journal.entity.JournalEntry;
import org.example.journal.entity.Users;
import org.example.journal.service.JournalEntryService;
import org.example.journal.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping
    public ResponseEntity<List<Users>> getAll() {
        return new ResponseEntity<> (userEntryService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Users> create(@RequestBody Users user) {
        userEntryService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> update(@RequestBody Users user , @PathVariable String username) {
        Users userIndb =  userEntryService.findByuserName(username);
        if(userIndb != null) {
            userIndb.setPassword(user.getPassword());
            userIndb.setUserName(user.getUserName());
            userEntryService.save(userIndb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
