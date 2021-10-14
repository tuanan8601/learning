package service;

import DAO.RedisDB.ObjectiveTestDAO;
import model.ObjectiveTest;

public class ObjectiveTestService {
    public ObjectiveTestDAO objectiveTestDAO;
    public ObjectiveTestService(){
        this.objectiveTestDAO = new ObjectiveTestDAO();
    }
    public ObjectiveTest getObjectiveTestByID(String id) {
        ObjectiveTest objectiveTest = objectiveTestDAO.getObjectiveTestByID(id);
        return objectiveTest;
    }
    public ObjectiveTest getRandomQuestions(String id,int num){
        ObjectiveTest objectiveTest = objectiveTestDAO.getRandomQuestions(id,num);
        return objectiveTest;
    }
}
