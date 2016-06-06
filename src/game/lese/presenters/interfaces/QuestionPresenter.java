/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.presenters.interfaces;

import game.lese.board.PlayerBoard;
import game.lese.house.House;
import game.lese.house.QuestionHouse;
import game.lese.model.Phase;
import game.lese.model.Question;
import game.lese.question.QuestionInfo;
import java.util.List;

/**
 *
 * @author marlon
 */
public interface QuestionPresenter {
    
    /**
     * Displays the question to the player.
     *
     * @param question
     * @param p
     */
    void showQuestion(QuestionHouse question, PlayerBoard p);
    
    /**
     * Displays question choices to the player.
     * @param question
     * @return 
     */
    List<String> showChoices(QuestionInfo question);
    
    /**
     * Obtains answer from the player
     * @param choices
     * @return player answer
     */
    String getPlayerAnswer(List<String> choices);
    
    /**
     * Obtains new question
     * @return 
     */
    Question getNewQuestion();
    
    /**
     * Gives feedback to the user based on their answer.
     * @param house
     * @param p
     */
    void showFeedback(House house, PlayerBoard p);
    
    /**
     * Gives message to the user based on their answer.
     * @param feedback 
     */
    void showMessage(String feedback);
    
    /**
     * Return confirmation's register question 
     * @return  
     */
    boolean confirmResgisterQuestion();
    
    Question getQuestionForEdit(List<Question> questions);
    
    Question editQuestion(Question question);

}
