package api;

import model.TestResult;
import model.result.FullResult;
import service.TestResultService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/testresult")
public class TestResultResource {
    TestResultService testResultService;
    public TestResultResource(){
        this.testResultService=new TestResultService();
    }

    @Path("/full/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public FullResult getFullResultbyID_api(@PathParam("id") String id){
        FullResult fullResult = testResultService.getFullResultbyID(id);

        return fullResult;
    }

    @Path("/uid/{uid}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<TestResult> getTestResultsbyUid(@PathParam("uid") String uid){
        return testResultService.getTestResultsbyUid(uid);
    }
}
