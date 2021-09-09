package api;

import model.Subject;
import service.SubjectService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/")
public class WelcomeResource {
    SubjectService subjectService;
    public WelcomeResource(){
        this.subjectService=new SubjectService();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Subject> welcome(){
        List<Subject> subjects = subjectService.getAllSubject();
        subjects.forEach(d->{
            d.setId(null);
            d.setObjectiveTest_map(null);
        });
        return subjects;
    }
}
