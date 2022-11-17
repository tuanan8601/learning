package DAO.MongoDB;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import model.*;
import model.result.FullResult;
import model.result.Result;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class TestResultDAO extends AbsDAO{
    public void addTestResult(TestResult testResult){
        MongoCollection<TestResult> testResults = getDB().getCollection("test_results", TestResult.class);
        testResult.setUid(new ObjectId(testResult.getUserId()));
        testResult.setChapSubjId(new ObjectId(testResult.getCsId()));
        testResults.insertOne(testResult);
        testResult.setFormId(testResult.getId().toString());
    }

    public FullResult getFullResultbyID(String id) {
        FullResult fullResult = new FullResult();
        MongoCollection<TestResult> testResults = getDB().getCollection("test_results", TestResult.class);
        TestResult testResult = testResults.find(eq("_id", new ObjectId(id))).first();
        for (FormAnswer formAnswer:testResult.getFormAnswers()) {
            Chapter chapter = new ObjectiveTestDAO().getObjectiveTestByID(formAnswer.getChapterId());
            if(chapter!=null) {
                for (Question question : chapter.getQuestions()) {
                    if (formAnswer.getQid() == question.getQid()) {
                        Result result = new Result();
                        result.setQuestion(question);
                        result.setFormAnswer(formAnswer);
                        fullResult.getResultList().add(result);
                    }
                }
            }
            else {
                Result result = new Result();
                result.setFormAnswer(formAnswer);
                fullResult.getResultList().add(result);
            }
        }
        testResult.setFormAnswers(null);
        testResult.setCsId(testResult.getChapSubjId().toString());
        testResult.setUserId(testResult.getUid().toString());
        fullResult.setTestResult(testResult);
        return fullResult;
    }

    public List<TestResult> getTestResultsbyUid(String uid) {
        List<TestResult> testResultList = new ArrayList<>();
        MongoCollection<TestResult> testResults = getDB().getCollection("test_results", TestResult.class);
        FindIterable<TestResult> testResultFindIterable = testResults.find(eq("uid", new ObjectId(uid))).sort(eq("createdAt",-1));

        if(testResultFindIterable!=null) {
            testResultFindIterable.forEach(d -> {
                d.setFormAnswers(null);
                d.setFormId(d.getId().toString());
                d.setUserId(d.getUid().toString());
                d.setCsId(d.getChapSubjId().toString());
                testResultList.add(d);
            });
        }
        System.out.println(testResultList);
        return testResultList;
    }

    public List<TestResult> getTestResultsbyTypeCsId(int type, String csId) {
        List<TestResult> testResultList = new ArrayList<>();
        MongoCollection<TestResult> testResults = getDB().getCollection("test_results", TestResult.class);
        FindIterable<TestResult> testResultFindIterable = testResults.find(and(eq("type",type),eq("chapSubjId",new ObjectId(csId)))).sort(eq("createdAt",-1));

        if(testResultFindIterable!=null) {
            testResultFindIterable.forEach(d -> {
                d.setFormAnswers(null);
                d.setFormId(d.getId().toString());
                d.setUserId(d.getUid().toString());
                d.setCsId(d.getChapSubjId().toString());
                testResultList.add(d);
            });
        }
        System.out.println(testResultList);
        return testResultList;
    }
}
