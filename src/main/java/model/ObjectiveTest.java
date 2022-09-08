package model;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@Data
public class ObjectiveTest {
    ObjectId id;
    String objectiveTestId;
    String testName;
    ObjectId subject_id;
    String subjId;
    String poster;
    int time;
    List<Question> questions = new ArrayList<>();
}
