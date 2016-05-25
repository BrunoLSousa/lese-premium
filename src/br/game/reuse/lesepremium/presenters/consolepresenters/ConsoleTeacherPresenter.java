/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.game.reuse.lesepremium.presenters.consolepresenters;

import br.game.reuse.lesepremium.management.TeacherBoard;
import br.game.reuse.lesepremium.model.Teacher;
import br.game.reuse.lesepremium.presenters.interfaces.GamePresenter;
import br.game.reuse.lesepremium.presenters.interfaces.TeacherPresenter;
import java.io.Console;
import java.util.Scanner;

/**
 *
 * @author bruno
 */
public class ConsoleTeacherPresenter implements TeacherPresenter {

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

    @Override
    public void showFeedbackAutentication(boolean validation) {
        if (validation) {
            TeacherBoard teacher = TeacherBoard.getInstance();
            if (teacher.isAutenticated()) {
                System.out.println("\nAutenticação Realizada com sucesso!!!");
            } else {
                System.out.println("\nSenha incorreta!!!");
            }
        } else {
            System.out.println("\nEmail Inválido!!!");
        }
    }

    @Override
    public String[] getNewPassword() {
        Scanner scanner = new Scanner(System.in);
        Console console = System.console();
        String[] data = new String[3];
        GamePresenter game = new ConsoleGamePresenter();
        game.cleanConsole();
        System.out.println("Trocar Senha");
        System.out.print("Senha Atual: ");
        data[0] = scanner.nextLine();
        System.out.print("Nova Senha: ");
        data[1] = scanner.nextLine();
        System.out.print("Confirmação da Nova Senha: ");
        data[2] = scanner.nextLine();
        return data;
    }

    @Override
    public void showFeedbackChangePassword(boolean validationPassword, String[] data, int confirm) {
        if (validationPassword) {
            if(data[1].equals(data[2])){
                if(confirm == 1){
                    System.out.println("\nSenha atualizada com sucesso!!!");
                }else{
                    System.out.println("\nNão foi possível atualizar senha!!!");
                }
            }else{
                System.out.println("\nNova senha e Confirmação não conferem!!!");
            }
        } else {
            System.out.println("\nSenha atual inválida!!!");
        }
    }

}
