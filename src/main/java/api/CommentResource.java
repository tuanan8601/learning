package api;

import model.Comment;
import model.ObjectiveTest;
import service.CommentService;
import service.ObjectiveTestService;

import javax.ws.rs.*;
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

    @POST
    @Path("/add")
    public String addComment(Comment comment) {
        commentService.addComment(comment);
        return "";
    }
}
