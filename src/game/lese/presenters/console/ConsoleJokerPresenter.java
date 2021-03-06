/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.presenters.console;

import game.lese.house.House;
import game.lese.house.JokerHouse;
import game.lese.model.Joker;
import game.lese.model.Phase;
import game.lese.model.dao.PhaseDAO;
import game.lese.outcome.BonusOutcome;
import game.lese.presenters.interfaces.JokerPresenter;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author bruno
 */
public class ConsoleJokerPresenter implements JokerPresenter {

    @Override
    public void showContent(House house) {
        String namePhase = "";
        switch (house.getDevPhase()) {
            case Inception:
                namePhase = "Requisitos";
                break;
            case Elaboration:
                namePhase = "Projeto";
                break;
            case Construction:
                namePhase = "Implementação";
                break;
            case Verification:
                namePhase = "Teste";
                break;
            case Transition:
                namePhase = "Implantação";
                break;
        }
        System.out.println("Coringa - " + namePhase + "\n");
        JokerHouse joker = (JokerHouse) house;
        if (joker.getOutcome() instanceof BonusOutcome) {
            System.out.println(joker.getJoker().getTitle() + "\n" + joker.getJoker().getDescription()
                    + "\nVocê ganhou " + joker.getOutcome().getPoints() + " pontos.\n");
        } else {
            System.out.println(joker.getJoker().getTitle() + "\n" + joker.getJoker().getDescription()
                    + "\nVocê perdeu " + joker.getOutcome().getPoints() + " pontos.\n");
        }
    }

    @Override
    public void showMessage(String feedback) {
        System.out.println(feedback + "\n");
    }

    @Override
    public Joker getNewJoker() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--------------------------Cadastro de Coringas---------------------------\n");
        Joker joker = new Joker();
        Phase phase = getPhaseJokers();
        joker.setPhase(phase);
        System.out.print("Digite a mensagem do coringa: ");
        joker.setDescription(scanner.nextLine());
        System.out.print("Digite a Pontuação da questão: ");
        joker.setScore(scanner.nextInt());
        System.out.print("Digite a quantidade de casas válidas na questão: ");
        joker.setAction(getActionJoker());
        return joker;
    }

    private Phase getPhaseJokers() {
        Scanner scanner = new Scanner(System.in);
        List<Phase> phases = PhaseDAO.selectAllPhases();
        int option = 0;
        while (option < 1 || option > phases.size()) {
            System.out.println("Phases:");
            int i = 1;
            for (Phase p : phases) {
                switch (p.getIdPhase()) {
                    case 1:
                        System.out.printf("\t%d - %s\n", i, "Requisitos");
                        break;
                    case 2:
                        System.out.printf("\t%d - %s\n", i, "Projeto");
                        break;
                    case 3:
                        System.out.printf("\t%d - %s\n", i, "Implementação");
                        break;
                    case 4:
                        System.out.printf("\t%d - %s\n", i, "Teste");
                        break;
                    case 5:
                        System.out.printf("\t%d - %s\n", i, "Implantação");
                        break;
                }
                i++;
            }
            System.out.print("\nEscolha a phase do coringa:");
            option = scanner.nextInt();
            if (option < 1 || option > phases.size()) {
                System.out.print("Opção inválida!!! Tente novamente.");
            }
        }
        return phases.get(option - 1);
    }

    private String getActionJoker() {
        Scanner scanner = new Scanner(System.in);
        String option = "";
        while (!option.equals("0") && !option.equals("1")) {
            option = "";
            System.out.println("Qual a ação desse coringa? ");
            System.out.printf("\t0- Penalidade\n");
            System.out.printf("\t1- Bonus\n");
            System.out.print("Escolha a opção: ");
            option = scanner.nextLine();
            if (!option.equals("0") && !option.equals("1")) {
                System.out.println("Opção inválida!!! Tente novamente.");
            }
        }
        return option;
    }

    @Override
    public boolean confirmResgisterJoker() {
        Scanner scanner = new Scanner(System.in);
        String option = "";
        while (!option.equals("s") && !option.equals("n")) {
            System.out.print("Deseja realmente cadastrar esse coringa? s/n: ");
            option = scanner.nextLine();
            if (!option.equals("s") && !option.equals("n")) {
                System.out.println("Opção inválida!!! Tente novamente.\n");
            }
        }
        return option.equals("s");
    }

    @Override
    public Joker getJokerForEdit(List<Joker> allJokers) {
        Scanner scanner = new Scanner(System.in);
        int selectedJokerId;
        System.out.println("CORINGAS CADASTRADOS: ");
        for (Joker j : allJokers) {
            System.out.println(j.getIdJoker() + ": " + j.getDescription());
        }

        System.out.println("Digite o ID do coringa que deseja editar:");
        System.out.println("(digite -1 para retornar ao menu anterior)");
        selectedJokerId = scanner.nextInt();
        if (selectedJokerId < 0) {
            System.out.println("Retornando ao menu anterior");
            return null;
        }

        for (Joker j : allJokers) {
            if (j.getIdJoker() == selectedJokerId) {
                System.out.println("Editando coringa " + j.getIdJoker() + ": " + j.getTitle());
                return j;
            }
        }

        System.out.println("Retornando ao menu anterior");
        return null;
    }

    @Override
    public Joker editJoker(Joker selectedJoker) {

        Scanner scanner = new Scanner(System.in);
        String userInput;
        System.out.println("Pressione enter para manter os valores atuais");
        System.out.print("Digite a mensagem do coringa");
        System.out.println("(mensagem atual: " + selectedJoker.getDescription() + ")");
        userInput = scanner.nextLine();
        if (userInput.length() > 0) {
            selectedJoker.setDescription(userInput);
        }
        System.out.print("Digite a Pontuação do coringa: ");
        System.out.println("(pontuação atual: " + selectedJoker.getScore() + ")");
        userInput = scanner.nextLine();
        if (userInput.length() > 0) {
            selectedJoker.setScore(Integer.parseInt(userInput));
        }
        System.out.print("Digite a quantidade de casas válidas do coringa: ");
        System.out.println("(quantidade atual: " + selectedJoker.getHouse() + ")");
        userInput = scanner.nextLine();
        if (userInput.length() > 0) {
            selectedJoker.setHouse(Integer.parseInt(userInput));
        }
        System.out.print("Digite a fase do coringa: ");
        System.out.println("(fase atual: " + selectedJoker.getPhase().getIdPhase() + ")");
        userInput = scanner.nextLine();
        if (userInput.length() > 0) {
            selectedJoker.setPhase(new Phase(Integer
                    .parseInt(userInput)));
        }

        return selectedJoker;
    }

}
