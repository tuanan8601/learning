package DAO.MongoDB;
//package DAO.MongoDB;

import com.mongodb.client.MongoCollection;
import model.*;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

public class ObjectiveTestDAO extends AbsDAO{
    public Chapter getObjectiveTestByID(String id) {
        MongoCollection<Chapter> objectiveTests = getDB().getCollection("chapters", Chapter.class);
        Chapter chapter = objectiveTests.find(eq("_id", new ObjectId(id))).first();
        chapter.setChapterId(chapter.getId().toString());
        chapter.setSubjId(chapter.getSubject_id().toString());
        return chapter;
    }

    public List<Chapter> searchObjectiveTest(Map filter, Map sort, int limit, int skip) {
        return null;
    }

    public long getObjectiveTestNumber(Map filter) {
        return 0;
    }

//    public ObjectiveTest getObjectiveTestRandomByID(String id, int num) {
//        MongoCollection<ObjectiveTest> objectiveTests = getDB().getCollection("chapters", model.ObjectiveTest.class);
//        ObjectiveTest objectiveTest = objectiveTests.find(eq("_id", new ObjectId(id))).first();
//        objectiveTest.setObjectiveTestId(objectiveTest.getId().toString());
//        objectiveTest.setSubjId(objectiveTest.getSubject_id().toString());
//        return objectiveTest;
//    }

    public boolean checkQuestionbyID(FormAnswer f) {
        MongoCollection<Chapter> objectiveTests = getDB().getCollection("chapters", Chapter.class);
        Chapter chapter = objectiveTests.find(eq("_id", new ObjectId(f.getChapterId()))).first();
        Question question=findQuestionbyid(chapter.getQuestions(),f.getQid());
        if(question.getSolutionHead().equals(f.getAnswerHead()))
            return true;
        else
            return false;
    }
    public Question findQuestionbyid(List<Question> questions,int qid){
        for (Question q:questions) {
            if(q.getQid()==qid)
                return q;
        }
        return null;
    }

    public static void main(String[] args) {
        ObjectiveTestDAO objectiveTestDAO = new ObjectiveTestDAO();
        System.out.println(objectiveTestDAO.getObjectiveTestByID("6321d8456140cf015c925342"));
    }
}
