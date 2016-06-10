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
import game.lese.presenters.console.ConsoleJokerPresenter;
import game.lese.presenters.interfaces.BoardPresenter;
import game.lese.presenters.interfaces.JokerPresenter;
import game.lese.question.JokerInfo;

/**
 *
 * @author cass
 */
public class JokerHouse extends House {
    private JokerInfo joker;
    
    public JokerHouse(int id, HouseOutcome outcome, DevelopmentPhase phase, JokerInfo j, int cycle) {
        super(id, outcome, phase, cycle);
        this.joker = j;
    }
    
    public JokerInfo getJoker() {
        return this.joker;
    }
    
    public void setJoker(JokerInfo j) {
        this.joker = j;
    }

    @Override
    protected void presentContent() {
        ContentTemplate contentTemplate = new ContentTemplate(getDevPhase());
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
