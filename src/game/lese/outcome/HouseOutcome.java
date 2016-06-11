/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.outcome;

import game.lese.board.PlayerBoard;
import game.lese.house.House;

/**
 *
 * @author cass
 */
public abstract class HouseOutcome {
    private int points;
    private int nHouses;
    
    protected HouseOutcome(int nHouses, int points) {
        this.points = points;
        this.nHouses = nHouses;
    }
        
    public int getPoints() {
        return this.points;
    }
    
    public int getNumberOfHouses() {
        return this.nHouses;
    }
    
    public abstract void apply(PlayerBoard p, House house);
}
