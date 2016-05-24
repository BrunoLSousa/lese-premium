/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.game.reuse.lesepremium.presenters.consolepresenters;

import br.game.reuse.lesepremium.presenters.interfaces.GamePresenter;
import br.game.reuse.lesepremium.presenters.interfaces.TeacherPresenter;
import java.io.Console;
import java.util.Scanner;

/**
 *
 * @author bruno
 */
public class ConsoleTeacherPresenter implements TeacherPresenter{

    @Override
    public String[] getAutentication() {
        Scanner scanner = new Scanner(System.in);
        Console console = System.console();
        String[] data = new String[2];
        GamePresenter game = new ConsoleGamePresenter();
        game.cleanConsole();
        System.out.println("Autenticação de Usuário");
        System.out.print("Email: ");
        data[0] = scanner.nextLine();
        System.out.print("Senha: ");
        data[1] = scanner.nextLine();
        return data;
    }
    
}
