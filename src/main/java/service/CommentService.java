package service;

import DAO.MongoDB.CommentDAO;
import model.Comment;

import java.util.List;

public class CommentService {
    public CommentDAO commentDAO;
    public CommentService(){
        this.commentDAO = new CommentDAO();
    }
    public List<Comment> getComments(String objectiveTest_id) {
        return commentDAO.getComments(objectiveTest_id);
    }

    public Comment getCommentbyID(String objectiveTest_id) {
        return commentDAO.getCommentbyID(objectiveTest_id);
    }


    public String addComment(Comment comment) {
        commentDAO.addComment(comment);
        return "";
    }
}
