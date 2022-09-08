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
    public TestResult checkObjectiveTest(List<FormAnswer> formAnswerList){
        TestResult testResult = new TestResult();
        testResult.setTotalScore(formAnswerList.size());
        int score=0;
        for (FormAnswer f : formAnswerList) {
            f.setCheck(objectiveTestDAO.checkQuestionbyID(f));
            if(f.getCheck()) score++;
        }
        testResult.setScore(score);
        return testResult;
    }
}
