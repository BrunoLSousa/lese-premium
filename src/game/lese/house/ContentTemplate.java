/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.house;

import game.lese.board.DevelopmentPhase;
import game.lese.model.Answer;
import game.lese.model.Joker;
import game.lese.model.Phase;
import game.lese.model.Question;
import game.lese.model.dao.JokerDAO;
import game.lese.model.dao.PhaseDAO;
import game.lese.model.dao.QuestionDAO;
import game.lese.outcome.BonusOutcome;
import game.lese.outcome.HouseOutcome;
import game.lese.outcome.PenalityOutcome;
import game.lese.question.JokerInfo;
import game.lese.question.QuestionInfo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bruno
 */
public class ContentTemplate {
    private List<Question> questions;
    private List<Joker> jokers;
    private Phase phase;
    
    public ContentTemplate(DevelopmentPhase devPhase) {
        questions = new ArrayList<>();
        jokers = new ArrayList<>();
        phase = new PhaseDAO().selectPhasePerName(devPhase.toString());
    }
    
    public House refreshQuestion(House house){
        Question q = draftQuestion();
        HouseOutcome outcome = new BonusOutcome(q.getHouse(), q.getScore(), (float) 0.0);
        QuestionInfo questionBoard = new QuestionInfo(q.getDescription(), q.getExplanation());
        for(Answer answer : q.getAnswer()){
            questionBoard.addChoice(answer.getDescription(), (answer.getStatus().equals("1")));
        }
        QuestionHouse questionUpdated = new QuestionHouse(house.getId(), outcome, house.getDevPhase(), questionBoard, house.getCycle());
        return questionUpdated;
    }
    
    public House refreshJoker(House house){
        Joker j = draftJoker();
        HouseOutcome outcome;
        if(j.getAction().equals("1")){
            outcome = new BonusOutcome(1, j.getScore(), (float) 0.0);
        }else{
            outcome = new PenalityOutcome(1, j.getScore());
        }
        JokerInfo jokerBoard = new JokerInfo(j.getTitle(), j.getDescription());
        JokerHouse jokerUpdated = new JokerHouse(house.getId(), outcome, house.getDevPhase(), jokerBoard, house.getCycle());
        return jokerUpdated;
    }
    
    private Question draftQuestion() {
        if (this.questions.isEmpty()) {
            completeListQuestions();
        }
        int indexQuestion = getRandomNumber(this.questions.size());
        return this.questions.remove(indexQuestion);
    }

    private Joker draftJoker() {
        if (this.jokers.isEmpty()) {
            completeListJokers();
        }
        int indexJoker = getRandomNumber(this.jokers.size());
        return this.jokers.remove(indexJoker);
    }
    
    private static int getRandomNumber(int limit){
        return (int)(limit*Math.random());
    }
    
    private void completeListQuestions(){
        this.questions = QuestionDAO.selectQuestionsPerPhase(this.phase);
    }
    
    private void completeListJokers(){
        this.jokers = JokerDAO.selectJokersPerPhase(this.phase);
    }
}
