package service;

import DAO.MongoDB.ObjectiveTestDAO;
import DAO.MongoDB.SubjectDAO;
import model.*;
import model.objTest.ObjectiveTest;
import model.objTest.TestQuest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ObjectiveTestService {
    public ObjectiveTestDAO objectiveTestDAO;
    public ObjectiveTestService(){
        this.objectiveTestDAO = new ObjectiveTestDAO();
    }

    public ObjectiveTest getSubjectTest(String id, int time, int qpchap) {
        ObjectiveTest objectiveTest = new ObjectiveTest();
        objectiveTest.setTestName(new SubjectDAO().getSubjectName(id));
        objectiveTest.setTime(time);
        objectiveTest.setSubjId(id);
        List<TestQuest> questList = new ArrayList<>();
        List<String> chapterIds = objectiveTestDAO.getChapterIdsbySubjectId(id);
        for (String chapId: chapterIds) {
            questList.addAll(objectiveTestDAO.getTestQuestbyChapterId(chapId,qpchap));
        }
        Collections.shuffle(questList);
        objectiveTest.setTestQuestList(questList);
        return objectiveTest;
    }

    public ObjectiveTest getChapterTest(String id, int time, int num) {
        ObjectiveTest objectiveTest = new ObjectiveTest();
        objectiveTest.setTestName(objectiveTestDAO.getChapterName(id));
        objectiveTest.setTime(time);
        objectiveTest.setSubjId(objectiveTestDAO.getChapterSubjectId(id));
        objectiveTest.setTestQuestList(objectiveTestDAO.getTestQuestbyChapterId(id,num));
        return objectiveTest;
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

    public static void main(String[] args) {
        ObjectiveTestService objectiveTestService = new ObjectiveTestService();
        objectiveTestService.getSubjectTest("613445960dba7b0a99ec262c",20,3).getTestQuestList().forEach(d->{
            System.out.println(d);
        });
        System.out.println();
        objectiveTestService.getChapterTest("6321d8456140cf015c925342",20,10).getTestQuestList().forEach(d->{
            System.out.println(d);
        });
    }

    public List<Chapter> getChapterbySubjectId(String subjid) {
        return objectiveTestDAO.getChapterbySubjectId(subjid);
    }

    public List<Chapter> searchChapter(String text) {
        return objectiveTestDAO.searchChapter(text);
    }

    public Question getQuestionbyQid(String id, int qid) {
        return objectiveTestDAO.getQuestionbyQid(id,qid);
    }

    public void addChapter(Chapter chapter) {
        objectiveTestDAO.addChapter(chapter);
    }

    public void deleteChapter(String chapId) {
        objectiveTestDAO.deleteChapter(chapId);
    }

    public Chapter updateSubject(String chapId, Chapter chapter) {
        return objectiveTestDAO.updateSubject(chapId, chapter);
    }

    public void addQuestion(String chapId, Question question) {
        objectiveTestDAO.addQuestion(chapId, question);
    }

    public void deleteQuestion(String chapId, int qid) {
        objectiveTestDAO.deleteQuestion(chapId,qid);
    }

    public Question updateQuestion(String chapId, int qid, Question question) {
        return objectiveTestDAO.updateQuestion(chapId,qid,question);
    }
}
