package model;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Subject {
    ObjectId id;
    String subjectId;
    String name;
    String type;
    String poster;
    Map<String, ObjectId> objectiveTest_map;
    HashMap<String, String> objectiveTest_name_id = new HashMap<>();
}
