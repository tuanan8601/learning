package model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Document {
    long id;
    String docName;
//    ObjectId subject_id;
    long subject_id;
    String link;
}
