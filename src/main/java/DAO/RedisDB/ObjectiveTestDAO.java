//package DAO.RedisDB;

//import DAO.RedisDB.AbsDAO;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.lang.Nullable;
//import model.*;
//import org.bson.types.ObjectId;
//import redis.clients.jedis.Jedis;

import java.util.*;
import static com.mongodb.client.model.Filters.eq;
//
//public class ObjectiveTestDAO extends AbsDAO {
//    public ObjectiveTest getObjectiveTestByID(String id) {
//        List<Map.Entry<String,String>> oTmap = jedis.hscan("objectivetest:"+id,0).getResult();
//        ObjectiveTest objectiveTest = new ObjectiveTest();
//        oTmap.forEach(e->{
//            if(e.getKey().equals("id")) objectiveTest.setObjectiveTestId(e.getValue());
//            if(e.getKey().equals("testname")) objectiveTest.setTestName(e.getValue());
//            if(e.getKey().equals("time")) objectiveTest.setTime(Integer.parseInt(e.getValue()));
//            if(e.getKey().equals("poster")) objectiveTest.setPoster(e.getValue());
//            if(e.getKey().equals("subject_id")) objectiveTest.setSubjectId(e.getValue());
//        });
//        List<String> qlist = jedis.sscan("questionset:objectivetest:"+id,0).getResult();
//        for (String q_id : qlist) {
//            List<Map.Entry<String,String>> qmap = jedis.hscan("question:"+q_id,0).getResult();
//            Question question = new Question();
//            question.setId(q_id);
//            qmap.forEach(qe->{
//                if(qe.getKey().equals("title")) question.setTitle(qe.getValue());
//                if(qe.getKey().equals("image")) question.setImage(qe.getValue());
//                if(qe.getKey().equals("solution")) question.setSolution(qe.getValue());
//                if(qe.getKey().equals("solutionHead")) question.setSolutionHead(qe.getValue().charAt(0));
//                if(qe.getKey().equals("feedback")) question.setFeedback(qe.getValue());
//            });
//            long len = jedis.llen("answer:question:"+q_id);
//            List<String> answers = jedis.lrange("answer:question:"+q_id,0,len-1);
//            Collections.reverse(answers);
//            answers.forEach(d->{
//                Answer answer = new Answer();
//                answer.setAnswer(d);
//                answer.setAnswerHead(d.charAt(0));
//                question.getAnswers().add(answer);
//            });
//            objectiveTest.getQuestions().add(question);
//        }
//
//        return objectiveTest;
//    }
//
//    public ObjectiveTest getRandomQuestions(String id, @Nullable int num) {
//        num = Optional.ofNullable(num).orElse(5);
//        List<Map.Entry<String,String>> oTmap = jedis.hscan("objectivetest:"+id,0).getResult();
//        ObjectiveTest objectiveTest = new ObjectiveTest();
//        oTmap.forEach(e->{
//            if(e.getKey().equals("id")) objectiveTest.setObjectiveTestId(e.getValue());
//            if(e.getKey().equals("testname")) objectiveTest.setTestName(e.getValue());
//            if(e.getKey().equals("time")) objectiveTest.setTime(Integer.parseInt(e.getValue()));
//            if(e.getKey().equals("poster")) objectiveTest.setPoster(e.getValue());
//            if(e.getKey().equals("subject_id")) objectiveTest.setSubjectId(e.getValue());
//        });
//        List<String> qlist = jedis.srandmember("questionset:objectivetest:"+id,num);
//        for (String q_id : qlist) {
//            List<Map.Entry<String,String>> qmap = jedis.hscan("question:"+q_id,0).getResult();
//            Question question = new Question();
//            question.setId(q_id);
//            qmap.forEach(qe->{
//                if(qe.getKey().equals("title")) question.setTitle(qe.getValue());
//                if(qe.getKey().equals("image")) question.setImage(qe.getValue());
//                if(qe.getKey().equals("solution")) question.setSolution(qe.getValue());
//                if(qe.getKey().equals("solutionHead")) question.setSolutionHead(qe.getValue().charAt(0));
//                if(qe.getKey().equals("feedback")) question.setFeedback(qe.getValue());
//            });
//            long len = jedis.llen("answer:question:"+q_id);
//            List<String> answers = jedis.lrange("answer:question:"+q_id,0,len-1);
//            Collections.reverse(answers);
//            answers.forEach(d->{
//                Answer answer = new Answer();
//                answer.setAnswer(d);
//                answer.setAnswerHead(d.charAt(0));
//                question.getAnswers().add(answer);
//            });
//            objectiveTest.getQuestions().add(question);
//        }
//
//        return objectiveTest;
//    }
//
//    public boolean checkQuestionbyID(FormAnswer formAnswer) {
//        String solution=jedis.hget("question:"+formAnswer.getQid(),"solution");
//        if(solution!=null)
//            return formAnswer.getAnswerHead()==solution.charAt(0);
//        else return false;
//    }
//
//    public Question getQuestionbyID(String id){
//        Question question=new Question();
//        List<Map.Entry<String,String>> qmap = jedis.hscan("question:"+id,0).getResult();
//        question.setId(id);
//        qmap.forEach(qe->{
//            if(qe.getKey().equals("title")) question.setTitle(qe.getValue());
//            if(qe.getKey().equals("image")) question.setImage(qe.getValue());
//            if(qe.getKey().equals("solution")) question.setSolution(qe.getValue());
//            if(qe.getKey().equals("solutionHead")) question.setSolutionHead(qe.getValue().charAt(0));
//            if(qe.getKey().equals("feedback")) question.setFeedback(qe.getValue());
//        });
//        long len = jedis.llen("answer:question:"+id);
//        List<String> answers = jedis.lrange("answer:question:"+id,0,len-1);
//        Collections.reverse(answers);
//        answers.forEach(d->{
//            Answer answer = new Answer();
//            answer.setAnswer(d);
//            answer.setAnswerHead(d.charAt(0));
//            question.getAnswers().add(answer);
//        });
//        return question;
//    }
//
//    public List<ObjectiveTest> searchObjectiveTest(Map filter, Map sort, int limit, int skip) {
//        return null;
//    }
//
//    public long getObjectiveTestNumber(Map filter) {
//        return 0;
//    }
//
//    public static void main(String[] args) {
//        ObjectiveTestDAO objectiveTestDAO = new ObjectiveTestDAO();
//        System.out.println(objectiveTestDAO.getObjectiveTestByID("7"));
//        System.out.println(objectiveTestDAO.getRandomQuestions("7",5));
//        FormAnswer formAnswer = new FormAnswer();
//        formAnswer.setQid("62");
//        formAnswer.setAnswerHead('A');
//        System.out.println(objectiveTestDAO.checkQuestionbyID(formAnswer));
//    }
//}
