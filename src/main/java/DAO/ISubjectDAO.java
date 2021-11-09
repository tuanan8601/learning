package DAO;

import model.Subject;

import java.util.List;
import java.util.Map;

public interface ISubjectDAO<T extends String> {
    Subject getSubjectByID(String id);
    public List<Subject> getAllSubject();
    List<Subject> searchSubject(T name, int limit, int skip);
    long getSubjectNumber(T name);
}
