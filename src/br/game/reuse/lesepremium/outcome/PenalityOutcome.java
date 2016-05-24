/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.game.reuse.lesepremium.outcome;

import br.game.reuse.lesepremium.board.PlayerBoard;
import br.game.reuse.lesepremium.house.House;
import br.game.reuse.lesepremium.house.JokerHouse;
import br.game.reuse.lesepremium.question.JokerBoard;

/**
 *
 * @author cass
 */
public class PenalityOutcome extends HouseOutcome {

    public PenalityOutcome(int nHouses, int points) {
        super(points, nHouses, 1);
    }

    @Override
    public void apply(PlayerBoard p, House house) {
        p.debitPoints(getPoints());
        p.move(getNumberOfHouses());
    }

}
