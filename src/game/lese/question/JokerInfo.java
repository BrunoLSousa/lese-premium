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
public class JokerInfo {
    private String title;
    private String description;
    
    public JokerInfo(String title, String description){
        this.title = title;
        this.description = description;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public String getDescription(){
        return this.description;
    }
    
}
