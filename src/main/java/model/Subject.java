package model;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.*;

@Data
public class Subject {
    ObjectId id;
    String subjectId;
    String name;
    String type;
    String poster;
    String document;
    List<ObjectId> objectiveTest_ids;
    List<String> objIds = new ArrayList<>();
//    Map<String, ObjectId> objectiveTest_map;
//    LinkedHashMap<String, String> objectiveTest_name_id = new LinkedHashMap<>();
}
