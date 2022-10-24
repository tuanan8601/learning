package service;

import DAO.MongoDB.SubjectDAO;
import DAO.MongoDB.UserDAO;
import model.Chapter;
import model.ScheduleItem;
import model.User;

import java.util.List;

public class UserService {
    public UserDAO userDAO;
    public UserService(){
        this.userDAO = new UserDAO();
    }

    public List<ScheduleItem> getAllSchedules(String userId) {
        return userDAO.getAllSchedules(userId);
    }

    public User updateSchedule(String userId, ScheduleItem scheduleItem){
        return userDAO.updateSchedule(userId,scheduleItem);
    }
}
