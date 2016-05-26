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

/**
 *
 * @author bruno
 */
public class ManagementJoker implements Management{

    @Override
    public void create() {
        JokerPresenter jokerPresenter = new ConsoleJokerPresenter();
        Joker joker = jokerPresenter.getNewJoker();
        if (jokerPresenter.confirmResgisterJoker()) {
            JokerDAO.createJoker(joker);
            jokerPresenter.showMessage("Coringa cadastrado com sucesso!!!");
        }
    }

    @Override
    public void alter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
