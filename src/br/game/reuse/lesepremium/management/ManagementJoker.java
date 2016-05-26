/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.game.reuse.lesepremium.management;

import br.game.reuse.lesepremium.model.Joker;
import br.game.reuse.lesepremium.model.dao.JokerDAO;
import br.game.reuse.lesepremium.presenters.consolepresenters.ConsoleJokerPresenter;
import br.game.reuse.lesepremium.presenters.interfaces.JokerPresenter;

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
