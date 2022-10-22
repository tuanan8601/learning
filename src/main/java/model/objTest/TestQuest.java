package model.objTest;

import lombok.Data;
import model.Question;
import org.bson.types.ObjectId;

@Data
public class TestQuest {
    ObjectId id;
    String chapterId;
    Question questions;
    Question question;
}
