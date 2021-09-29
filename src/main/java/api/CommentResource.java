package api;

import model.Comment;
import model.ObjectiveTest;
import service.CommentService;
import service.ObjectiveTestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/objectivetest/comment")
public class CommentResource {
    CommentService commentService;
    public CommentResource(){
        this.commentService=new CommentService();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Comment> getComments(@QueryParam("id") String id){
        List<Comment> comments = commentService.getComments(id);
        return comments;
    }
}
