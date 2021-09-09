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
    String subjectId;
    String poster;
    List<Question> questions;
}
