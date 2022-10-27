package DAO.MongoDB;

import com.mongodb.client.MongoCollection;
import model.Chapter;
import model.ScheduleItem;
import model.TestResult;
import model.User;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class UserDAO extends AbsDAO {
    public List<ScheduleItem> getAllSchedules(String userId) {
        MongoCollection<User> users = getDB().getCollection("users", User.class);
        User user = users.find(eq("_id", new ObjectId(userId))).first();
        return user.getSchedule();
    }

    public User updateSchedule(String userId, ScheduleItem scheduleItem) {
        boolean hasSchedule = false;
        MongoCollection<User> users = getDB().getCollection("users", User.class);
        User user = users.find(eq("_id", new ObjectId(userId))).first();
        List<ScheduleItem> scheduleItemList = user.getSchedule();

        if (user != null) {
            if (scheduleItem.getSubject().trim().equals("")&&scheduleItem.getNote().trim().equals("")){
                if(scheduleItemList!=null){
                    for (Iterator<ScheduleItem> iterator = scheduleItemList.iterator(); iterator.hasNext(); ) {
                        ScheduleItem item = iterator.next();
                        if (item.getWeekday() == scheduleItem.getWeekday() && item.getShift() == scheduleItem.getShift()) {
                            iterator.remove();
                        }
                    }
                }
            }
            else {
                if (scheduleItemList == null) {
                    scheduleItemList = new ArrayList<>();
                    scheduleItemList.add(scheduleItem);
                    user.setSchedule(scheduleItemList);
                } else {
                    for (ScheduleItem item : scheduleItemList) {
                        if (item.getWeekday() == scheduleItem.getWeekday() && item.getShift() == scheduleItem.getShift()) {
                            int index = scheduleItemList.indexOf(item);
                            scheduleItemList.set(index, scheduleItem);
                            hasSchedule = true;
                        }
                    }
                    if (!hasSchedule) scheduleItemList.add(scheduleItem);
                }
            }
        }

        System.out.println(scheduleItemList);
        users.replaceOne(eq("_id", new ObjectId(userId)), user);
        return user;
    }

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
//        ScheduleItem scheduleItem = new ScheduleItem();
//        scheduleItem.setWeekday(1);
//        scheduleItem.setShift(4);
//        scheduleItem.setSubjectId(new ObjectId("613445960dba7b0a99ec262c"));
//        scheduleItem.setNote("An go 12332!");
//        userDAO.updateSchedule("6355e9f711868e94a5efcfb0",scheduleItem);
    }

    public ScheduleItem getSchedulebyWeekdayandShift(String uid, int weekday, int shift) {
        ScheduleItem scheduleItem = null;
        MongoCollection<User> users = getDB().getCollection("users", User.class);
        User user = users.find(eq("_id", new ObjectId(uid))).first();
        List<ScheduleItem> scheduleItemList = user.getSchedule();
        if (user != null) {
            if (scheduleItemList != null) {
                for (ScheduleItem item : scheduleItemList) {
                    if (item.getWeekday() == weekday && item.getShift() == shift) {
                        scheduleItem = item;
                    }
                }
            }
        }
        return scheduleItem;
    }
}
