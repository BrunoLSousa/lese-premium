/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.presenters.console;

import game.lese.board.PlayerBoard;
import game.lese.presenters.interfaces.HousePresenter;

/**
 *
 * @author marlon
 */
public class ConsoleHousePresenter implements HousePresenter {

    @Override
    public void showHouseInfo(PlayerBoard p) {
//        System.out.println("Casa Atual: " + (p.getPawnPosition().getId() + 1));
//        System.out.println("Pontuação Atual: " + p.getCurrentScore());
    }
    
}
