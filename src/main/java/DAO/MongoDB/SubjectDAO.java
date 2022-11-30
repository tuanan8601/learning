package DAO.MongoDB;//package DAO.MongoDB;

import DAO.ISubjectDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import model.Comment;
import model.Subject;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

public class SubjectDAO extends AbsDAO implements ISubjectDAO {
    @Override
    public Subject getSubjectByID(String id) {
        MongoCollection<Subject> subjects = getDB().getCollection("subjects", Subject.class);
        Subject subject = subjects.find(eq("_id", new ObjectId(id))).first();
        subject.setSubjectId(subject.getId().toString());
//        subject.getObjectiveTest_ids().forEach(d->{
//            subject.getObjIds().add(d.toString());
//        });
//        subject.getObjectiveTest_map().forEach((name,objId) -> {
//            subject.getObjectiveTest_name_id().put(name,objId.toString());
//        });
        return subject;
    }

    public String getSubjectName(String id) {
        MongoCollection<Subject> subjects = getDB().getCollection("subjects", Subject.class);
        Subject subject = subjects.find(eq("_id", new ObjectId(id))).first();
        return subject.getName();
    }

    public List<Subject> getAllSubject(){
        List<Subject> subjectList= new ArrayList<>();
        MongoCollection<Subject> subjects = getDB().getCollection("subjects", Subject.class);
        subjects.find().sort(eq("name",1)).forEach(d -> {
            d.setSubjectId(d.getId().toString());
//            d.getObjectiveTest_map().forEach((name,objId) -> {
//                d.getObjectiveTest_name_id().put(name,objId.toString());
//            });
            subjectList.add(d);
        });
        return subjectList;
    }

    @Override
    public List<Subject> searchSubject(String name, int limit, int skip) {
        return null;
    }
    public List<Subject> searchSubject(String text) {
        List<Subject> subjectList= new ArrayList<>();
        MongoCollection<Subject> subjects = getDB().getCollection("subjects", Subject.class);
        Bson filter = Filters.text(text);
        subjects.find(filter).forEach(d -> {
            d.setSubjectId(d.getId().toString());
            subjectList.add(d);
        });
        return subjectList;
    }
    @Override
    public long getSubjectNumber(String name) {
        return 0;
    }

//    @Override
//    public List<Subject> searchSubject(Map filter, Map sort, int limit, int skip) {
//        return null;
//    }
//
//    @Override
//    public long getSubjectNumber(Map filter) {
//        return 0;
//    }

    public static void main(String[] args) {
        SubjectDAO subjectDAO = new SubjectDAO();
        System.out.println(subjectDAO.getSubjectByID("613445960dba7b0a99ec262c"));
        System.out.println(subjectDAO.getAllSubject());
    }

    public void addSubject(Subject subject) {
        MongoCollection<Subject> subjects = getDB().getCollection("subjects", Subject.class);
        subjects.insertOne(subject);
        if(subject!=null)
            subject.setSubjectId(subject.getId().toString());
    }

    public void deleteSubject(String subjectId) {
        MongoCollection<Subject> subjects = getDB().getCollection("subjects", Subject.class);
        subjects.deleteOne(eq("_id",new ObjectId(subjectId)));
    }

    public Subject updateSubject(String subjectId, Subject subject) {
        MongoCollection<Subject> subjects = getDB().getCollection("subjects", Subject.class);
        subjects.replaceOne(eq("_id",new ObjectId(subjectId)),subject);
        return subject;
    }
}
