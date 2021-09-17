package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Question {
    String id;
    String title;
    String image;
    List<Answer> answers = new ArrayList<>();
    String solution;
    char solutionHead;
    String feedback;
}