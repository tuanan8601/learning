package DAO;

import com.mongodb.client.MongoCollection;
import model.Subject;
import org.bson.types.ObjectId;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

public class SubjectDAO extends AbsDAO implements ISubjectDAO{
    @Override
    public Subject getSubjectByID(String id) {
        MongoCollection<Subject> subjects = getDB().getCollection("subjects", Subject.class);
        Subject subject = subjects.find(eq("_id", new ObjectId(id))).first();
        subject.setSubjectId(subject.getId().toString());
        subject.getObjectiveTest_map().forEach((name,objId) -> {
            subject.getObjectiveTest_name_id().put(name,objId.toString());
        });
        return subject;
    }

    public List<Subject> getAllSubject(){
        List<Subject> subjectList= new ArrayList<>();
        MongoCollection<Subject> subjects = getDB().getCollection("subjects", Subject.class);
        subjects.find().forEach(d -> {
            d.setSubjectId(d.getId().toString());
            d.getObjectiveTest_map().forEach((name,objId) -> {
                d.getObjectiveTest_name_id().put(name,objId.toString());
            });
            subjectList.add(d);
        });
        return subjectList;
    }

    @Override
    public List<Subject> searchSubject(Map filter, Map sort, int limit, int skip) {
        return null;
    }

    @Override
    public long getSubjectNumber(Map filter) {
        return 0;
    }

    public static void main(String[] args) {
        SubjectDAO subjectDAO = new SubjectDAO();
        System.out.println(subjectDAO.getSubjectByID("61344df70e20bb787f488706"));
        System.out.println(subjectDAO.getAllSubject());
    }
}
