package org.example.journal.service;

import org.bson.types.ObjectId;
import org.example.journal.entity.JournalEntry;
import org.example.journal.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> findAll() {
        return journalEntryRepository.findAll();
    }

    public JournalEntry findById(ObjectId id) {
        return journalEntryRepository.findById(id).orElse(null);
    }
    public List<JournalEntry> deleteEntry(ObjectId id) {
         journalEntryRepository.deleteById(id);
         return journalEntryRepository.findAll();
    }
    public JournalEntry updateEntry(ObjectId id, JournalEntry journalEntry) {
        return journalEntryRepository.save(journalEntry);
    }

}


// controller ---> service ---> repository