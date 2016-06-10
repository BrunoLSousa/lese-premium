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
public class ContentElaboration extends ContentTemplate {
    private static ContentElaboration instance;
    private List<Question> questionElaboration;
    private List<Joker> jokerElaboration;
    private final Phase phase;

    private ContentElaboration() {
        this.phase = new PhaseDAO().selectPhasePerName(DevelopmentPhase.Elaboration.toString());
        completeListQuestions();
        completeListJokers();
    }

    public static ContentElaboration getInstance() {
        if (instance == null) {
            instance = new ContentElaboration();
        }
        return instance;
    }

    @Override
    public Question draftQuestion() {
        if (this.questionElaboration.isEmpty()) {
            completeListQuestions();
        }
        int indexQuestion = getRandomNumber(this.questionElaboration.size());
        return this.questionElaboration.remove(indexQuestion);
    }

    @Override
    public Joker draftJoker() {
        if (this.jokerElaboration.isEmpty()) {
            completeListJokers();
        }
        int indexJoker = getRandomNumber(this.jokerElaboration.size());
        return this.jokerElaboration.remove(indexJoker);
    }

    private int getRandomNumber(int limit) {
        return (int) (limit * Math.random());
    }

    private void completeListQuestions() {
        this.questionElaboration = QuestionDAO.selectQuestionPerPhase(this.phase);
    }

    private void completeListJokers() {
        this.jokerElaboration = JokerDAO.selectJokersPerPhase(this.phase);
    }

}
