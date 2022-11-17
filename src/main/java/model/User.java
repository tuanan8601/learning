package model;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@Data
public class User {
    ObjectId id;
    String uid;
    String username;
    String password;
    String displayName;
    String photoURL;
    String email;
    String role;
    Date dateOB;
    String school;
    String phoneNum;
    List<ScheduleItem> schedule;
}
