//package DAO.MongoDB;
//
//import com.mongodb.client.MongoCollection;
//import model.ObjectiveTest;
//import org.bson.types.ObjectId;
//
//import java.util.List;
//import java.util.Map;
//
//import static com.mongodb.client.model.Filters.eq;
//
//public class ObjectiveTestDAO extends AbsDAO{
//    public ObjectiveTest getObjectiveTestByID(String id) {
//        MongoCollection<ObjectiveTest> objectiveTests = getDB().getCollection("objective_tests", model.ObjectiveTest.class);
//        ObjectiveTest objectiveTest = objectiveTests.find(eq("_id", new ObjectId(id))).first();
//        objectiveTest.setObjectiveTestId(objectiveTest.getId().toString());
//        objectiveTest.setSubjectId(objectiveTest.getSubject_id().toString());
//        return objectiveTest;
//    }
//
//    public List<ObjectiveTest> searchObjectiveTest(Map filter, Map sort, int limit, int skip) {
//        return null;
//    }
//
//    public long getObjectiveTestNumber(Map filter) {
//        return 0;
//    }
//
//    public static void main(String[] args) {
//        ObjectiveTestDAO objectiveTestDAO = new ObjectiveTestDAO();
//        System.out.println(objectiveTestDAO.getObjectiveTestByID("613596ca2a99a62a6c65f1d7"));
//    }
//}
