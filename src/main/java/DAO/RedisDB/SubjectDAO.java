package DAO.RedisDB;

import DAO.ISubjectDAO;
import model.Subject;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.Tuple;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;

public class SubjectDAO extends AbsDAO implements ISubjectDAO {
    @Override
    public Subject getSubjectByID(String id) {
        List<Map.Entry<String,String>> sbjmap = jedis.hscan("subject:"+id,0).getResult();
        Subject subject = new Subject();
        for(Map.Entry<String,String> sbjentry:sbjmap) {
            if (sbjentry.getKey().equals("id")) subject.setSubjectId(sbjentry.getValue());
            if (sbjentry.getKey().equals("name")) subject.setName(sbjentry.getValue());
            if (sbjentry.getKey().equals("type")) subject.setType(sbjentry.getValue());
            if (sbjentry.getKey().equals("poster")) subject.setPoster(sbjentry.getValue());
        }
        List<Tuple> oTzset = jedis.zscan("objectivetestzset:subject:" + id, 0).getResult();
        for (Tuple oT_tuple : oTzset){
            String oT_id = oT_tuple.getElement();
            String testname =jedis.hscan("objectivetest:"+oT_id,0,new ScanParams().match("testname")).getResult().get(0).getValue();
            subject.getObjectiveTest_name_id().put(testname, oT_id);
        }
        return subject;
    }

    public List<Subject> getAllSubject(){
        List<Map.Entry<String,String>> sbj = jedis.hscan("subjectindex",0).getResult();
        List<Subject> subjectList= new ArrayList<>();
        sbj.forEach(e->{
            Subject subject = new Subject();
            List<Map.Entry<String,String>> sbjmap = jedis.hscan("subject:"+e.getValue(),0).getResult();
            subject.setName(e.getKey());
            subject.setSubjectId(e.getValue());
            sbjmap.forEach(sbje->{
                if(sbje.getKey().equals("poster")) subject.setPoster(sbje.getValue());
                if(sbje.getKey().equals("type")) subject.setType(sbje.getValue());
            });
            subjectList.add(subject);
        });
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