package service;

import DAO.RedisDB.CommentDAO;
import model.Comment;

import java.util.List;

public class CommentService {
    public CommentDAO commentDAO;
    public CommentService(){
        this.commentDAO = new CommentDAO();
    }
    public List<Comment> getComments(String objectiveTest_id) {
        List<Comment> list = commentDAO.getComments(objectiveTest_id);
        return list;
    }

    public String addComment(Comment comment) {
        commentDAO.addComment(comment);
        return "";
    }
}
