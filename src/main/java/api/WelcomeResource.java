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
    public Map<String,String> welcome(){
        Map<String,String> subject_map = new HashMap<>();
        subjectService.getAllSubject().forEach(d->{
            subject_map.put(d.getName(),d.getSubjectId());
        });
        return subject_map;
    }
}
