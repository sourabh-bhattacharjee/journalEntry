package org.example.journal.controller;

import org.bson.types.ObjectId;
import org.example.journal.entity.JournalEntry;
import org.example.journal.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAll(){
        return new ResponseEntity<>(journalEntryService.findAll(), HttpStatus.OK);
        //return journalEntryService.findAll();
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createJournalEntry(@RequestBody JournalEntry journalEntry){
        try{
            journalEntryService.saveEntry(journalEntry);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntry(@PathVariable ObjectId id){
        Optional<JournalEntry> JournalEntry = journalEntryService.findById(id);
        if(JournalEntry.isPresent()){
            return new ResponseEntity<>(JournalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId id){
         journalEntryService.deleteEntry(id);
         return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping("/id/{id}")
    public JournalEntry updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry){
        return journalEntryService.updateEntry(id, journalEntry);
    }

}
