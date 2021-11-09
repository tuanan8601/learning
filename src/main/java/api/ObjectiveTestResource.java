package api;

import model.Comment;
import model.FormAnswer;
import model.ObjectiveTest;
import model.TestResult;
import service.ObjectiveTestService;

import javax.ws.rs.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("/objectivetest")
public class ObjectiveTestResource {
    ObjectiveTestService objectiveTestService;
    public ObjectiveTestResource(){
        this.objectiveTestService=new ObjectiveTestService();
    }
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ObjectiveTest getObjectiveTestbyID_api(@QueryParam("id") String id){
        ObjectiveTest objectiveTest = objectiveTestService.getObjectiveTestByID(id);
//        objectiveTest.setId(null);
//        objectiveTest.setSubject_id(null);
        return objectiveTest;
    }

    @Path("/random")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ObjectiveTest getObjectiveTestbyID_api(@QueryParam("id") String id,@QueryParam("num")String num){
        ObjectiveTest objectiveTest = objectiveTestService.getRandomQuestions(id,Integer.parseInt(num));
//        objectiveTest.setId(null);
//        objectiveTest.setSubject_id(null);
        return objectiveTest;
    }

    @POST
    @Path("/check")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public TestResult checkTest(String testForm) {
        String testName = new String();
        int time = 0, dotime = 0;
        System.out.println(testForm);
        List<FormAnswer> formAnswerList = new ArrayList<>();
        List<String> forms = Arrays.asList(testForm.split("&"));
        System.out.println(forms);
        for (String d : forms) {
            if(d.startsWith("time")) time=Integer.parseInt(d.substring(d.indexOf("=") + 1));
            if(d.startsWith("dotime")) dotime=Integer.parseInt(d.substring(d.indexOf("=") + 1));
            if (d.startsWith("testName")) testName=d.substring(d.indexOf("=") + 1);
            if (d.startsWith("qid")) {
                FormAnswer formAnswer = new FormAnswer();
                String id = d.substring(d.indexOf("=") + 1);
                formAnswer.setQid(id);
                String num = d.substring(d.indexOf("_")+1, d.indexOf("="));
                String answer=new String();
                char answerHead='.';
                if(testForm.indexOf("answer_"+num+"=")>=0){
                    answer=testForm.substring(testForm.indexOf("answer_"+num+"="));
                    answer=answer.substring(answer.indexOf("=")+1);
                    if(answer.indexOf("&")>0) answer=answer.substring(0,answer.indexOf("&"));
                    answerHead=answer.charAt(0);
                }
                formAnswer.setAnswerHead(answerHead);
                formAnswerList.add(formAnswer);
            }
        }
        System.out.println(formAnswerList);
        TestResult testResult = objectiveTestService.checkObjectiveTest(formAnswerList);
        testResult.setTestName(testName);
        testResult.setTime(time);
        testResult.setDotime(dotime);
        return testResult;
    }
}
