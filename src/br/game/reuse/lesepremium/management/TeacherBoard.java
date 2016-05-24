/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.game.reuse.lesepremium.management;

import br.game.reuse.lesepremium.model.Teacher;
import br.game.reuse.lesepremium.model.dao.TeacherDAO;
import br.game.reuse.lesepremium.presenters.consolepresenters.ConsoleTeacherPresenter;
import br.game.reuse.lesepremium.presenters.interfaces.TeacherPresenter;

/**
 *
 * @author bruno
 */
public class TeacherBoard {

    private static TeacherBoard instance = null;
    private Teacher teacher = null;

    private TeacherBoard() {
        instance = new TeacherBoard();
    }

    public static TeacherBoard getInstance() {
        if (instance == null) {
            instance = new TeacherBoard();
        }
        return instance;
    }

    public boolean isAutenticated() {
        return (this.teacher != null);
    }

    public boolean autenticate() {
        TeacherPresenter teacherPresenter = new ConsoleTeacherPresenter();
        String[] data = teacherPresenter.getAutentication();
        this.teacher = TeacherDAO.authentication(data);
        return (this.teacher != null);
    }

    public void logout() {
        this.teacher = null;
    }
}
