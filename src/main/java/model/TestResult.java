package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class TestResult {
    String testName;
    int score;
    int totalScore;
    int time;
    int dotime;
    List<FormAnswer> formAnswerList = new ArrayList<>();
}
