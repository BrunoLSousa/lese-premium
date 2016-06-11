/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.outcome;

import game.lese.board.PlayerBoard;
import game.lese.house.House;
import game.lese.house.IntermediateHouse;
import game.lese.house.QuestionHouse;

/**
 *
 * @author cass
 */
public class BonusOutcome extends HouseOutcome {

    public BonusOutcome(int nHouses, int points) {
        super(nHouses, points);
    }

    @Override
    public void apply(PlayerBoard p, House house) {
        if (house instanceof QuestionHouse) {
            QuestionHouse question = (QuestionHouse) house;
            if (question.isCorrect()) {
                p.creditPoints(getPoints());
                p.move(getNumberOfHouses());
            } else {
                p.debitPoints(getPoints());
                p.move(-getNumberOfHouses());
            }
        } else {
            if (house instanceof IntermediateHouse) {
                p.increaseCycle();
            }
            p.creditPoints(getPoints());
            p.move(getNumberOfHouses());
        }
    }
}
