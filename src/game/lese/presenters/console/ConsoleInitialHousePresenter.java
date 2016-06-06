/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.presenters.console;

import game.lese.house.House;
import game.lese.house.InitialHouse;
import game.lese.presenters.interfaces.MessagePresenter;
import game.lese.presenters.interfaces.ProjectPresenter;

/**
 *
 * @author bruno
 */
public class ConsoleInitialHousePresenter implements MessagePresenter{
    
    @Override
    public void showContent(House house){
        InitialHouse initialHouse = (InitialHouse)house;
        System.out.println(initialHouse.getMessage());
        ProjectPresenter projectPresenter = new ConsoleProjectPresenter();
        projectPresenter.showProjects();
        System.out.println("\nVocê acaba de ganhar " + house.getOutcome().getPoints() + " pontos pela sua contratação. Avance " + house.getOutcome().getNumberOfHouses() + " casa.");
    }
}
