/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.game.reuse.lesepremium.model.dao;

import br.game.reuse.lesepremium.connection.DBConnection;
import br.game.reuse.lesepremium.model.Answer;
import br.game.reuse.lesepremium.model.Phase;
import br.game.reuse.lesepremium.model.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bruno
 */
public class QuestionDAO {
    
    public static int createQuestion(Question question){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("INSERT INTO question(phase, description, explanation, score, house) VALUES(?, ?, ?, ?, ?)");
            ps.setInt(1, question.getPhase().getIdPhase());
            ps.setString(2, question.getDescription());
            ps.setString(3, question.getExplanation());
            ps.setInt(4, question.getScore());
            ps.setInt(5, question.getHouse());
            ps.executeUpdate();
            ps = connection.prepareStatement("SELECT MAX(id_question) FROM question");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int idQuestion = rs.getInt(1);
            return idQuestion;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBConnection.closeConnection(connection, ps);
        }
        return 0;
    }
    
    public static List<Question> selectQuestionPerPhase(Phase phase){
        Connection connection = null;
        PreparedStatement ps = null;
        List<Question> listQuestions = new ArrayList<>();
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("SELECT * FROM question WHERE phase=?");
            ps.setInt(1, phase.getIdPhase());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Question question = new Question();
                question.setIdQueston(rs.getInt("id_question"));
                question.setPhase(phase);
                question.setDescription(rs.getString("description"));
                question.setExplanation(rs.getString("explanation"));
                question.setScore(rs.getInt("score"));
                question.setHouse(rs.getInt("house"));
                question.setAnswer(new AnswerDAO().selectAnswerPerIdQuestion(question.getIdQueston()));
                listQuestions.add(question);
            }            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBConnection.closeConnection(connection, ps);
        }
        return listQuestions;
    }
    
}
