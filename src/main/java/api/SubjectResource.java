package api;

import model.Subject;
import service.SubjectService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/subject")
public class SubjectResource {
    SubjectService subjectService;
    public SubjectResource(){
        this.subjectService = new SubjectService();
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Subject getSubjectbyID_api(@PathParam("id") String id){
        Subject subject = subjectService.getSubjectByID(id);
//        subject.setId(null);
//        subject.setObjectiveTest_map(null);
        return subject;
    }

    @POST
    @Path("/add")
    public String addSubject(Subject subject) {
        System.out.println(subject);
        subjectService.addSubject(subject);
        return "";
    }

    @DELETE
    @Path("/{id}")
    public String deleteSubject(@PathParam("id") String subjectId){
        subjectService.deleteSubject(subjectId);
        return "";
    }

    @PUT
    @Path("/{id}")
    public Subject updateSubject(@PathParam("id") String subjectId,Subject subject){
        return subjectService.updateSubject(subjectId,subject);
    }
//    @Path("/search")
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public List<Subject> search(@QueryParam("text") String name){
//        List<Subject> subjects = subjectService.searchSubject(name,0,0);
//        subjects.forEach(d->{
//            d.setId(null);
//            d.setObjectiveTest_map(null);
//        });
//        return subjects;
//    }

    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Subject> all(){
        List<Subject> subjects = subjectService.getAllSubject();
//        subjects.forEach(d->{
//            d.setId(null);
//            d.setObjectiveTest_map(null);
//        });
        return subjects;
    }

    @Path("/search")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Subject> searchSubject(@QueryParam("text") String text){
        return subjectService.searchSubject(text);
    }
}
