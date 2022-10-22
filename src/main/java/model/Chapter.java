package model;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@Data
public class Chapter {
    ObjectId id;
    String chapterId;
    String testName;
    ObjectId subject_id;
    String subjId;
    String poster;
    List<Question> questions = new ArrayList<>();
}
