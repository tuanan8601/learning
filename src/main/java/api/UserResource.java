package api;

import model.ScheduleItem;
import model.Subject;
import model.TestResult;
import model.User;
import service.SubjectService;
import service.TestResultService;
import service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

@Path("/user")
public class UserResource {
    UserService userService;
    public UserResource(){
        this.userService = new UserService();
    }

    @Path("/schedule/{uid}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<ScheduleItem> getAllSchedules(@PathParam("uid") String uid){
        return userService.getAllSchedules(uid);
    }

    @Path("/schedule/{uid}/weekday/{wd}/shift/{sh}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ScheduleItem getSchedulebyDayandShift(@PathParam("uid") String uid,@PathParam("wd") int weekday,@PathParam("sh") int shift){
        return userService.getSchedulebyWeekdayandShift(uid,weekday,shift);
    }

    @PUT
    @Path("/schedule/{uid}")
    @Produces({MediaType.TEXT_PLAIN})
    public String updateSchedule(@PathParam("uid") String userId, ScheduleItem scheduleItem) {
        userService.updateSchedule(userId,scheduleItem);
        return "Sucess!";
    }
}
