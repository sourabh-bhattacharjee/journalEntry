package org.example.journal.repository;

import org.bson.types.ObjectId;
import org.example.journal.entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface  UserEntryRepository extends MongoRepository<Users, ObjectId> {

    Users findByuserName(String userName);
    void deleteByuserName(String userName);
}
