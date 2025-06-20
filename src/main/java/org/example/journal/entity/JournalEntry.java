package org.example.journal.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@Document(collection = "journal_entries")
public class JournalEntry {

    @Id
    @NonNull
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private Date date;

}
