package utils;

import model.Answer;
import model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParseQuestion {
    public static boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    public static boolean isAnswer(String str){

        return !isNumeric(str.substring(0,1))&&(str.charAt(1)=='.'||str.charAt(1)==','||str.charAt(1)==')');
    }

    public static boolean isTitle(String str){

        return isNumeric(str.split("\\.")[0]);
    }

    public static List<Question> parseQuestion(String text){
        boolean back =false;
        List<Question> questionList = new ArrayList<>();
        Scanner scanner = new Scanner(text);
        Question question = null;
        String line = "";
        while (scanner.hasNextLine() || back) {
            if(!back) {
                line = scanner.nextLine().trim();
            }
            else back =false;
            if(line.trim().equals("")){

            }
            else if(isTitle(line)){
                if(question!=null){
                    questionList.add(question);
                }
                question = new Question();
                String title=line;
                while (scanner.hasNextLine()){
                    line = scanner.nextLine().trim();
                    if(!line.trim().equals("")&&!isAnswer(line)&&!line.startsWith("ANSWER:")&&!line.startsWith("IMAGE:")){
                        title+="\r\n"+line;
                    }
                    else{
                        question.setTitle(title);
                        back = true;
                        break;
                    }
                }
            }
            else if(isAnswer(line)){
                Answer answer = new Answer();
                String strans=line;
                answer.setAnswerHead(line.charAt(0));
                while (scanner.hasNextLine()){
                    line = scanner.nextLine().trim();
                    if(!line.trim().equals("")&&!isAnswer(line)&&!isTitle(line)&&!line.startsWith("ANSWER:")&&!line.startsWith("IMAGE:")){
                        strans+="\r\n"+line;
                    }
                    else{
                        answer.setAnswer(strans);
                        question.getAnswers().add(answer);
                        back=true;
                        break;
                    }
                }
            }
            else if(line.startsWith("ANSWER:")){
                String strSolutionHead=line.substring(7).trim();
                question.setSolutionHead(strSolutionHead.charAt(0)+"");
                for (Answer q : question.getAnswers()) {
                    if ((q.getAnswerHead() + "").trim().equals(strSolutionHead.trim())) {
                        question.setSolution(q.getAnswer());
                    }
                }
            }
            else if(line.startsWith("IMAGE:")){
                String strSolutionHead=line.substring(6).trim();
                question.setImage(strSolutionHead);
            }
        }
        scanner.close();
        return questionList;
    }
}
