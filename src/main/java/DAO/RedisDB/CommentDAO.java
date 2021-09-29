package DAO.RedisDB;

import model.Comment;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.*;

public class CommentDAO extends AbsDAO {
    public static long getMaxIndex(String objectname, Jedis jedis){
        HashSet<String> list = (HashSet<String>) jedis.keys(objectname+":*");
        List<Long> index = new ArrayList<>();
        list.forEach(d->{
            int length = d.split(":").length;
            index.add(Long.parseLong(d.split(":")[length-1]));
        });
        index.add(0L);
        return Collections.max(index);
    }
    public List<Comment> getComments(String objectiveTest_id) {
        List<Comment> list = new ArrayList<>();
        List<Tuple> comments = jedis.zscan("commentzset:objectivetest:"+objectiveTest_id,0).getResult();
        Collections.reverse(comments);
        comments.forEach(cz->{
            List<Map.Entry<String, String>> commentmap=jedis.hscan("comment:"+cz.getElement(),0).getResult();
            Comment comment = new Comment();
            commentmap.forEach(c->{
                if(c.getKey().equals("id")) comment.setId(c.getValue());
                if(c.getKey().equals("name")) comment.setName(c.getValue());
                if(c.getKey().equals("email")) comment.setEmail(c.getValue());
                if(c.getKey().equals("text")) comment.setText(c.getValue());
                if(c.getKey().equals("objectiveTest_id")) comment.setObjectiveTest_id(c.getValue());
                if(c.getKey().equals("date")) comment.setDate(new Date(Long.parseLong(c.getValue())));
            });
            list.add(comment);
        });
        return list;
    }

    public void addComment(Comment comment) {
        comment.setId(CommentDAO.getMaxIndex("comment",jedis)+1+"");

        Map<String,String> map = new HashMap<>();
        map.put("id",String.valueOf(comment.getId()));
        map.put("name",comment.getName());
        map.put("email",comment.getEmail());
        map.put("text",comment.getText());
        map.put("objectiveTest_id",String.valueOf(comment.getObjectiveTest_id()));
        map.put("date",String.valueOf(new Date().getTime()));
        System.out.println(map);
        jedis.hmset("comment:"+comment.getId(),map);
        jedis.zadd("commentzset:objectivetest:"+comment.getObjectiveTest_id(),comment.getDate().getTime(),""+comment.getId());
    }

    public static void main(String[] args) {
        System.out.println(new CommentDAO().getComments("4"));

//        Comment comment = new Comment();
//        comment.setEmail("tuanan8601@gmail.com");
//        comment.setName("Tuấn An Nguyễn");
//        comment.setText("T không làm được Câu 4 :(");
//        comment.setObjectiveTest_id("4");
//        System.out.println(comment);
//        new CommentDAO().addComment(comment);
    }
}
