package api;

import model.*;
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

    @Path("/{uid}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public User getUserbyId(@PathParam("uid") String uid){
        return userService.getUserbyId(uid);
    }

    @Path("username/{username}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public User findUserbyUsername(@PathParam("username") String username){
        return userService.findUserbyUsername(username);
    }

    @POST
    @Path("/add")
    @Produces({MediaType.TEXT_PLAIN})
    public String addUser(User user) {
        userService.addUser(user);
        return user.getUid();
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
