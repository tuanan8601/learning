package service;

import DAO.MongoDB.ObjectiveTestDAO;
import model.FormAnswer;
import model.ObjectiveTest;
import model.Question;
import model.TestResult;

import java.util.List;

public class ObjectiveTestService {
    public ObjectiveTestDAO objectiveTestDAO;
    public ObjectiveTestService(){
        this.objectiveTestDAO = new ObjectiveTestDAO();
    }
    public ObjectiveTest getObjectiveTestByID(String id) {
        ObjectiveTest objectiveTest = objectiveTestDAO.getObjectiveTestByID(id);
        return objectiveTest;
    }
//    public ObjectiveTest getRandomQuestions(String id,int num){
//        ObjectiveTest objectiveTest = objectiveTestDAO.getObjectiveTestRandomByID(id,num);
//        return objectiveTest;
//    }
    public TestResult checkObjectiveTest(TestResult testResult){
        int score=0;
        for (FormAnswer f : testResult.getFormAnswers()) {
            f.setCheck(objectiveTestDAO.checkQuestionbyID(testResult.getTestId(),f));
            if(f.getCheck()) score++;
        }
        testResult.setScore(score);
        return testResult;
    }
}
