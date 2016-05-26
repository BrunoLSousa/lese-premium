/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.game.reuse.lesepremium.management;

import br.game.reuse.lesepremium.model.Answer;
import br.game.reuse.lesepremium.model.Question;
import br.game.reuse.lesepremium.model.dao.AnswerDAO;
import br.game.reuse.lesepremium.model.dao.QuestionDAO;
import br.game.reuse.lesepremium.presenters.consolepresenters.ConsoleQuestionPresenter;
import br.game.reuse.lesepremium.presenters.interfaces.QuestionPresenter;

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
            question.setIdQueston(idQuestion);
            for (Answer a : question.getAnswer()) {
                a.setQuestion(question);
                AnswerDAO.createAnswer(a);
            }
            questionPresenter.showMessage("Questão cadastrada com sucesso!!!");
        }
    }

    @Override
    public void alter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
