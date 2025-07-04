package org.example.journal.controller;

import org.example.journal.entity.Users;
import org.example.journal.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserEntryService userEntryService;

    @PostMapping("/user")
    public ResponseEntity<Users> create(@RequestBody Users user) {
        userEntryService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
