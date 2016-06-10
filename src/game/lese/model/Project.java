/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.model;

/**
 *
 * @author bruno
 */
public class Project {
    
    private String name;
    private String description;
    private int nCycles;

    public Project(String newName, String newDescription, int numCycles) {
        this.name = newName;
        this.description = newDescription;
        this.nCycles = numCycles;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public int getNumCycles() {
        return nCycles;
    }

    public void setNumCycles(int cycles) {
        this.nCycles = cycles;
    }
    
}
