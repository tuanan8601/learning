package service;

import DAO.MongoDB.SubjectDAO;
import model.Subject;
import redis.clients.jedis.ScanParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SubjectService {
    public SubjectDAO subjectDAO;
    public SubjectService(){
        this.subjectDAO = new SubjectDAO();
    }
    public Subject getSubjectByID(String id) {
        Subject subject = subjectDAO.getSubjectByID(id);
        return subject;
    }
    public List<Subject> getAllSubject(){
        return subjectDAO.getAllSubject();
    }

    public static void main(String[] args) {
        SubjectService subjectService = new SubjectService();
        System.out.println(subjectService.getAllSubject());
    }

    public List<Subject> searchSubject(String name, int limit, int skip) {
        return subjectDAO.searchSubject(name,limit,skip);
    }
}
