package model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Document {
    String id;
    String docName;
//    ObjectId subject_id;
    String subject_id;
    String link;
}
