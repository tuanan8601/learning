package api;

import model.Subject;
import service.SubjectService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/home")
public class HomeResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Welcome, Learning4Student!";
    }
}
