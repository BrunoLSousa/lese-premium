/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.presenters.console;

import game.lese.main.DynamicAnnotation;
import game.lese.model.Player;
import game.lese.model.dao.PlayerDAO;
import game.lese.presenters.interfaces.GamePresenter;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author bruno
 */
public class ConsoleGamePresenter implements GamePresenter {

    @Override
    public int menu() {
        System.out.println("\nMenu Principal:");
        System.out.println("1 - Jogar");
        System.out.println("2 - Acessar Ranking");
        System.out.println("3 - Ajuda");
        
        
        if(DynamicAnnotation.PROJECT_MANAGMENT) {
            System.out.println("4 - Autenticar");
            System.out.println("5 - Exit\n");
        } else {
            System.out.println("4 - Exit\n");            
        }
        
        System.out.print("Escolha a opção: ");
        Scanner entrada = new Scanner(System.in);
        return entrada.nextInt();
    }

    @Override
    public void showRanking() {
        List<Player> players = new PlayerDAO().ranking();
        System.out.println("--------------------Ranking----------------------\n");
        if (!players.isEmpty()) {
            int position = 1;
            for (Player p : players) {
                System.out.println(position + " - " + p.getName() + " -------------------- " + p.getScore());
                position++;
            }
        } else {
            System.out.println("Ranking vazio.");
        }
    }

    @Override
    public void showHelp() {
        System.out.println("HELP");
    }

    @Override
    public void showExit() {
        System.out.println("Obrigado por ter participado do jogo! Esperamos que tenha gostado.\nAté breve!");
    }

    @Override
    public void showDefault() {
        System.out.println("Opção inexistente! Escolha novamente.");
    }

    @Override
    public void welcome() {
        System.out.println("Seja Bem Vindo ao LeSE!");
    }

    @Override
    public void cleanConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
                System.out.println("\f");
            }
        } catch (final Exception e) {
            System.out.println("Algo deu errado :(");
        }
    }
    
    @Override
    public boolean continueGame() {
        String option = "";
        do{
            System.out.print("Digite s ou enter para continuar ou n para finalizar o jogo: ");
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextLine();
            if(option.length() > 0 && !option.equals("s") && !option.equals("n")){
                System.out.println("Opção inválida! Tente novamente.");
            }
        }while(option.length() > 0 && !option.equals("s") && !option.equals("n"));
        return (option.equals("s") || option.length() == 0 );
    }

    @Override
    public int menuTeacher() {
        System.out.println("\n\nÁrea Restrita");
        System.out.println("\nMenu do Professor:");
        System.out.println("1 - Cadastrar Questões");
        System.out.println("2 - Alterar Questões");
        System.out.println("3 - Cadastrar Coringas");
        System.out.println("4 - Alterar Coringas");
        System.out.println("5 - Alterar Senha");
        System.out.println("6 - Sair da área restrita\n");
        System.out.print("Escolha a opção: ");
        Scanner entrada = new Scanner(System.in);
        return entrada.nextInt();
    }

    @Override
    public void showExitRestrictedArea() {
        System.out.println("Autenticação encerrada com sucesso!!!");
    }

}
