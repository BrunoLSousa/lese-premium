/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.presenters.console;

import game.lese.house.FinalHouse;
import game.lese.house.House;
import game.lese.presenters.interfaces.MessagePresenter;

/**
 *
 * @author bruno
 */
public class ConsoleFinalHousePresenter implements MessagePresenter{

    @Override
    public void showContent(House house) {
        FinalHouse initialHouse = (FinalHouse)house;
        System.out.println(initialHouse.getMessage());
    }
    
}
