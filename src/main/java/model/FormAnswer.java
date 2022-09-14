package model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class FormAnswer {
    String qid;
    char answerHead;
    boolean check;

    public boolean getCheck() {
        return check;
    }
}
