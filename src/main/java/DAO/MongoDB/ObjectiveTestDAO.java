package DAO.MongoDB;
//package DAO.MongoDB;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
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
        if(chapter!=null) {
            chapter.setChapterId(chapter.getId().toString());
            chapter.setSubjId(chapter.getSubject_id().toString());
        }
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

    public List<Chapter> getChapterbySubjectId(String subjid) {
        List<Chapter> chapterList= new ArrayList<>();
        MongoCollection<Chapter> chapters = getDB().getCollection("chapters", Chapter.class);
        chapters.find(eq("subject_id", new ObjectId(subjid))).forEach(d -> {
            d.setChapterId(d.getId().toString());
            d.setSubjId(d.getSubject_id().toString());
            d.setQuestions(null);
            chapterList.add(d);
        });
        return chapterList;
    }

    public static void main(String[] args) {
        ObjectiveTestDAO objectiveTestDAO = new ObjectiveTestDAO();
//        System.out.println(objectiveTestDAO.getObjectiveTestByID("6321d8456140cf015c925342"));
//        System.out.println(objectiveTestDAO.getTestQuestbyChapterId("6321d8456140cf015c925342",10));
//        System.out.println(objectiveTestDAO.getChapterbySubjectId("613445960dba7b0a99ec262c"));
    }

    public List<Chapter> searchChapter(String text) {
        List<Chapter> chapterList= new ArrayList<>();
        MongoCollection<Chapter> chapters = getDB().getCollection("chapters", Chapter.class);
        Bson filter = Filters.text(text);
        chapters.find(filter).forEach(d -> {
            d.setChapterId(d.getId().toString());
            d.setSubjId(d.getSubject_id().toString());
            d.setQuestions(null);
            chapterList.add(d);
        });
        return chapterList;
    }

    public Question getQuestionbyQid(String id, int qid) {
        MongoCollection<Chapter> objectiveTests = getDB().getCollection("chapters", Chapter.class);
        Chapter chapter = objectiveTests.find(eq("_id", new ObjectId(id))).first();
        Question question = new Question();
        if(chapter!=null) {
            for (Question quest: chapter.getQuestions()) {
                if(quest.getQid()==qid){
                    question=quest;
                }
            }
        }
        return question;
    }

    public void addChapter(Chapter chapter) {
        MongoCollection<Chapter> chapters = getDB().getCollection("chapters", Chapter.class);
        if(chapter.getSubjId()!=null) chapter.setSubject_id(new ObjectId(chapter.getSubjId()));
        chapters.insertOne(chapter);
    }

    public void deleteChapter(String chapId) {
        MongoCollection<Chapter> chapters = getDB().getCollection("chapters", Chapter.class);
        chapters.deleteOne(eq("_id",new ObjectId(chapId)));
    }

    public Chapter updateSubject(String chapId, Chapter chapter) {
        MongoCollection<Chapter> chapters = getDB().getCollection("chapters", Chapter.class);
        chapter.setId(new ObjectId(chapter.getChapterId()));
        chapter.setSubject_id(new ObjectId(chapter.getSubjId()));
        chapters.replaceOne(eq("_id",new ObjectId(chapId)),chapter);
        return chapter;
    }

    public void addQuestion(String chapId, Question question) {
        MongoCollection<Chapter> objectiveTests = getDB().getCollection("chapters", Chapter.class);
        Chapter chapter = objectiveTests.find(eq("_id", new ObjectId(chapId))).first();
        List<Question> questionList = chapter.getQuestions();
        question.setQid(newId(questionList));
        chapter.getQuestions().add(question);
        objectiveTests.replaceOne(eq("_id",new ObjectId(chapId)),chapter);
    }

    public int newId(List<Question> questionList){
        List<Integer> ids = new ArrayList<>();
        for (Question q:questionList) {
            ids.add(q.getQid());
        }
        if(ids.size()<=0) return 0;
        return Collections.max(ids)+1;
    }

    public void deleteQuestion(String chapId, int qid) {
        MongoCollection<Chapter> objectiveTests = getDB().getCollection("chapters", Chapter.class);
        Chapter chapter = objectiveTests.find(eq("_id", new ObjectId(chapId))).first();
        List<Question> questionList = chapter.getQuestions();
        questionList.remove(findQuestionbyid(questionList,qid));
        objectiveTests.replaceOne(eq("_id",new ObjectId(chapId)),chapter);
    }

    public Question updateQuestion(String chapId, int qid, Question question) {
        MongoCollection<Chapter> objectiveTests = getDB().getCollection("chapters", Chapter.class);
        Chapter chapter = objectiveTests.find(eq("_id", new ObjectId(chapId))).first();
        List<Question> questionList = chapter.getQuestions();
        question.setQid(qid);
        questionList.set(questionList.indexOf(findQuestionbyid(questionList,qid)),question);
        objectiveTests.replaceOne(eq("_id",new ObjectId(chapId)),chapter);
        return question;
    }
}
