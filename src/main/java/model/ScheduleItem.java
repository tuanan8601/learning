package model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class ScheduleItem {
    int weekday;
    int shift;
    String subject;
    String note;
}
