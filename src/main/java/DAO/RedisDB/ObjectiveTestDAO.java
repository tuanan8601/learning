package DAO.RedisDB;

import DAO.RedisDB.AbsDAO;
import com.mongodb.client.MongoCollection;
import model.Answer;
import model.ObjectiveTest;
import model.Question;
import org.bson.types.ObjectId;

import java.util.*;

import static DAO.RedisDB.AbsDAO.jedis;
import static com.mongodb.client.model.Filters.eq;

public class ObjectiveTestDAO extends AbsDAO {
    public ObjectiveTestDAO(){
        getdb();
    }
    public ObjectiveTest getObjectiveTestByID(String id) {
        Map<String,String> oTmap = jedis.hgetAll("objectivetest:"+id);
        ObjectiveTest objectiveTest = new ObjectiveTest();
        objectiveTest.setObjectiveTestId(oTmap.get("id"));
        objectiveTest.setTestName(oTmap.get("testname"));
        objectiveTest.setPoster(oTmap.get("poster"));
        objectiveTest.setSubjectId(oTmap.get("subject_id"));
        Iterator<String> qset = jedis.smembers("questionset:objectivetest:"+id).iterator();
        while(qset.hasNext()){
            String q_id = qset.next();
            Map<String,String> qmap = jedis.hgetAll("question:"+q_id);
            Question question = new Question();
            question.setId(q_id);
            question.setTitle(qmap.get("title"));
            question.setImage(qmap.get("image"));
            question.setSolution(qmap.get("solution"));
            question.setSolutionHead(qmap.get("solutionHead").charAt(0));
            question.setFeedback(qmap.get("feedback"));
            long len = jedis.llen("answer:question:"+q_id);
            List<String> answers = jedis.lrange("answer:question:"+q_id,1,len);
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
        System.out.println(objectiveTestDAO.getObjectiveTestByID("9"));
    }
}
