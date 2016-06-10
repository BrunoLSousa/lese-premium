/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.presenters.console;

import game.lese.board.Board;
import game.lese.presenters.interfaces.ProjectPresenter;
import game.lese.question.ProjectInfo;

/**
 *
 * @author marlon
 */
public class ConsoleProjectPresenter implements ProjectPresenter {

    @Override
    public void showProjects() {
        Board board = Board.getInstance();
        ProjectInfo project = board.getProject();
        System.out.println("\nVocê vai atuar no " + project.getName() + "\n" + "Descrição do Projeto: " + project.getDescription() + "\nNúmero de ciclos: " + project.getNumCycles());
    }
    
}
