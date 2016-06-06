/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.presenters.console;

import game.lese.board.PlayerBoard;
import game.lese.house.House;
import game.lese.house.QuestionHouse;
import game.lese.model.Answer;
import game.lese.model.Phase;
import game.lese.model.Question;
import game.lese.model.dao.AnswerDAO;
import game.lese.model.dao.PhaseDAO;
import game.lese.presenters.interfaces.BoardPresenter;
import game.lese.presenters.interfaces.QuestionPresenter;
import game.lese.question.QuestionInfo;
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
    public void showQuestion(QuestionHouse question, PlayerBoard p) {
        String namePhase = "";
        switch (question.getDevPhase()) {
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
        System.out.println("Questão - " + namePhase + "\n");
        System.out.println("Pergunta Casa " + (p.getCurrentPos() + 1) + " - " + p.getNickname() + " responde.\n");
        BoardPresenter boardPresenter = new ConsoleBoardPresenter();
        System.out.println(question.getQuestion().getDescription());
    }

    @Override
    public List<String> showChoices(QuestionInfo question) {
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
            System.out.println("Fases:");
            int i = 1;
            for (Phase p : phases) {
                System.out.printf("\t%d - %s\n", i, p.getName());
                i++;
            }
            System.out.print("\nEscolha a fase da questão:");
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
    
    @Override
    public Question getQuestionForEdit(List<Question> questions) {
        Scanner scanner = new Scanner(System.in);
        int selectedQuestionId;
        System.out.println("QUESTÕES CADASTRADAS: ");
        for (Question q : questions) {
            System.out.println(q.getIdQueston() + ": " + q.getDescription());
        }
        
        System.out.println("Digite o ID da questão que deseja editar:");
        System.out.println("(digite -1 para retornar ao menu anterior)");
        selectedQuestionId = scanner.nextInt();
        if (selectedQuestionId < 0) {
            System.out.println("Retornando ao menu anterior"); 
            return null;
        }
        
        for (Question q: questions) {
            if (q.getIdQueston() == selectedQuestionId) {
                System.out.println("Editando questão " + q.getIdQueston() + ": " + q.getDescription());
                return q;
            }
        }
        
        System.out.println("Retornando ao menu anterior"); 
        return null;
    }
    
    @Override
    public Question editQuestion(Question question) {
        
        //EDITING QUESTION ITSELF
        Scanner scanner = new Scanner(System.in);
        String option, innerOption, userInput; //helper variables for menus
        System.out.println("Pressione enter para manter os valores atuais");
        System.out.print("Digite o enunciado da pergunta: ");
        System.out.println("(enunciado atual: " + question.getDescription() + ")");
        userInput = scanner.nextLine();
        if (userInput.length() > 0) question.setDescription(userInput);
        System.out.print("Digite uma explicação para a resposta correta: ");
        System.out.println("(explicação atual: " + question.getExplanation() + ")");
        userInput = scanner.nextLine();
        if (userInput.length() > 0) question.setExplanation(userInput);
        System.out.print("Digite a Pontuação da questão: ");
        System.out.println("(pontuação atual: " + question.getScore()+ ")");
        userInput = scanner.nextLine();
        if (userInput.length() > 0) question.setScore(Integer.parseInt(userInput));
        System.out.print("Digite a quantidade de casas válidas na questão: ");
        System.out.println("(quantidade atual: " + question.getHouse()+ ")");
        userInput = scanner.nextLine();
        if (userInput.length() > 0) question.setHouse(Integer.parseInt(userInput));
        System.out.print("Digite a fase da questão: ");
        System.out.println("(fase atual: " + question.getPhase().getIdPhase()+ ")");
        userInput = scanner.nextLine();
        if (userInput.length() > 0) question.setPhase(new Phase(Integer
                .parseInt(userInput)));
        
        //---------------------------------------------------------------------
        //EDITING ANSWERS RELATED TO THIS QUESTION
        do {
        System.out.println("Deseja editar as respostas desta pergunta? [s/n]");
        List<Answer> allAnswers = AnswerDAO.selectAnswerPerIdQuestion(question.getIdQueston());
        option = scanner.nextLine();
        if (option.length() == 0 || option.startsWith("s") ) {
            System.out.println("Lista de respostas: ");
            for (Answer a: allAnswers) {
                System.out.println(a.getIdAnswer()+ ": " + a.getDescription());
            }
            System.out.println("Deseja adicionar nova resposta ou deletar existente? [a|d]");
            System.out.println("Digite qualquer outro caracter para retornar ao menu anterior.");
            innerOption = scanner.nextLine();
            
            //Adding new question
            if (innerOption.startsWith("a")) {
                Answer answer = new Answer();
                System.out.print("Digite o enunciado da opção de resposta para essa questão: ");
                answer.setDescription(scanner.nextLine());
                System.out.println("Esta é a resposta correta? [s|n]");
                innerOption = scanner.nextLine();
                if (innerOption.startsWith("s")) {
                    answer.setStatus("1");
                } else {
                    answer.setStatus("0");
                }
                
                answer.setQuestion(question);
                answer.setIdAnswer(AnswerDAO.createAnswer(answer));
                allAnswers.add(answer);
                System.out.println("Resposta adicionada com sucesso.");
                
            } else if (innerOption.startsWith("d")) {
                //Deleting existing question
                System.out.println("Digite ID da resposta que deseja deletar:");
                int selectedAnswerId = Integer.parseInt(scanner.nextLine());
                
                //if user inputs negative value, we return to previous operation
                if (selectedAnswerId < 0) return question;
                Answer selectedAnswer = null;
                
                for (Answer a: allAnswers) {
                    if (a.getIdAnswer() == selectedAnswerId) {
                        selectedAnswer = a;
                        break;
                    }
                }

                if (selectedAnswer == null) return question;
                AnswerDAO.deleteAnswer(selectedAnswer);
                allAnswers.remove(selectedAnswer);
                System.out.println("Resposta deletada com sucesso.");
            } 

            question.setAnswer(allAnswers);
 
        }  } while (option.length() == 0 || option.startsWith("s"));
       
        
        return question;
    }
    

}
