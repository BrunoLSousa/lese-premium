/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.board;

import game.lese.house.House;
import game.lese.main.DynamicAnnotation;
import game.lese.presenters.console.ConsoleBoardPresenter;
import game.lese.presenters.console.ConsoleGamePresenter;
import game.lese.presenters.console.ConsolePlayerPresenter;
import game.lese.presenters.interfaces.BoardPresenter;
import game.lese.presenters.interfaces.GamePresenter;
import game.lese.presenters.interfaces.PlayerPresenter;
/**
 *
 * @author cass
 */
public class BoardGame {

    private Board board;
    private final BoardPresenter boardPresenter;
    private final GamePresenter gamePresenter;
    private final PlayerPresenter playerPresenter;
    
    public BoardGame() {
        boardPresenter = new ConsoleBoardPresenter();
        gamePresenter = new ConsoleGamePresenter();
        playerPresenter = new ConsolePlayerPresenter();
    }
   
    public void init() {
        createBoard(new BoardBuilder());
        this.board = Board.getInstance();
        setUpGame();
        run();
    }
    
    private void createBoard(BoardBuilder builder) {
        builder.buildBoard();
        builder.buildProject();
        builder.buildHouses();
    }

    private void setUpGame() {
        int amountPlayers = 1;
        
        if(DynamicAnnotation.MULTIPLAYER) {
            amountPlayers = boardPresenter.getNumPlayers();
        }
        
        for (int i = 1; i <= amountPlayers; i++) {
            String playerName = playerPresenter.getPlayerName(i);
            //TODO(cass): set diferent colors for each player
            this.board.addPlayer(playerName, "red");
        }
        gamePresenter.cleanConsole();
    }
    
    private void run() {
        String winner = null;
        boolean continueGame = true;
        
        while (winner == null && continueGame) {
            for (PlayerBoard player : this.board.getPlayers()) {
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
                    break;
                }
            }
        }

    }
}
