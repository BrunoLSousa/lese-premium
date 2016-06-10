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
public class ContentConstruction extends ContentTemplate{
    private static ContentConstruction instance;
    private List<Question> questionConstruction;
    private List<Joker> jokerConstruction;
    private final Phase phase;

    private ContentConstruction() {
        this.phase = new PhaseDAO().selectPhasePerName(DevelopmentPhase.Construction.toString());
        completeListQuestions();
        completeListJokers();
    }

    public static ContentConstruction getInstance() {
        if (instance == null) {
            instance = new ContentConstruction();
        }
        return instance;
    }

    @Override
    public Question draftQuestion() {
        if (this.questionConstruction.isEmpty()) {
            completeListQuestions();
        }
        int indexQuestion = getRandomNumber(this.questionConstruction.size());
        return this.questionConstruction.remove(indexQuestion);
    }

    @Override
    public Joker draftJoker() {
        if (this.jokerConstruction.isEmpty()) {
            completeListJokers();
        }
        int indexJoker = getRandomNumber(this.jokerConstruction.size());
        return this.jokerConstruction.remove(indexJoker);
    }

    private int getRandomNumber(int limit) {
        return (int) (limit * Math.random());
    }

    private void completeListQuestions() {
        this.questionConstruction = QuestionDAO.selectQuestionPerPhase(this.phase);
    }

    private void completeListJokers() {
        this.jokerConstruction = JokerDAO.selectJokersPerPhase(this.phase);
    }
}
