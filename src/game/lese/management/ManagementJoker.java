/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.management;

import game.lese.model.Joker;
import game.lese.model.dao.JokerDAO;
import game.lese.presenters.console.ConsoleJokerPresenter;
import game.lese.presenters.interfaces.JokerPresenter;
import java.util.List;

/**
 *
 * @author bruno
 */
public class ManagementJoker implements Management {

    @Override
    public void create() {
        JokerPresenter jokerPresenter = new ConsoleJokerPresenter();
        Joker joker = jokerPresenter.getNewJoker();
        if (jokerPresenter.confirmResgisterJoker()) {
            JokerDAO.createJoker(joker);
            jokerPresenter.showMessage("Coringa cadastrado com sucesso!!!");
        } else {
            jokerPresenter.showMessage("Error ao criar curinga. Tente Novamente");
        }
    }

    @Override
    public void alter() { 
        List<Joker> allJokers = JokerDAO.selectAllJokers();
        JokerPresenter jokerPresenter = new ConsoleJokerPresenter();
        Joker selectedJoker = jokerPresenter.getJokerForEdit(allJokers);
        String infoMsg = "Error ao atualizar curinga. Tente Novamente";
        
        if (selectedJoker != null) {
            Joker result = jokerPresenter.editJoker(selectedJoker);
            
            if (result != null) {
                JokerDAO.updateJoker(result);
                infoMsg = "Coringa atualizado com sucessos";
            }
        }
        
        jokerPresenter.showMessage(infoMsg);
    }
    
}
