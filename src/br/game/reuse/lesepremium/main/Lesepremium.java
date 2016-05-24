/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.game.reuse.lesepremium.main;

import br.game.reuse.lesepremium.board.BoardGame;

/**
 *
 * @author bruno
 */
public class Lesepremium {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BoardGame game = new BoardGame();
        game.init();
    }

}
