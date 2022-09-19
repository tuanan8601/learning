package service;

import DAO.MongoDB.ObjectiveTestDAO;
import DAO.MongoDB.TestResultDAO;
import model.Comment;
import model.TestResult;
import model.result.FullResult;

public class TestResultService {
    public TestResultDAO testResultDAO;
    public TestResultService(){
        this.testResultDAO = new TestResultDAO();
    }
    public String addTestResult(TestResult testResult) {
        testResultDAO.addTestResult(testResult);
        return "";
    }

    public FullResult getFullResultbyID(String id) {
        return testResultDAO.getFullResultbyID(id);
    }
}
