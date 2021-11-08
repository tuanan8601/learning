package DAO.MongoDB;//package DAO.MongoDB;
//
//import java.util.Map;
//
//public class QuestionDAO extends AbsDAO{
////    public Question getQuestionByID(int id) {
////        Question q = null;
////        try {
////            Connection con = getConnection();
////            PreparedStatement ps = con.prepareStatement("select * " +
////                    "from question,question_answers " +
////                    "where question.ID=question_answers.Question_ID and question.ID=?");
////            ps.setInt(1,id);
////            ResultSet rs = ps.executeQuery();
////            q = new Question();
////            while (rs.next()) {
////                q.setId(rs.getInt("question.ID"));
////                q.setTitle(rs.getString("title"));
////                Answer answer = new Answer();
////                answer.setAnswer(rs.getString("answer"));
////                answer.setAnswerHead(rs.getString("ANSWERHEAD").charAt(0));
////                q.getAnswers().add(answer);
////                q.setSolution(rs.getString("solution"));
////                q.setSolutionHead(rs.getString("SOLUTIONHEAD").charAt(0));
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return q;
////    }
////
////    public List<Question> searchQuestion(Map filter, Map sort, int limit, int skip) {
////        return null;
////    }
////
//    public long getQuestionNumber(Map filter) {
//        return 0;
//    }
////
////    public static void main(String[] args) {
////        QuestionDAO questionDAO = new QuestionDAO();
////        System.out.println(questionDAO.getQuestionByID(15));
////    }
//}
