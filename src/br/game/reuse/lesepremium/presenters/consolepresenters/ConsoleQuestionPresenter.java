/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.game.reuse.lesepremium.presenters.consolepresenters;

import br.game.reuse.lesepremium.board.PlayerBoard;
import br.game.reuse.lesepremium.house.House;
import br.game.reuse.lesepremium.house.QuestionHouse;
import br.game.reuse.lesepremium.model.Answer;
import br.game.reuse.lesepremium.model.Phase;
import br.game.reuse.lesepremium.model.Question;
import br.game.reuse.lesepremium.model.dao.PhaseDAO;
import br.game.reuse.lesepremium.model.dao.QuestionDAO;
import br.game.reuse.lesepremium.presenters.interfaces.BoardPresenter;
import br.game.reuse.lesepremium.presenters.interfaces.QuestionPresenter;
import br.game.reuse.lesepremium.question.QuestionBoard;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author marlon
 */
public class ConsoleQuestionPresenter implements QuestionPresenter {

    
    // -------------------------------------------------------------------------
    // PLAYER RELATED METHODS
    // -------------------------------------------------------------------------
    @Override
    public void showQuestion(QuestionBoard question, PlayerBoard p) {
        System.out.println("Pergunta Casa " + (p.getCurrentPos() + 1) + " - " + p.getNickname() + " responde.\n");
        BoardPresenter boardPresenter = new ConsoleBoardPresenter();
        System.out.println(question.getDescription());
    }

    @Override
    public List<String> showChoices(QuestionBoard question) {
        int option = 1;
        List<String> choices = new ArrayList<>();
        for (String choice : question.getChoices()) {
            choices.add(choice);
        }
        Collections.shuffle(choices);
        for (String choice : choices) {
            System.out.println(option + " - " + choice);
            option++;
        }
        return choices;
    }

    @Override
    public String getPlayerAnswer(List<String> choices) {
        int option = 0;
        while (option < 1 || option > choices.size()) {
            System.out.print("Digite a opção que você acha correta: ");
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();
            if (option < 1 || option > choices.size()) {
                System.out.println("Opção não existente. Digite a opção de 1 a " + choices.size() + " para responder a questão!!!");
            }
        }
        return choices.get(option - 1);
    }

    @Override
    public void showFeedback(House house, PlayerBoard p) {
        QuestionHouse question = (QuestionHouse) house;
        if (question.isCorrect()) {
            System.out.println("\nParabéns " + p.getNickname() + ", você acertou!!!\n");
            System.out.println("Explicação da resposta correta:");
            System.out.println(question.getQuestion().getEplanation() + "\n");
            System.out.println("Você ganhou " + question.getOutcome().getPoints() + " pontos. Avance " + question.getOutcome().getNumberOfHouses() + " casa(s).");
        } else {
            System.out.println("\nQue pena " + p.getNickname() + ", você erou!!!\n");
            System.out.println("A resposta correta é: ");
            System.out.println(question.getQuestion().getAnswer() + "\n");
            System.out.println("Explicação da resposta correta:");
            System.out.println(question.getQuestion().getEplanation() + "\n");
            System.out.println("Você perdeu " + question.getOutcome().getPoints() + " pontos. Recue " + question.getOutcome().getNumberOfHouses() + " casa(s).\n");
        }
    }

    @Override
    public void showMessage(String feedback) {
        System.out.println(feedback + "\n");
    }
    
    
    
    
    // -------------------------------------------------------------------------
    // TEACHER RELATED METHODS (ADMIN / MANAGEMENT)
    // -------------------------------------------------------------------------
    @Override
    public Question getNewQuestion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--------------------------Cadastro de Questões---------------------------\n");
        Question question = new Question();
        Phase phase = getPhaseQuestion();
        question.setPhase(phase);
        System.out.print("Digite o enunciado da pergunta: ");
        question.setDescription(scanner.nextLine());
        System.out.print("Digite uma explicação para a resposta correta: ");
        question.setExplanation(scanner.nextLine());
        System.out.print("Digite a Pontuação da questão: ");
        question.setScore(scanner.nextInt());
        System.out.print("Digite a quantidade de casas válidas na questão: ");
        question.setHouse(scanner.nextInt());
        List<Answer> answers = getAnswerQuestion();
        setAnswerCorrect(answers);
        question.setAnswer(answers);
        return question;
    }

    private Phase getPhaseQuestion() {
        Scanner scanner = new Scanner(System.in);
        List<Phase> phases = PhaseDAO.selectAllPhases();
        int option = 0;
        while (option < 1 || option > phases.size()) {
            System.out.println("Phases:");
            int i = 1;
            for (Phase p : phases) {
                System.out.printf("\t%d - %s\n", i, p.getName());
                i++;
            }
            System.out.print("\nEscolha a phase da questão:");
            option = scanner.nextInt();
            if (option < 1 || option > phases.size()) {
                System.out.print("Opção inválida!!! Tente novamente.");
            }
        }
        return phases.get(option - 1);
    }

    private List<Answer> getAnswerQuestion() {
        Scanner scanner = new Scanner(System.in);
        String option = "";
        List<Answer> answers = new ArrayList<Answer>();
        while (!option.equals("n")) {
            option = "";
            Answer answer = new Answer();
            System.out.print("Digite o enunciado da opção de resposta para essa questão: ");
            answer.setDescription(scanner.nextLine());
            answers.add(answer);
            while (!option.equals("s") && !option.equals("n")) {
                System.out.print("Deseja cadastrar nova opção? s/n: ");
                option = scanner.nextLine();
                if (!option.equals("s") && !option.equals("n")) {
                    System.out.println("Opção inválida!!! Tente novamente.");
                }
            }
        }
        return answers;
    }

    private void setAnswerCorrect(List<Answer> answers) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual opção é a correta?");
        for (int i = 1; i <= answers.size(); i++) {
            System.out.printf("\t%d - %s\n", i, answers.get(i - 1).getDescription());
        }
        int option = 0;
        while (option < 1 || option > answers.size()) {
            System.out.print("Opção: ");
            option = scanner.nextInt();
            if (option < 1 || option > answers.size()) {
                System.out.println("Opção inválida!!! Tente novamente.");
            }
        }
        answers.get(option - 1).setStatus("1");
    }

    @Override
    public boolean confirmResgisterQuestion() {
        Scanner scanner = new Scanner(System.in);
        String option = "";
        while (!option.equals("s") && !option.equals("n")) {
            System.out.print("Deseja realmente cadastrar essa questão? s/n: ");
            option = scanner.nextLine();
            if (!option.equals("s") && !option.equals("n")) {
                System.out.println("Opção inválida!!! Tente novamente.\n");
            }
        }
        return option.equals("s");
    }

}
