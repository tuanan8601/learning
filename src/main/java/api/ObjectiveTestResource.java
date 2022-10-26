package api;

import model.Chapter;
import model.TestResult;
import model.objTest.ObjectiveTest;
import org.bson.types.ObjectId;
import service.ObjectiveTestService;
import service.TestResultService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

@Path("/objectivetest")
public class ObjectiveTestResource {
    ObjectiveTestService objectiveTestService;
    public ObjectiveTestResource(){
        this.objectiveTestService=new ObjectiveTestService();
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Chapter getObjectiveTestbyID_api(@PathParam("id") String id){
        Chapter chapter = objectiveTestService.getObjectiveTestByID(id);
//        objectiveTest.setId(null);
//        objectiveTest.setSubject_id(null);
        return chapter;
    }

    @Path("subjecttest/{subjectId}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ObjectiveTest getSubjectTest (@PathParam("subjectId") String id, @QueryParam("time") int time, @QueryParam("questperchap") int qpchap){
        ObjectiveTest objectiveTest = objectiveTestService.getSubjectTest(id,time,qpchap);
        return objectiveTest;
    }

    @Path("chaptertest/{chapterId}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ObjectiveTest getChapterTest (@PathParam("chapterId") String id, @QueryParam("time") int time, @QueryParam("num") int num){
        ObjectiveTest objectiveTest = objectiveTestService.getChapterTest(id,time,num);;
        return objectiveTest;
    }

    @POST
    @Path("/check")
    @Produces(MediaType.APPLICATION_JSON)
    public TestResult checkTest(TestResult testResult) {
        testResult = objectiveTestService.checkObjectiveTest(testResult);
        testResult.setCreatedAt(new Date());
        System.out.println(testResult.toString());
        (new TestResultService()).addTestResult(testResult);
        System.out.println("Again: "+testResult.toString());
        return testResult;
    }

    @Path("subject/{subjid}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Chapter> getChapterbySubjectId(@PathParam("subjid") String subjid){
        return objectiveTestService.getChapterbySubjectId(subjid);
    }
}
