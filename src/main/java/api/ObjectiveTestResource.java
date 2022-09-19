package api;

import model.Comment;
import model.FormAnswer;
import model.ObjectiveTest;
import model.TestResult;
import org.bson.types.ObjectId;
import service.ObjectiveTestService;
import service.TestResultService;

import javax.ws.rs.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
    public ObjectiveTest getObjectiveTestbyID_api(@PathParam("id") String id){
        ObjectiveTest objectiveTest = objectiveTestService.getObjectiveTestByID(id);
//        objectiveTest.setId(null);
//        objectiveTest.setSubject_id(null);
        return objectiveTest;
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
        testResult.setObjTestId(new ObjectId(testResult.getTestId()));
        testResult.setCreatedAt(new Date());
        System.out.println(testResult.toString());
        (new TestResultService()).addTestResult(testResult);
        System.out.println("Again: "+testResult.toString());
        return testResult;
    }
}
