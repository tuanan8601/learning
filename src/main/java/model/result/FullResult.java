package model.result;

import lombok.Data;
import model.TestResult;

import java.util.LinkedList;
import java.util.List;

@Data
public class FullResult {
    TestResult testResult;
    List<Result> resultList = new LinkedList<>();
}
