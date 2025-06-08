package org.example.journal.controller;

import org.bson.types.ObjectId;
import org.example.journal.entity.JournalEntry;
import org.example.journal.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.findAll();
    }

    @PostMapping
    public boolean createJournalEntry(@RequestBody JournalEntry journalEntry){
        journalEntryService.saveEntry(journalEntry);
        return true;
    }

    @GetMapping("/id/{id}")
    public JournalEntry getJournalEntry(@PathVariable ObjectId id){
        return journalEntryService.findById(id);
    }

    @DeleteMapping("/id/{id}")
    public List<JournalEntry> deleteJournalEntry(@PathVariable ObjectId id){
        return journalEntryService.deleteEntry(id);
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry){
        return journalEntryService.updateEntry(id, journalEntry);
    }

}
