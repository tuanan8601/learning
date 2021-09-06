package service;

import DAO.SubjectDAO;
import model.Subject;

import java.util.List;

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
}
