package model;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class TestResult {
    ObjectId id;
    String formId;
    ObjectId objTestId;
    String testId;
    String testName;
    int score;
    int totalScore;
    int time;
    int dotime;

    List<FormAnswer> formAnswers;
}
