/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.house;

import game.lese.board.DevelopmentPhase;
import game.lese.board.PlayerBoard;
import game.lese.outcome.HouseOutcome;
import game.lese.presenters.console.ConsoleBoardPresenter;
import game.lese.presenters.console.ConsoleQuestionPresenter;
import game.lese.presenters.interfaces.BoardPresenter;
import game.lese.presenters.interfaces.QuestionPresenter;
import game.lese.question.QuestionInfo;
import java.util.List;

/**
 *
 * @author cass
 */
public class QuestionHouse extends House {

    private QuestionInfo question;
    private boolean correct;

    public QuestionHouse(int id, HouseOutcome outcome, DevelopmentPhase phase, QuestionInfo q, int cycle) {
        super(id, outcome, phase, cycle);
        this.question = q;
        this.correct = false;
    }

    public void setQuestion(QuestionInfo q) {
        this.question = q;
    }

    public QuestionInfo getQuestion() {
        return this.question;
    }

    @Override
    protected void presentContent() {
        ContentTemplate contentTemplate = new ContentTemplate(getDevPhase());
        House houseUpdated = contentTemplate.refreshQuestion(this);
        updateContent(houseUpdated);
    }

    @Override
    protected void interactWithPlayer(PlayerBoard p) {
        BoardPresenter boardPresenter = new ConsoleBoardPresenter();
        boardPresenter.showHeaderGame();
        QuestionPresenter questionPresenter = new ConsoleQuestionPresenter();
        questionPresenter.showQuestion(this, p);
        List<String> choices = questionPresenter.showChoices(this.question);
        String answer = questionPresenter.getPlayerAnswer(choices);
        this.correct = this.question.verifyAnswer(answer);
    }

    @Override
    protected void applyOutcome(PlayerBoard p) {
        HouseOutcome outcome = getOutcome();
        QuestionPresenter questionPresenter = new ConsoleQuestionPresenter();
        questionPresenter.showFeedback(this, p);
        outcome.apply(p, this);
        this.correct = false;
    }

    public boolean isCorrect() {
        return this.correct;
    }
    
    public void updateContent(House house){
        QuestionHouse q = (QuestionHouse)house;
        setOutcome(q.getOutcome());
        setQuestion(q.getQuestion());
    }
}
