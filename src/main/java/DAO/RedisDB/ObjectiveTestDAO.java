package DAO.RedisDB;

import DAO.RedisDB.AbsDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.lang.Nullable;
import model.Answer;
import model.ObjectiveTest;
import model.Question;
import org.bson.types.ObjectId;
import redis.clients.jedis.Jedis;

import java.util.*;
import static com.mongodb.client.model.Filters.eq;

public class ObjectiveTestDAO extends AbsDAO {
    public ObjectiveTest getObjectiveTestByID(String id) {
        List<Map.Entry<String,String>> oTmap = jedis.hscan("objectivetest:"+id,0).getResult();
        ObjectiveTest objectiveTest = new ObjectiveTest();
        oTmap.forEach(e->{
            if(e.getKey().equals("id")) objectiveTest.setObjectiveTestId(e.getValue());
            if(e.getKey().equals("testname")) objectiveTest.setTestName(e.getValue());
            if(e.getKey().equals("poster")) objectiveTest.setPoster(e.getValue());
            if(e.getKey().equals("subject_id")) objectiveTest.setSubjectId(e.getValue());
        });
        List<String> qlist = jedis.sscan("questionset:objectivetest:"+id,0).getResult();
        for (String q_id : qlist) {
            List<Map.Entry<String,String>> qmap = jedis.hscan("question:"+q_id,0).getResult();
            Question question = new Question();
            question.setId(q_id);
            qmap.forEach(qe->{
                if(qe.getKey().equals("title")) question.setTitle(qe.getValue());
                if(qe.getKey().equals("image")) question.setImage(qe.getValue());
                if(qe.getKey().equals("solution")) question.setSolution(qe.getValue());
                if(qe.getKey().equals("solutionHead")) question.setSolutionHead(qe.getValue().charAt(0));
                if(qe.getKey().equals("feedback")) question.setFeedback(qe.getValue());
            });
            long len = jedis.llen("answer:question:"+q_id);
            List<String> answers = jedis.lrange("answer:question:"+q_id,0,len-1);
            Collections.reverse(answers);
            answers.forEach(d->{
                Answer answer = new Answer();
                answer.setAnswer(d);
                answer.setAnswerHead(d.charAt(0));
                question.getAnswers().add(answer);
            });
            objectiveTest.getQuestions().add(question);
        }

        return objectiveTest;
    }

    public ObjectiveTest getRandomQuestions(String id, @Nullable int num) {
        num = Optional.ofNullable(num).orElse(5);
        List<Map.Entry<String,String>> oTmap = jedis.hscan("objectivetest:"+id,0).getResult();
        ObjectiveTest objectiveTest = new ObjectiveTest();
        oTmap.forEach(e->{
            if(e.getKey().equals("id")) objectiveTest.setObjectiveTestId(e.getValue());
            if(e.getKey().equals("testname")) objectiveTest.setTestName(e.getValue());
            if(e.getKey().equals("poster")) objectiveTest.setPoster(e.getValue());
            if(e.getKey().equals("subject_id")) objectiveTest.setSubjectId(e.getValue());
        });
        List<String> qlist = jedis.srandmember("questionset:objectivetest:"+id,num);
        for (String q_id : qlist) {
            List<Map.Entry<String,String>> qmap = jedis.hscan("question:"+q_id,0).getResult();
            Question question = new Question();
            question.setId(q_id);
            qmap.forEach(qe->{
                if(qe.getKey().equals("title")) question.setTitle(qe.getValue());
                if(qe.getKey().equals("image")) question.setImage(qe.getValue());
                if(qe.getKey().equals("solution")) question.setSolution(qe.getValue());
                if(qe.getKey().equals("solutionHead")) question.setSolutionHead(qe.getValue().charAt(0));
                if(qe.getKey().equals("feedback")) question.setFeedback(qe.getValue());
            });
            long len = jedis.llen("answer:question:"+q_id);
            List<String> answers = jedis.lrange("answer:question:"+q_id,0,len-1);
            Collections.reverse(answers);
            answers.forEach(d->{
                Answer answer = new Answer();
                answer.setAnswer(d);
                answer.setAnswerHead(d.charAt(0));
                question.getAnswers().add(answer);
            });
            objectiveTest.getQuestions().add(question);
        }

        return objectiveTest;
    }

    public List<ObjectiveTest> searchObjectiveTest(Map filter, Map sort, int limit, int skip) {
        return null;
    }

    public long getObjectiveTestNumber(Map filter) {
        return 0;
    }

    public static void main(String[] args) {
        ObjectiveTestDAO objectiveTestDAO = new ObjectiveTestDAO();
        System.out.println(objectiveTestDAO.getObjectiveTestByID("7"));
    }
}
