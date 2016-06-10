/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.management;

import game.lese.model.Teacher;
import game.lese.model.dao.TeacherDAO;
import game.lese.presenters.console.ConsoleTeacherPresenter;
import game.lese.presenters.interfaces.TeacherPresenter;
/**
 *
 * @author bruno
 */
public class Manager {

    private Teacher teacher;

    public Manager() {
        this.teacher = null;
    }
    
    public boolean isAuthenticated() {
        return (this.teacher != null);
    }

    public boolean logIn() {
        //email:teste@teste.com senha: 123456 
        TeacherPresenter teacherPresenter = new ConsoleTeacherPresenter();
        String[] data = teacherPresenter.getAutentication();
        this.teacher = TeacherDAO.authenticate(data[0], data[1]);
        boolean success = this.teacher != null;
        teacherPresenter.showFeedbackAutentication(success);
        return success;
    }

    public void logout() {
        this.teacher = null;
    }

    public void changePassword() {
        TeacherPresenter teacherPresenter = new ConsoleTeacherPresenter();
        String[] data = teacherPresenter.getNewPassword();
        Teacher t = TeacherDAO.authenticate(this.teacher.getEmail(), data[0]);
        int confirm = 0;
        if (t != null) {
            if (data[1].equals(data[2])) {
                confirm = TeacherDAO.changePassword(t.getCpf(), data[1]);
            }
        }
        teacherPresenter.showFeedbackChangePassword((t!=null), data, confirm);
    }
}
