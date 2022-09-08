package api;

import model.Comment;
import service.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/comment")
public class CommentResource {
    CommentService commentService;
    public CommentResource(){
        this.commentService=new CommentService();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Comment> getComments(@QueryParam("id") String id){
        return commentService.getComments(id);
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Comment getCommentbyId(@PathParam("id") String id){
        Comment comment = commentService.getCommentbyID(id);
        return comment;
    }

    @POST
    @Path("/add")
    public String addComment(Comment comment) {
        commentService.addComment(comment);
        return "";
    }
}
