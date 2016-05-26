/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.view;

import game.lese.model.Player;
import game.lese.view.interfaces.AbstractComponent;

/**
 *
 * @author bruno
 */
public class BoardView {

    private Player student = null;
    
    public BoardView(String nameStudent){
        this.student = new Player(nameStudent);
    }
    
    /*public static void main(String[] args) {
        AbstractComponent component = new ComponentHouseView();
        component.addComponent(component);
        AbstractComponent component2 = new ComponentScoreboardView();
        component2.addComponent(component);
        AbstractComponent component3 = new ComponentDiceView();
        component3.addComponent(component);
        component.setSize(1000, 700);
        component.setVisible(true);
        component.setResizable(false);
    }*/
    
    public void createStudent(){
        
    }

}
