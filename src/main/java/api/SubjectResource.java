package api;
import model.Subject;
import service.SubjectService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/subject")
public class SubjectResource {
    SubjectService subjectService;
    public SubjectResource(){
        this.subjectService = new SubjectService();
    }
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Subject getSubjectbyID_api(@QueryParam("id") String id){
        Subject subject = subjectService.getSubjectByID(id);
//        subject.setId(null);
//        subject.setObjectiveTest_map(null);
        return subject;
    }
}
