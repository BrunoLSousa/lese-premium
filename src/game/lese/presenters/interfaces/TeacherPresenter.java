/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.presenters.interfaces;

/**
 *
 * @author bruno
 */
public interface TeacherPresenter {
    
    /**
     *
     * @return
     */
    String[] getAutentication();
    
    /**
     *
     * @param validation
     */
    void showFeedbackAutentication(boolean validation);
    
    /**
     *
     * @return
     */
    String[] getNewPassword();
    
    /**
     *
     * @param validationPassword
     * @param confirm
     * @param data
     */
    void showFeedbackChangePassword(boolean validationPassword, String[] data, int confirm);
}
