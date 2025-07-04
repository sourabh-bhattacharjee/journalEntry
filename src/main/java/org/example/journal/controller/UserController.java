package org.example.journal.controller;

import org.example.journal.entity.Users;
import org.example.journal.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserEntryService userEntryService;

//    @GetMapping
//    public ResponseEntity<List<Users>> getAll() {
//        return new ResponseEntity<> (userEntryService.findAll(), HttpStatus.OK);
//    }


    @PutMapping
    public ResponseEntity<?> update(@RequestBody Users user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Users userIndb =  userEntryService.findByuserName(username);
        if(userIndb != null) {
            userIndb.setPassword(user.getPassword());
            userIndb.setUserName(user.getUserName());
            userEntryService.save(userIndb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Users userIndb = userEntryService.findByuserName(username);
        if(userIndb != null) {
            userEntryService.deleteByUsername(username);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
