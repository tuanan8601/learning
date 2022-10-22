package model.objTest;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.*;

@Data
public class ObjectiveTest {
    String testName;
    String subjId;
    int time;
    List<TestQuest> testQuestList = new LinkedList<>();
}
