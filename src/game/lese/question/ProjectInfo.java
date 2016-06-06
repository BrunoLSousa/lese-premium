/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.question;

/**
 *
 * @author bruno
 */
public class ProjectInfo {
    private String name;
    private String description;
    private int nCycles;
    
    public ProjectInfo(String name, String description, int numCycles){
        this.name = name;
        this.description = description;
        this.nCycles = numCycles;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public int getNumCycles(){
        return this.nCycles;
    }
}
