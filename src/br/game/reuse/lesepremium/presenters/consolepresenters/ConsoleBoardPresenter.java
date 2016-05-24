/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.game.reuse.lesepremium.presenters.consolepresenters;

import br.game.reuse.lesepremium.board.Board;
import br.game.reuse.lesepremium.board.PlayerBoard;
import br.game.reuse.lesepremium.presenters.interfaces.BoardPresenter;
import br.game.reuse.lesepremium.presenters.interfaces.GamePresenter;
import java.util.Scanner;

/**
 *
 * @author marlon
 */
public class ConsoleBoardPresenter implements BoardPresenter {
    
    @Override
    public void showStartScreen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void showSetUpScreen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int getNumPlayers() {
        Scanner scanner = new Scanner(System.in);
        int amount = 0;
        System.out.print("\n");
        while (amount < 1 || amount > 4) {
            System.out.print("Digite a quantidade de jogadores (máximo 4 players): ");
            amount = scanner.nextInt();
            if (amount < 1 || amount > 4) {
                System.out.println("Número de jogadores inválido. Para iniciar o jogo é necessário no mínimo 1 e no máximo 4 jogadores.");
            }
        }
        return amount;
    }
    
    @Override
    public void showWinner(PlayerBoard player) {
        System.out.println("Player " + player.getNickname() + " won!!!");
    }
    
    @Override
    public void showHeaderGame() {
        GamePresenter gamePresenter = new ConsoleGamePresenter();
        gamePresenter.cleanConsole();
        Board board = Board.getInstance();
        System.out.println("--------------------------------Informações----------------------------------\n");
        for (int i = 0; i < board.getPlayers().size(); i++) {
            PlayerBoard p = board.getPlayer("p" + (i + 1));
            String line = "Jogador: " + p.getNickname() + " | Pontuação: " + p.getCurrentScore() + " | Ciclo: " + p.getCurrentCycle() + " | Casa: " + (p.getCurrentPos() + 1);
            System.out.println(line);
        }
        System.out.println("-------------------------------------------------------------------------------");
    }
    
    @Override
    public void finalizeGame() {
        System.out.println("\f");
        System.out.println("Você finalizou o jogo!");
    }
    
}
