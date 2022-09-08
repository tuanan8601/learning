package model;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@Data
public class Question {
    int id;
    String title;
    String image;
    List<Answer> answers = new ArrayList<>();
    String solution;
    char solutionHead;
    String feedback;
}