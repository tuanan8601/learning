package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Question {
    String title;
    List<Answer> answers;
    String solution;
    char solutionHead;
    String feedback;
}