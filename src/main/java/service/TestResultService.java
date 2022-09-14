package service;

import DAO.MongoDB.ObjectiveTestDAO;
import DAO.MongoDB.TestResultDAO;
import model.Comment;
import model.TestResult;

public class TestResultService {
    public TestResultDAO testResultDAO;
    public TestResultService(){
        this.testResultDAO = new TestResultDAO();
    }
    public String addTestResult(TestResult testResult) {
        testResultDAO.addTestResult(testResult);
        return "";
    }
}
