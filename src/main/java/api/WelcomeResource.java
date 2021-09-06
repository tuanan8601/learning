package api;

import model.Subject;
import service.SubjectService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class WelcomeResource {
    SubjectService subjectService;
    public WelcomeResource(){
        this.subjectService=new SubjectService();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Subject> welcome(){
        return subjectService.getAllSubject();
    }
}
