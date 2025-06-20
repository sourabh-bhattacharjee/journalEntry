package org.example.journal.service;

import org.bson.types.ObjectId;
import org.example.journal.entity.JournalEntry;
import org.example.journal.entity.Users;
import org.example.journal.repository.JournalEntryRepository;
import org.example.journal.repository.UserEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserEntryService userEntryService;

    public JournalEntry saveEntry(JournalEntry journalEntry) {
        return journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> findAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }


    public List<JournalEntry> deleteEntry(ObjectId id , String userName) {

        Users user = userEntryService.findByuserName(userName);
        List<JournalEntry> entries = user.getJournalEntries();
        entries.removeIf(journalEntry -> journalEntry.getId().equals(id));
        userEntryService.save(user);
         journalEntryRepository.deleteById(id);
         return journalEntryRepository.findAll();
    }
    public JournalEntry updateEntry(ObjectId id, JournalEntry journalEntry , String userName) {
        Users user = userEntryService.findByuserName(userName);
        List<JournalEntry> entries = user.getJournalEntries();
        boolean ownsEntry = entries.stream()
                .anyMatch(entry -> entry.getId().equals(id));
        if(ownsEntry) {
            Optional<JournalEntry> JournalEntryEntity = journalEntryRepository.findById(id);
            if(JournalEntryEntity.isPresent()) {
                if(journalEntry.getTitle() != null) {
                    JournalEntryEntity.get().setTitle(journalEntry.getTitle());
                }
                if(journalEntry.getContent() != null) {
                    JournalEntryEntity.get().setContent(journalEntry.getContent());
                }
                if(journalEntry.getDate() != null) {
                    JournalEntryEntity.get().setDate(journalEntry.getDate());
                }
                journalEntryRepository.save(JournalEntryEntity.get());
            }
            return journalEntry;
        }
        return null;
    }
}


// controller ---> service ---> repository