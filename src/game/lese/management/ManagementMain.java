/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.management;

import game.lese.presenters.console.ConsoleGamePresenter;
import game.lese.presenters.interfaces.GamePresenter;

/**
 *
 * @author cass
 */
public class ManagementMain {
    private Manager teacherBoard;
    private GamePresenter gamePresenter;

    
    public ManagementMain() {
        gamePresenter = new ConsoleGamePresenter();
        teacherBoard = Manager.getInstance();
    }
    
    public void showMenu() {

        if(this.teacherBoard.autenticate()){
            Management management;
            int opcao;
            do {
                opcao = gamePresenter.menuTeacher();
                switch (opcao) {
                    case 1:
                        management = new ManagementQuestion();
                        management.create();
                        break;
                    case 2:
                        management = new ManagementQuestion();
                        management.alter();
                        break;
                    case 3:
                        management = new ManagementJoker();
                        management.create();
                        break;
                    case 4:
                        management = new ManagementJoker();
                        management.alter();
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
    }
}
