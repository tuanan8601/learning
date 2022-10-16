package service;

import DAO.MongoDB.ObjectiveTestDAO;
import model.FormAnswer;
import model.Chapter;
import model.TestResult;

public class ObjectiveTestService {
    public ObjectiveTestDAO objectiveTestDAO;
    public ObjectiveTestService(){
        this.objectiveTestDAO = new ObjectiveTestDAO();
    }
    public Chapter getObjectiveTestByID(String id) {
        Chapter chapter = objectiveTestDAO.getObjectiveTestByID(id);
        return chapter;
    }
//    public ObjectiveTest getRandomQuestions(String id,int num){
//        ObjectiveTest objectiveTest = objectiveTestDAO.getObjectiveTestRandomByID(id,num);
//        return objectiveTest;
//    }
    public TestResult checkObjectiveTest(TestResult testResult){
        int score=0;
        for (FormAnswer f : testResult.getFormAnswers()) {
            f.setCheck(objectiveTestDAO.checkQuestionbyID(f));
            if(f.getCheck()) score++;
        }
        testResult.setScore(score);
        return testResult;
    }
}
