package DAO.MongoDB;
//package DAO.MongoDB;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import model.*;
import model.objTest.TestQuest;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.ne;
import static com.mongodb.client.model.Projections.include;

public class ObjectiveTestDAO extends AbsDAO{
    public Chapter getObjectiveTestByID(String id) {
        MongoCollection<Chapter> objectiveTests = getDB().getCollection("chapters", Chapter.class);
        Chapter chapter = objectiveTests.find(eq("_id", new ObjectId(id))).first();
        chapter.setChapterId(chapter.getId().toString());
        chapter.setSubjId(chapter.getSubject_id().toString());
        return chapter;
    }

    public String getChapterName(String id){
        MongoCollection<Chapter> objectiveTests = getDB().getCollection("chapters", Chapter.class);
        Bson projection = include("testName");
        Chapter chapter = objectiveTests.find(eq("_id", new ObjectId(id))).projection(projection).first();
        return chapter.getTestName();
    }

    public String getChapterSubjectId(String id){
        MongoCollection<Chapter> objectiveTests = getDB().getCollection("chapters", Chapter.class);
        Bson projection = include("subject_id");
        Chapter chapter = objectiveTests.find(eq("_id", new ObjectId(id))).projection(projection).first();
        return chapter.getSubject_id().toString();
    }

    public List<String> getChapterIdsbySubjectId(String subjId){
        MongoCollection<Chapter> objectiveTests = getDB().getCollection("chapters", Chapter.class);
        Bson projection = include("_id");
        Iterator<Chapter> chapterIterator = objectiveTests.find(eq("subject_id", new ObjectId(subjId))).projection(projection).iterator();
        List<String> chapterList = new ArrayList<>();
        while (chapterIterator.hasNext()) {
            chapterList.add(chapterIterator.next().getId().toString());
        }
        return chapterList;
    }

    public List<Chapter> searchObjectiveTest(Map filter, Map sort, int limit, int skip) {
        return null;
    }

    public long getObjectiveTestNumber(Map filter) {
        return 0;
    }

//    public ObjectiveTest getObjectiveTestRandomByID(String id, int num) {
//        MongoCollection<ObjectiveTest> objectiveTests = getDB().getCollection("chapters", model.objTest.ObjectiveTest.class);
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

    public List<TestQuest> getTestQuestbyChapterId(String chapId, int num){
        List<TestQuest> testQuestList = new ArrayList<>();
        MongoCollection<TestQuest> objectiveTests = getDB().getCollection("chapters", TestQuest.class);
        AggregateIterable<TestQuest> result = objectiveTests.aggregate(Arrays.asList(new Document("$match",
                        new Document("_id",
                                new ObjectId(chapId))),
                new Document("$project",
                        new Document("questions", 1)),
                new Document("$unwind",
                        new Document("path", "$questions")),
                new Document("$sample",
                        new Document("size", num))));
        MongoCursor<TestQuest> iterator = result.iterator();
        while (iterator.hasNext()) {
            TestQuest next = iterator.next();
            next.setChapterId(chapId);
            next.setQuestion(next.getQuestions());
            next.setId(null);
            next.setQuestions(null);
            testQuestList.add(next);
        }

        return testQuestList;
    }

    public static void main(String[] args) {
        ObjectiveTestDAO objectiveTestDAO = new ObjectiveTestDAO();
//        System.out.println(objectiveTestDAO.getObjectiveTestByID("6321d8456140cf015c925342"));
        System.out.println(objectiveTestDAO.getTestQuestbyChapterId("6321d8456140cf015c925342",10));
    }
}
