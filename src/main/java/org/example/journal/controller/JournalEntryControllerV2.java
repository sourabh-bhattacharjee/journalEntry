package org.example.journal.controller;

import org.bson.types.ObjectId;
import org.example.journal.entity.JournalEntry;
import org.example.journal.entity.Users;
import org.example.journal.service.JournalEntryService;
import org.example.journal.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntriesByUserName(@PathVariable String userName) {
        Users user = userEntryService.findByuserName(userName);
        List<JournalEntry> journalEntries = user.getJournalEntries();
        if (journalEntries != null && !journalEntries.isEmpty()) {
            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{userName}")
    @Transactional // transactional tells spring framework that either this code works together or it will abort.
    public ResponseEntity<JournalEntry> createJournalEntry(@RequestBody JournalEntry journalEntry, @PathVariable String userName) {
        try{
            Users user = userEntryService.findByuserName(userName);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            JournalEntry entry =  journalEntryService.saveEntry(journalEntry);
            List<JournalEntry> journalEntries = user.getJournalEntries();
            if (journalEntries == null) {
                journalEntries = new ArrayList<>();
            }
            journalEntries.add(entry);
            user.setJournalEntries(journalEntries);
            userEntryService.save(user);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        }
        catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/id/{id}")
    public ResponseEntity<?> getJournalEntry(@PathVariable ObjectId id){
        Optional<JournalEntry> JournalEntry = journalEntryService.findById(id);
        if(JournalEntry.isPresent()){
            return new ResponseEntity<>(JournalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{userName}/{id}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId id , @PathVariable String userName){
          journalEntryService.deleteEntry(id,userName);
         return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping("/id/{userName}/{id}")
    public ResponseEntity<?> updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry , @PathVariable String userName ){
         JournalEntry journalEntry1 = journalEntryService.updateEntry(id, journalEntry , userName);
         if(journalEntry1 != null){
             return new ResponseEntity<>(journalEntry1, HttpStatus.OK);
         }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
