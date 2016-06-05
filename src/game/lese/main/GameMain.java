/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.main;

import game.lese.board.BoardGame;
import game.lese.management.ManagementMain;
import game.lese.presenters.console.ConsoleGamePresenter;
import game.lese.presenters.interfaces.GamePresenter;

/**
 *
 * @author cass
 */
public class GameMain {
    
    private final GamePresenter gamePresenter;
    
    
    public GameMain() {
        gamePresenter = new ConsoleGamePresenter();
    }
    
    public void showMenu() {
        gamePresenter.welcome();
        int opcao;
        do {
            opcao = gamePresenter.menu();
            switch (opcao) {
                case 1:
                    BoardGame game = new BoardGame();
                    game.init();
                    break;
                case 2:
                    gamePresenter.showRanking();
                    break;
                case 3:
                    gamePresenter.showHelp();
                    break;
                case 4:
                    ManagementMain mgt = new ManagementMain();
                    mgt.showMenu();
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
}
