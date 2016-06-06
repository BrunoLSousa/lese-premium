/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.board;

import game.lese.house.House;

/**
 *
 * @author cass
 */
public class PlayerBoard {

    private final String nickname;
    private int accumulatedPoints;
    private final Pawn pawn;
    private int currentPos;
    private int currentCycle;

    public PlayerBoard(String nickname) {
        this.nickname = nickname;
        this.accumulatedPoints = 0;
        this.pawn = new Pawn("red");
        this.currentPos = 0;
        this.currentCycle = 1;
    }

    public String getNickname() {
        return this.nickname;
    }

    public House getPawnPosition() {
        Board board = Board.getInstance();
        return board.getPlayerHouse(this);
    }

    public int getCurrentPos() {
        return this.currentPos;
    }

    public int getCurrentCycle() {
        return this.currentCycle;
    }

    public void move(int nHouses) {
        int finalHouseCycle = (this.currentCycle * DevelopmentPhase.values().length * 6) - 1;
        int initialHouseCycle = ((this.currentCycle - 1) * DevelopmentPhase.values().length * 6) + 1;
        if ((this.currentPos + nHouses) > finalHouseCycle) {
            this.currentPos = finalHouseCycle;
//            QuestionPresenter questionPresenter = new ConsoleQuestionPresenter();
//            String feedback = "Você não consegue avançar as " + nHouses + " casas. Por isso, vá para casa " + finalHouseCycle;
//            questionPresenter.showMessage(feedback);
        } else if ((this.currentPos + nHouses) < initialHouseCycle) {
            this.currentPos = initialHouseCycle;
//            QuestionPresenter questionPresenter = new ConsoleQuestionPresenter();
//            String feedback = "Você não consegue voltar as " + (-nHouses) + " casas. Por isso, volte para casa " + initialHouseCycle;
//            questionPresenter.showMessage(feedback);
        } else {
            this.currentPos = this.currentPos + nHouses;
        }
    }

    public void increasingCycle() {
        this.currentCycle = this.currentCycle + 1;
    }

    public void creditPoints(int earnedPoints) {
        this.accumulatedPoints += earnedPoints;
    }

    public void debitPoints(int earnedPoints) {
        this.accumulatedPoints -= earnedPoints;
    }

    public int getCurrentScore() {
        return this.accumulatedPoints;
    }

}
