/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.game.reuse.lesepremium.house;

import br.game.reuse.lesepremium.board.DevelopmentPhase;
import br.game.reuse.lesepremium.board.PlayerBoard;
import br.game.reuse.lesepremium.content.ContentConstruction;
import br.game.reuse.lesepremium.content.ContentElaboration;
import br.game.reuse.lesepremium.content.ContentInception;
import br.game.reuse.lesepremium.content.ContentTemplate;
import br.game.reuse.lesepremium.content.ContentTransition;
import br.game.reuse.lesepremium.content.ContentVerification;
import br.game.reuse.lesepremium.outcome.HouseOutcome;
import br.game.reuse.lesepremium.presenters.consolepresenters.ConsoleBoardPresenter;
import br.game.reuse.lesepremium.presenters.consolepresenters.ConsoleJokerPresenter;
import br.game.reuse.lesepremium.presenters.interfaces.BoardPresenter;
import br.game.reuse.lesepremium.presenters.interfaces.JokerPresenter;
import br.game.reuse.lesepremium.question.JokerBoard;

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
