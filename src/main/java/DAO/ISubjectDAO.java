package DAO;

import model.Subject;

import java.util.List;
import java.util.Map;

public interface ISubjectDAO<T extends Map> {
    Subject getSubjectByID(String id);
    public List<Subject> getAllSubject();
    List<Subject> searchSubject(T filter, T sort, int limit, int skip);
    long getSubjectNumber(T filter);
}
