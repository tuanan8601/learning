package service;

import DAO.RedisDB.CommentDAO;
import model.Comment;
import org.bson.Document;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

public class CommentService {
    public List<Comment> getComments(String objectiveTest_id) {
        List<Comment> list = new CommentDAO().getComments(objectiveTest_id);
        return list;
    }

    public String addComment(Comment comment) {
        new CommentDAO().addComment(comment);
        return "";
    }
}
