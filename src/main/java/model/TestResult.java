package model;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class TestResult {
    ObjectId id;
    String formId;
    String testName;
    String userId;
    ObjectId uid;
    int score;
    int totalScore;
    int time;
    int dotime;
    Date createdAt;

    List<FormAnswer> formAnswers;
}
