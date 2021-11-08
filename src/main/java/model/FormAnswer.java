package model;

import lombok.Data;

@Data
public class FormAnswer {
    String qid;
    char answerHead;
    boolean check;
    Question question;

    public boolean getCheck() {
        return check;
    }
}
