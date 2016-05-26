/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.content;

import game.lese.board.DevelopmentPhase;
import game.lese.model.Joker;
import game.lese.model.Phase;
import game.lese.model.Question;
import game.lese.model.dao.JokerDAO;
import game.lese.model.dao.PhaseDAO;
import game.lese.model.dao.QuestionDAO;
import java.util.List;

/**
 *
 * @author bruno
 */
public class ContentTransition extends ContentTemplate{
    private static ContentTransition instance;
    private List<Question> questionTransition;
    private List<Joker> jokerTransition;
    private final Phase phase;

    private ContentTransition() {
        this.phase = new PhaseDAO().selectPhasePerName(DevelopmentPhase.Transition.toString());
        completeListQuestions();
        completeListJokers();
    }

    public static ContentTransition getInstance() {
        if (instance == null) {
            instance = new ContentTransition();
        }
        return instance;
    }

    @Override
    public Question draftQuestion() {
        if (this.questionTransition.isEmpty()) {
            completeListQuestions();
        }
        int indexQuestion = getRandomNumber(this.questionTransition.size());
        Question question = this.questionTransition.get(indexQuestion);
        this.questionTransition.remove(indexQuestion);
        return question;
    }

    @Override
    public Joker draftJoker() {
        if (this.jokerTransition.isEmpty()) {
            completeListJokers();
        }
        int indexJoker = getRandomNumber(this.jokerTransition.size());
        Joker joker = this.jokerTransition.get(indexJoker);
        this.jokerTransition.remove(indexJoker);
        return joker;
    }

    private int getRandomNumber(int limit) {
        return (int) (limit * Math.random());
    }

    private void completeListQuestions() {
        this.questionTransition = new QuestionDAO().selectQuestionPerPhase(this.phase);
    }

    private void completeListJokers() {
        this.jokerTransition = new JokerDAO().selectJokersPerPhase(this.phase);
    }
}
