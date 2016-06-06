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
import game.lese.presenters.console.ConsoleIntermediateHousePresenter;
import game.lese.presenters.interfaces.BoardPresenter;
import game.lese.presenters.interfaces.MessagePresenter;

/**
 *
 * @author bruno
 */
public class IntermediateHouse extends House {

    private final String message;

    public IntermediateHouse(int id, HouseOutcome outcome, DevelopmentPhase phase, String msg, int cycle) {
        super(id, outcome, phase, cycle);
        this.message = msg;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    protected void presentContent() {
//        System.out.println(this.message);
    }

    @Override
    protected void interactWithPlayer(PlayerBoard p) {
        BoardPresenter boardPresenter = new ConsoleBoardPresenter();
        boardPresenter.showHeaderGame();
        MessagePresenter intermediatePresenter = new ConsoleIntermediateHousePresenter();
        intermediatePresenter.showContent(this);
    }

    @Override
    protected void applyOutcome(PlayerBoard p) {
        HouseOutcome outcome = getOutcome();
        outcome.apply(p, this);
    }

}
