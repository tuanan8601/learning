package api;

import model.ObjectiveTest;
import service.ObjectiveTestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/objectivetest")
public class ObjectiveTestResource {
    ObjectiveTestService objectiveTestService;
    public ObjectiveTestResource(){
        this.objectiveTestService=new ObjectiveTestService();
    }
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ObjectiveTest getObjectiveTestbyID_api(@QueryParam("id") String id){
        ObjectiveTest objectiveTest = objectiveTestService.getObjectiveTestByID(id);
        objectiveTest.setId(null);
        objectiveTest.setSubject_id(null);
        return objectiveTest;
    }
    @Path("/hello")
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
}
