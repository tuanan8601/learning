package model;

import lombok.Data;

@Data
public class FormAnswer {
    String qid;
    char answerHead;
    boolean check;
    Question question;
    int time;
    int dotime;

    public boolean getCheck() {
        return check;
    }
}
