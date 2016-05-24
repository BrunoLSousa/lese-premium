/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.game.reuse.lesepremium.presenters.consolepresenters;

import br.game.reuse.lesepremium.house.House;
import br.game.reuse.lesepremium.house.IntermediateHouse;
import br.game.reuse.lesepremium.presenters.interfaces.MessagePresenter;
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
