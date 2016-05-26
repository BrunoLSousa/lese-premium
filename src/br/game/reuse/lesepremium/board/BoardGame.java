/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.game.reuse.lesepremium.board;

import br.game.reuse.lesepremium.house.House;
import br.game.reuse.lesepremium.management.Management;
import br.game.reuse.lesepremium.management.ManagementJoker;
import br.game.reuse.lesepremium.management.ManagementQuestion;
import br.game.reuse.lesepremium.management.TeacherBoard;
import br.game.reuse.lesepremium.presenters.consolepresenters.ConsoleBoardPresenter;
import br.game.reuse.lesepremium.presenters.consolepresenters.ConsoleGamePresenter;
import br.game.reuse.lesepremium.presenters.consolepresenters.ConsolePlayerPresenter;
import br.game.reuse.lesepremium.presenters.interfaces.BoardPresenter;
import br.game.reuse.lesepremium.presenters.interfaces.GamePresenter;
import br.game.reuse.lesepremium.presenters.interfaces.PlayerPresenter;

/**
 *
 * @author cass
 */
public class BoardGame {

    private Board board;
    private TeacherBoard teacherBoard;
    private BoardPresenter boardPresenter;
    private GamePresenter gamePresenter;
    private PlayerPresenter playerPresenter;

    public void createBoard(BoardBuilder builder) {

        builder.buildBoard();
        builder.buildProject();
        builder.buildHouses();
        this.teacherBoard = TeacherBoard.getInstance();
    }

    public void setUpGame() {
        int amountPlayers = boardPresenter.getNumPlayers();
        for (int i = 1; i <= amountPlayers; i++) {
            String playerName = playerPresenter.getPlayerName(i);
            String idPlayer = "p" + i;
            this.board.addPlayer(playerName, "red", idPlayer);
        }
        gamePresenter.cleanConsole();
    }

    public void init() {
        createBoard(new BoardBuilder());
        this.board = Board.getInstance();
        initPresenters();
        
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
        Management management = null;
        int opcao = 0;
        do {
            opcao = gamePresenter.menuTeacher();
            switch (opcao) {
                case 1:
                    management = new ManagementQuestion();
                    management.create();
                    break;
                case 2:
                    //Alter question. 
                    break;
                case 3:
                    management = new ManagementJoker();
                    management.create();
                    break;
                case 4:
                    //Alter joker. 
                    break;
                case 5:
                    this.teacherBoard.changePassword();
                    break;
                case 6:
                    this.teacherBoard.logout();
                    gamePresenter.showExitRestrictedArea();
                    break;
                default:
                    gamePresenter.showDefault();
                    break;
            }
        } while (this.teacherBoard.isAutenticated());
    }

    public void run() {
        String winner = null;
        boolean continueGame = true;

        while (winner == null && continueGame) {
            for (int i = 1; i <= this.board.getPlayers().size(); i++) {
                PlayerBoard player = this.board.getPlayer("p" + i);
                House playerHouse = this.board.getPlayerHouse(player);
                playerHouse.execute(player);
                continueGame = gamePresenter.continueGame();

                if (this.board.hasWinner()) {
                    winner = this.board.getWinner();
                    this.board.storePlayers();
                    boardPresenter.showWinner(player);
                    break;
                } else if (!continueGame) {
                    this.board.destroyPlayers();
                    boardPresenter.finalizeGame();
                }
            }
        }

    }
    
    protected void initPresenters() {
        boardPresenter = new ConsoleBoardPresenter();
        gamePresenter = new ConsoleGamePresenter();
        playerPresenter = new ConsolePlayerPresenter();
    }
}
