/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.management;

import game.lese.model.Answer;
import game.lese.model.Question;
import game.lese.model.dao.AnswerDAO;
import game.lese.model.dao.QuestionDAO;
import game.lese.presenters.console.ConsoleQuestionPresenter;
import game.lese.presenters.interfaces.QuestionPresenter;
import java.util.List;

/**
 *
 * @author bruno
 */
public class ManagementQuestion implements Management {

    @Override
    public void create() {
        QuestionPresenter questionPresenter = new ConsoleQuestionPresenter();
        Question question = questionPresenter.getNewQuestion();
        if (questionPresenter.confirmResgisterQuestion()) {
            int idQuestion = QuestionDAO.createQuestion(question);
            question.setQuestionId(idQuestion);
            for (Answer a : question.getAnswer()) {
                a.setQuestion(question);
                AnswerDAO.createAnswer(a);
            }
            questionPresenter.showMessage("Questão cadastrada com sucesso!!!");
        } else {
            questionPresenter.showMessage("Error ao criar questão. Tente Novamente");
        }
    }

    @Override
    public void alter() { 
        List<Question> allQuestions = QuestionDAO.selectAllQuestions();
        QuestionPresenter questionPresenter = new ConsoleQuestionPresenter();
        Question selectedQuestion = questionPresenter.getQuestionForEdit(allQuestions);
       
        String infoMsg = "Error ao atualizar questão. Tente Novamente";
        
        if (selectedQuestion != null) {
            Question result = questionPresenter.editQuestion(selectedQuestion);
            
            if (result != null) {
                QuestionDAO.updateQuestion(result);
                infoMsg = "Questão atualizado com sucessos";
            }
        }
        
        questionPresenter.showMessage(infoMsg);
    }

}
