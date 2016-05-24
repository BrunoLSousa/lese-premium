/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.game.reuse.lesepremium.board;

import br.game.reuse.lesepremium.house.House;
import br.game.reuse.lesepremium.management.TeacherBoard;
import br.game.reuse.lesepremium.presenters.consolepresenters.ConsoleBoardPresenter;
import br.game.reuse.lesepremium.presenters.consolepresenters.ConsoleGamePresenter;
import br.game.reuse.lesepremium.presenters.consolepresenters.ConsolePlayerPresenter;
import br.game.reuse.lesepremium.presenters.consolepresenters.ConsoleTeacherPresenter;
import br.game.reuse.lesepremium.presenters.interfaces.BoardPresenter;
import br.game.reuse.lesepremium.presenters.interfaces.GamePresenter;
import br.game.reuse.lesepremium.presenters.interfaces.PlayerPresenter;
import br.game.reuse.lesepremium.presenters.interfaces.TeacherPresenter;

/**
 *
 * @author cass
 */
public class BoardGame {

    private Board board;
    private TeacherBoard teacherBoard;

    public void creatBoard(BoardBuilder builder) {

        builder.buildBoard();
        builder.buildProject();
        builder.buildHouses();
        this.teacherBoard = TeacherBoard.getInstance();
    }

    public void setUpGame() {
        PlayerPresenter playerPresenter = new ConsolePlayerPresenter();
        BoardPresenter boardPresenter = new ConsoleBoardPresenter();
        int amountPlayers = boardPresenter.getNumPlayers();
        for (int i = 1; i <= amountPlayers; i++) {
            String playerName = playerPresenter.getPlayerName(i);
            String idPlayer = "p" + i;
            this.board.addPlayer(playerName, "red", idPlayer);
        }
        GamePresenter gamePresenter = new ConsoleGamePresenter();
        gamePresenter.cleanConsole();
    }

    public void init() {
        creatBoard(new BoardBuilder());
        this.board = Board.getInstance();
        GamePresenter gamePresenter = new ConsoleGamePresenter();
        gamePresenter.welcome();
        int opcao = 0;
        do {
            opcao = gamePresenter.menu();
            switch (opcao) {
                case 1:
                    setUpGame();
                    run();
                    break;
                case 2:
                    gamePresenter.showRanking();
                    break;
                case 3:
                    gamePresenter.showHelp();
                    break;
                case 4:
                    boolean authenticate = this.teacherBoard.autenticate();
                    if(authenticate){
                        menuTeacher();
                    }
                    break;
                case 5:
                    gamePresenter.showExit();
                    break;
                default:
                    gamePresenter.showDefault();
                    break;
            }
        } while (opcao != 5);
    }

    public void menuTeacher() {
        GamePresenter gamePresenter = new ConsoleGamePresenter();
        int opcao = 0;
        do {
            opcao = gamePresenter.menuTeacher();
            switch (opcao) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    this.teacherBoard.logout();
                    gamePresenter.showExit();
                    break;
                default:
                    gamePresenter.showDefault();
                    break;
            }
        } while (this.teacherBoard.isAutenticated());
    }

    public void run() {
        String winner = null;
        BoardPresenter boardPresenter = new ConsoleBoardPresenter();
        boolean continueGame = true;

        while (winner == null && continueGame) {
            for (int i = 1; i <= this.board.getPlayers().size(); i++) {
                PlayerBoard player = this.board.getPlayer("p" + i);
                House playerHouse = this.board.getPlayerHouse(player);
                playerHouse.execute(player);
                GamePresenter gamePresenter = new ConsoleGamePresenter();
                continueGame = gamePresenter.continueGame();

                if (this.board.hasWinner()) {
                    winner = this.board.getWinner();
                    this.board.storePlayers();
                    boardPresenter.showWinner(player);
                    break;
                } else if (!continueGame) {
                    boardPresenter.finalizeGame();
                }
            }
        }

    }
}
