package org.example.journal.repository;

import org.bson.types.ObjectId;
import org.example.journal.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {


}
