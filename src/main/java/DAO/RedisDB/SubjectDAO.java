package DAO.RedisDB;

import DAO.ISubjectDAO;
import DAO.RedisDB.AbsDAO;
import com.mongodb.client.MongoCollection;
import model.Subject;
import org.bson.types.ObjectId;
import redis.clients.jedis.Jedis;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;

public class SubjectDAO extends AbsDAO implements ISubjectDAO {
    @Override
    public Subject getSubjectByID(String id) {
        Jedis jedis = getConnection();
        Map<String,String> sbjmap = jedis.hgetAll("subject:"+id);
        Subject subject = new Subject();
        subject.setSubjectId(sbjmap.get("id"));
        subject.setName(sbjmap.get("name"));
        subject.setType(sbjmap.get("type"));
        subject.setPoster(sbjmap.get("poster"));
        Iterator<String> oTi = jedis.zrangeByScore("objectivetestzset:subject:"+id,"-inf","+inf").iterator();
        while(oTi.hasNext()){
            String oT_id = oTi.next();
            subject.getObjectiveTest_name_id().put(jedis.hget("objectivetest:"+oT_id,"testname"),oT_id);
        }
        return subject;
    }

    public List<Subject> getAllSubject(){
        Jedis jedis = getConnection();
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