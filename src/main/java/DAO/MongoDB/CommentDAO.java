package DAO.MongoDB;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import model.Comment;
import model.Subject;
import org.bson.Document;
import org.bson.types.ObjectId;
//import redis.clients.jedis.Tuple;

import java.util.*;

public class CommentDAO extends AbsDAO{
    public List<Comment> getComments(String objectiveTest_id) {
        MongoCollection<Comment> comments = getDB().getCollection("comments", Comment.class);
        Iterator<Comment> list = comments.find(new Document("objectiveTestId",new ObjectId(objectiveTest_id)))
                .sort(new Document("date",1)).iterator();
        List<Comment> listComments = new ArrayList<>();
        while (list.hasNext()) {
            listComments.add(list.next());
        }
        return listComments;
    }

    public Comment getCommentbyID(String objectiveTest_id) {
        MongoCollection<Comment> comments = getDB().getCollection("comments", Comment.class);
        Comment comment = comments.find(new Document("_id",new ObjectId(objectiveTest_id))).first();
        return comment;
    }

    public void addComment(Comment comment) {
        MongoCollection<Comment> comments = getDB().getCollection("comments", Comment.class);
        comment.setDate(new Date());
        comments.insertOne(comment);
    }

    public static void main(String[] args) {
        System.out.println(new CommentDAO().getComments("630aca6d3b38422a8e6c0f85"));
        System.out.println(new CommentDAO().getCommentbyID("6319c4e0cdf936365302ccde"));
//        Comment comment = new Comment();
//        comment.setEmail("tuanan8601@gmail.com");
//        comment.setName("Tuấn An Nguyễn");
//        comment.setText("Cảm ơn mọi người");
//        comment.setObjectiveTestId(new ObjectId("630aca6d3b38422a8e6c0f85"));
//        System.out.println(comment);
//        new CommentDAO().addComment(comment);
    }
}
