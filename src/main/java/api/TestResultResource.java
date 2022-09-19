package api;

import model.ObjectiveTest;
import model.TestResult;
import model.result.FullResult;
import service.ObjectiveTestService;
import service.TestResultService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
}
