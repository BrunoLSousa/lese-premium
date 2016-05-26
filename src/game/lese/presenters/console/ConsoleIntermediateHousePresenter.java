/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.presenters.console;

import game.lese.house.House;
import game.lese.house.IntermediateHouse;
import game.lese.presenters.interfaces.MessagePresenter;
import java.util.Scanner;

/**
 *
 * @author bruno
 */
public class ConsoleIntermediateHousePresenter implements MessagePresenter{

    @Override
    public void showContent(House house) {
        IntermediateHouse initialHouse = (IntermediateHouse)house;
        System.out.println(initialHouse.getMessage());
    }
    
}
