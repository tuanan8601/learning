package api;

import model.Chapter;
import model.TestResult;
import org.bson.types.ObjectId;
import service.ObjectiveTestService;
import service.TestResultService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

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

//    @Path("/random")
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public ObjectiveTest getObjectiveTestbyID_api(@QueryParam("id") String id,@QueryParam("num")String num){
//        ObjectiveTest objectiveTest = objectiveTestService.getRandomQuestions(id,Integer.parseInt(num));
////        objectiveTest.setId(null);
////        objectiveTest.setSubject_id(null);
//        return objectiveTest;
//    }

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
}
