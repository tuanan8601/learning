package model.result;

import lombok.Data;
import model.Answer;
import model.FormAnswer;
import model.Question;

import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;

@Data
public class Result {
    Question question;
    FormAnswer formAnswer;
}
