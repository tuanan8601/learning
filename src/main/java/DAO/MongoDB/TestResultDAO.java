package DAO.MongoDB;

import com.mongodb.client.MongoCollection;
import model.TestResult;

public class TestResultDAO extends AbsDAO{
    public void addTestResult(TestResult testResult){
        MongoCollection<TestResult> testResults = getDB().getCollection("test_results", TestResult.class);
        testResults.insertOne(testResult);
        testResult.setFormId(testResult.getId().toString());
    }
}
