/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.house;

import game.lese.board.DevelopmentPhase;
import game.lese.board.PlayerBoard;
import game.lese.content.ContentConstruction;
import game.lese.content.ContentElaboration;
import game.lese.content.ContentInception;
import game.lese.content.ContentTemplate;
import game.lese.content.ContentTransition;
import game.lese.content.ContentVerification;
import game.lese.outcome.HouseOutcome;
import game.lese.presenters.console.ConsoleBoardPresenter;
import game.lese.presenters.console.ConsoleJokerPresenter;
import game.lese.presenters.interfaces.BoardPresenter;
import game.lese.presenters.interfaces.JokerPresenter;
import game.lese.question.JokerBoard;

/**
 *
 * @author cass
 */
public class JokerHouse extends House {
    private JokerBoard joker;
    
    public JokerHouse(int id, HouseOutcome outcome, DevelopmentPhase phase, JokerBoard j, int cycle) {
        super(id, outcome, phase, cycle);
        this.joker = j;
    }
    
    public JokerBoard getJoker() {
        return this.joker;
    }
    
    public void setJoker(JokerBoard j) {
        this.joker = j;
    }

    @Override
    protected void presentContent() {
        ContentTemplate contentTemplate = null;
        switch(getDevPhase()){
            case Inception:
                contentTemplate = ContentInception.getInstance();
                break;
            case Elaboration:
                contentTemplate = ContentElaboration.getInstance();
                break;
            case Construction:
                contentTemplate = ContentConstruction.getInstance();
                break;
            case Verification:
                contentTemplate = ContentVerification.getInstance();
                break;
            case Transition:
                contentTemplate = ContentTransition.getInstance();
                break;
        }
        House houseUpdated = contentTemplate.refreshJoker(this);
        updateContent(houseUpdated);
    }

    @Override
    protected void interactWithPlayer(PlayerBoard p) {
        BoardPresenter boardPresenter = new ConsoleBoardPresenter();
        boardPresenter.showHeaderGame();
        JokerPresenter jokerPresenter = new ConsoleJokerPresenter();
        jokerPresenter.showContent(this);
    }

    @Override
    protected void applyOutcome(PlayerBoard p) {
        HouseOutcome outcome = getOutcome();
        outcome.apply(p, this);
    }
    
    public void updateContent(House house){
        JokerHouse j = (JokerHouse)house;
        setOutcome(j.getOutcome());
        setJoker(j.getJoker());
    }
}
