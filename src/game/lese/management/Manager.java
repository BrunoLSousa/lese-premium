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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bruno
 */
public class Manager {

    private static Manager instance = null;
    private Teacher teacher;

    private Manager() {
        this.teacher = null;
    }

    public static Manager getInstance() {
        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    public boolean isAutenticated() {
        return (this.teacher != null);
    }

    public boolean autenticate() {
        try {
            TeacherPresenter teacherPresenter = new ConsoleTeacherPresenter();
            String[] data = teacherPresenter.getAutentication();
            String email = data[0];
            boolean validation = TeacherDAO.validateEmail(email);
            if (validation) {
                MessageDigest md = MessageDigest.getInstance("MD5");
                BigInteger hash = new BigInteger(1, md.digest(data[1].getBytes()));
                String password = String.format("%32X", hash).toLowerCase();
                this.teacher = TeacherDAO.authenticate(email, password);
            }
            teacherPresenter.showFeedbackAutentication(validation);
            return (this.teacher != null);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void logout() {
        this.teacher = null;
    }

    public void changePassword() {
        try {
            TeacherPresenter teacherPresenter = new ConsoleTeacherPresenter();
            String[] data = teacherPresenter.getNewPassword();
            MessageDigest md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, md.digest(data[0].getBytes()));
            data[0] = String.format("%32X", hash);
            data[0] = data[0].toLowerCase();
            Teacher t = TeacherDAO.validatePassword(this.teacher.getEmail(), data[0]);
            int confirm = 0;
            if (t != null) {
                if (data[1].equals(data[2])) {
                    hash = new BigInteger(1, md.digest(data[1].getBytes()));
                    String newPassword= String.format("%32X", hash);
                    newPassword = newPassword.toLowerCase();
                    confirm = TeacherDAO.changePassword(t.getCpf(), newPassword);
                }
            }
            teacherPresenter.showFeedbackChangePassword((t!=null), data, confirm);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
