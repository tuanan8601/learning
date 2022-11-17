package api;

import model.*;
import model.objTest.ObjectiveTest;
import org.bson.types.ObjectId;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import service.ObjectiveTestService;
import service.TestResultService;
import utils.FileUtils;
import utils.ParseQuestion;
//import utils.FileUtils;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@Path("/objectivetest")
public class ObjectiveTestResource {
    ObjectiveTestService objectiveTestService;
    public ObjectiveTestResource(){
        this.objectiveTestService=new ObjectiveTestService();
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Chapter getObjectiveTestbyID_api(@PathParam("id") String id){
        Chapter chapter = objectiveTestService.getObjectiveTestByID(id);
//        objectiveTest.setId(null);
//        objectiveTest.setSubject_id(null);
        return chapter;
    }

    @POST
    @Path("/add")
    public String addChapter(Chapter chapter) {
        System.out.println(chapter);
        objectiveTestService.addChapter(chapter);
        return "";
    }

    @DELETE
    @Path("/{id}")
    public String deleteChapter(@PathParam("id") String chapId){
        objectiveTestService.deleteChapter(chapId);
        return "";
    }

    @PUT
    @Path("/{id}")
    public Chapter updateSubject(@PathParam("id") String chapId,Chapter chapter){
        return objectiveTestService.updateSubject(chapId,chapter);
    }

    @POST
    @Path("/{id}/question/add")
    public String addQuestion(@PathParam("id")String chapId, Question question) {
        System.out.println(question);
        objectiveTestService.addQuestion(chapId,question);
        return "";
    }

    @DELETE
    @Path("/{id}/question/{qid}")
    public String deleteQuestion(@PathParam("id") String chapId,@PathParam("qid") int qid){
        objectiveTestService.deleteQuestion(chapId,qid);
        return "";
    }

    @PUT
    @Path("/{id}/question/many")
    public String deleteQuestionMany(@PathParam("id") String chapId,List<Integer> listId){
        listId.forEach(id->{
            objectiveTestService.deleteQuestion(chapId,id);
        });
        return "";
    }

    @PUT
    @Path("/{id}/question/{qid}")
    public Question updateQuestion(@PathParam("id") String chapId,@PathParam("qid") int qid, Question question){
        return objectiveTestService.updateQuestion(chapId,qid,question);
    }

    @Path("subjecttest/{subjectId}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ObjectiveTest getSubjectTest (@PathParam("subjectId") String id, @QueryParam("time") int time, @QueryParam("questperchap") int qpchap){
        ObjectiveTest objectiveTest = objectiveTestService.getSubjectTest(id,time,qpchap);
        return objectiveTest;
    }

    @Path("chaptertest/{chapterId}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ObjectiveTest getChapterTest (@PathParam("chapterId") String id, @QueryParam("time") int time, @QueryParam("num") int num){
        ObjectiveTest objectiveTest = objectiveTestService.getChapterTest(id,time,num);;
        return objectiveTest;
    }

    @POST
    @Path("/check")
    @Produces(MediaType.APPLICATION_JSON)
    public TestResult checkTest(TestResult testResult) {
        testResult = objectiveTestService.checkObjectiveTest(testResult);
        testResult.setCreatedAt(new Date());
        System.out.println(testResult.toString());
        (new TestResultService()).addTestResult(testResult);
        System.out.println("Again: "+testResult.toString());
        return testResult;
    }

    @Path("subject/{subjid}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Chapter> getChapterbySubjectId(@PathParam("subjid") String subjid){
        return objectiveTestService.getChapterbySubjectId(subjid);
    }

    @Path("/search")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Chapter> searchChapter(@QueryParam("text") String text){
        return objectiveTestService.searchChapter(text);
    }

    @Path("/{id}/question/{qid}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Question getQuestionbyQid(@PathParam("id") String id,@PathParam("qid") int qid){
        return objectiveTestService.getQuestionbyQid(id,qid);
    }

    public static final String BASE_FOLDER = "D:/Du_an_on_thi/txt/";

    @POST
    @Path("/upload/{id}")
    public String uploadFile(@PathParam("id") String chapId, TextFile textFile) {
//        System.out.println(textFile);
        ParseQuestion.parseQuestion(textFile.getContent()).forEach(q->{
            System.out.println(q);
            objectiveTestService.addQuestion(chapId,q);
        });
//        try (PrintWriter out = new PrintWriter(BASE_FOLDER+textFile.getName()+".txt")) {
//            out.println(textFile.getContent());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        return "Sucess";
    }
}
