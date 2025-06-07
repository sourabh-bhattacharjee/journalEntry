package org.example.journal.controller;

import org.example.journal.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<Long , JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> gerAll(){
        return  new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createJournalEntry(@RequestBody JournalEntry journalEntry){
        journalEntries.put(journalEntry.getId(),journalEntry);
        return true;
    }

    @GetMapping("/id/{id}")
    public  JournalEntry getJournalEntry(@PathVariable Long id){
        return journalEntries.get(id);
    }

    @DeleteMapping("/id/{id}")
    public  JournalEntry deleteJournalEntry(@PathVariable Long id){
        return journalEntries.remove(id);
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateJournalEntry(@PathVariable Long id, @RequestBody JournalEntry journalEntry){
        return journalEntries.put(id,journalEntry);
    }

}
