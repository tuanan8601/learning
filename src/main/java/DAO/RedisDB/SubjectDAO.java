package DAO.RedisDB;

import DAO.ISubjectDAO;
import DAO.RedisDB.AbsDAO;
import com.mongodb.client.MongoCollection;
import model.Subject;
import org.bson.types.ObjectId;

import java.util.*;

import static DAO.RedisDB.AbsDAO.jedis;
import static com.mongodb.client.model.Filters.eq;

public class SubjectDAO extends AbsDAO implements ISubjectDAO {
    public SubjectDAO(){
        getdb();
    }
    @Override
    public Subject getSubjectByID(String id) {
        Map<String,String> sbjmap = jedis.hgetAll("subject:"+id);
        Subject subject = new Subject();
        subject.setSubjectId(sbjmap.get("id"));
        subject.setName(sbjmap.get("name"));
        subject.setType(sbjmap.get("type"));
        subject.setPoster(sbjmap.get("poster"));
        Iterator<String> oTi = jedis.smembers("objectivetestset:subject:"+id).iterator();
        while(oTi.hasNext()){
            String oT_id = oTi.next();
            subject.getObjectiveTest_name_id().put(jedis.hget("objectivetest:"+oT_id,"testname"),oT_id);
        }
        return subject;
    }

    public List<Subject> getAllSubject(){
        Iterator<String> sbji = jedis.keys("subject:*").iterator();
        List<Subject> subjectList= new ArrayList<>();
        while (sbji.hasNext()){
            String key = sbji.next();
            Subject subject = getSubjectByID(key.split(":")[1]);
            subjectList.add(subject);
        }

        return subjectList;
    }

    public List<Subject> getSubjectByType(String type){
        List<Subject> subjectList= new ArrayList<>();
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
        System.out.println(new SubjectDAO().getSubjectByID("2"));
        System.out.println(new SubjectDAO().getAllSubject());
    }
}