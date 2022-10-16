package DAO.MongoDB;

import com.mongodb.client.MongoCollection;
import model.*;
import model.result.FullResult;
import model.result.Result;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

public class TestResultDAO extends AbsDAO{
    public void addTestResult(TestResult testResult){
        MongoCollection<TestResult> testResults = getDB().getCollection("test_results", TestResult.class);
        testResults.insertOne(testResult);
        testResult.setFormId(testResult.getId().toString());
    }

    public FullResult getFullResultbyID(String id) {
        FullResult fullResult = new FullResult();
        MongoCollection<TestResult> testResults = getDB().getCollection("test_results", TestResult.class);
        TestResult testResult = testResults.find(eq("_id", new ObjectId(id))).first();
        for (FormAnswer formAnswer:testResult.getFormAnswers()) {
            Chapter chapter = new ObjectiveTestDAO().getObjectiveTestByID(formAnswer.getChapterId());
            for (Question question: chapter.getQuestions()) {
                if(formAnswer.getQid() == question.getQid()){
                    Result result = new Result();
                    result.setQuestion(question);
                    result.setFormAnswer(formAnswer);
                    fullResult.getResultList().add(result);
                }
            }
        }
        testResult.setFormAnswers(null);
        fullResult.setTestResult(testResult);
        return fullResult;
    }
}