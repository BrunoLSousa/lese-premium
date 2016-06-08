/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.board;

import game.lese.question.JokerInfo;
import game.lese.question.QuestionInfo;
import game.lese.house.FinalHouse;
import game.lese.outcome.BonusOutcome;
import game.lese.outcome.HouseOutcome;
import game.lese.house.House;
import game.lese.house.InitialHouse;
import game.lese.house.IntermediateHouse;
import game.lese.house.QuestionHouse;
import game.lese.house.JokerHouse;

/**
 *
 * @author cass
 */
public class BoardBuilder {

    private Board board;

    public void buildBoard() {
        this.board = Board.getInstance();
    }
    
    public void buildProject(){
        String name = "Projeto Merci";
        String description = "Apoio  informatizado  ao  controle  de  vendas  e  de  compras  da  mercearia  Pereira  &  Pereira Comercial Ltda";
        int cycle = 2;
        this.board.createProjectBoard(name, description, cycle);
    }

    public void buildHouses() {

        int nHousesPerPhase = 6;
        int idHouse = 0;
        int developmentCycles = this.board.getProjectBoard().getNumCycles();
        for (int cycle = 1; cycle <= developmentCycles; cycle++) {
            for (DevelopmentPhase phase : DevelopmentPhase.values()) {
                for (int i = 1; i <= nHousesPerPhase; i++) {
                    if (idHouse == 0 || idHouse == ((cycle -1) * nHousesPerPhase * DevelopmentPhase.values().length)) {
                        buildInitialHouse(idHouse, cycle);
                    } else {
                        if (cycle == developmentCycles && idHouse == (cycle * nHousesPerPhase * DevelopmentPhase.values().length) - 1) {
                            buildFinalHouse(idHouse, cycle);
                        } else {
                            if (cycle < developmentCycles && (idHouse == (cycle * nHousesPerPhase * DevelopmentPhase.values().length) - 1)) {
                                buildIntermediateHouse(idHouse, cycle);
                            } else {
                                if (i % 3 != 0) {
                                    buildQuestionHouse(phase, idHouse, cycle);
                                } else {
                                    buildJokerHouse(phase, idHouse, cycle);
                                }
                            }
                        }
                    }
                    idHouse++;
                }
            }
        }
    }

    public void buildInitialHouse(int idHouse, int cycle) {
        String message = "Parabéns! \nVocê acaba de ser contratado pela empresa XYZ para trabalhar como engenheiro de software. "
                + "Prepare-se, muitos desafios estão por vir. Novos conhecimentos serão obtidos e muitos obstáculos serão encontrados. "
                + "Mas fique tranquilo. No final tudo isso valerá apenas.\n\n"
                + "Você acaba de ganhar 10 pontos pela sua contratação.";

        HouseOutcome outcome = new BonusOutcome(1, 10, (float) 0.0);
        House initHouse = new InitialHouse(idHouse, outcome, null, message, cycle);
        this.board.addHouse(initHouse);
    }

    public void buildIntermediateHouse(int idHouse, int cycle) {
        String message = "Parabéns! \nVocê acaba de finalizar o ciclo "+cycle+" de desenvolvimento do software.\n\n"
                + "Prepare-se, agora para o inicio do ciclo "+ (cycle+1) + ".";

        HouseOutcome outcome = new BonusOutcome(2, 0, (float) 0.0);
        House intermediateHouse = new IntermediateHouse(idHouse, outcome, null, message, cycle);
        this.board.addHouse(intermediateHouse);
    }

    public void buildFinalHouse(int idHouse, int cycle) {
        String message = "Parabéns! \nVocê completou todos os ciclos de desenvolvimento do software.\n\n"
                + "Fim do jogo.";

        HouseOutcome outcome = new BonusOutcome(0, 0, (float) 0.0);
        House finalHouse = new FinalHouse(idHouse, outcome, null, message, cycle);
        this.board.addHouse(finalHouse);
    }

    public void buildQuestionHouse(DevelopmentPhase phase, int idHouse, int cycle) {
        QuestionInfo q = new QuestionInfo("desc", "explanation");
        q.addChoice("42", true);
        for (int j = 0; j < 3; j++) {
            q.addChoice(Integer.toString(j), false);
        }

        HouseOutcome outcome = new BonusOutcome(4, 5, (float) 1.0);
        House questionHouse = new QuestionHouse(idHouse, outcome, phase, q, cycle);
        this.board.addHouse(questionHouse);
    }

    public void buildJokerHouse(DevelopmentPhase phase, int idHouse, int cycle) {
        JokerInfo j = new JokerInfo("title", "explanation");
        HouseOutcome outcome = new BonusOutcome(4, 3, (float) 1.0);
        House jokerHouse = new JokerHouse(idHouse, outcome, phase, j, cycle);
        this.board.addHouse(jokerHouse);
    }

}
