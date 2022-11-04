package DAO.MongoDB;

import com.mongodb.client.MongoCollection;
import model.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
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

    public User getUserbyId(String uid) {
        MongoCollection<User> users = getDB().getCollection("users", User.class);
        User user = users.find(eq("_id", new ObjectId(uid))).first();
        if(user!=null) {
            user.setUid(user.getId().toString());
            user.setSchedule(null);
        }
//        System.out.println(user);
        return user;
    }

    public User findUserbyUsername(String username) {
        MongoCollection<User> users = getDB().getCollection("users", User.class);
        User user = users.find(eq("username", username)).first();
        if(user!=null) {
            user.setUid(user.getId().toString());
            user.setSchedule(null);
        }
//        System.out.println(user);
        return user;
    }

    public void addUser(User user) {
        MongoCollection<User> users = getDB().getCollection("users", User.class);
        System.out.println(user);
        users.insertOne(user);
        user.setUid(user.getId().toString());
    }

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
//        System.out.println(userDAO.getUserbyId("6355e9f711868e94a5efcfb0"));
//        System.out.println(userDAO.findUserbyUsername("tuanan863a3"));
//        ScheduleItem scheduleItem = new ScheduleItem();
//        scheduleItem.setWeekday(1);
//        scheduleItem.setShift(4);
//        scheduleItem.setSubjectId(new ObjectId("613445960dba7b0a99ec262c"));
//        scheduleItem.setNote("An go 12332!");
//        userDAO.updateSchedule("6355e9f711868e94a5efcfb0",scheduleItem);

    }

    public void editUser(String uid, User user) {
        MongoCollection<User> users = getDB().getCollection("users", User.class);
        user.setId(new ObjectId(user.getUid()));
        users.replaceOne(eq("_id", new ObjectId(uid)), user);
    }
}
