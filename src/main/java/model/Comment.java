package model;

import lombok.Data;
import org.bson.types.ObjectId;
import utils.MyTime;

import java.util.Date;

@Data
public class Comment {
//    private ObjectId id;
    String id;
    private String name;
    private String email;
    private String text;
//    private ObjectId objectiveTest_id;
    String objectiveTest_id;
    private Date date;
    private String photoURL;
    public String getTimeAgo() {
        return MyTime.timeAgo(new Date(), date);
    }
}