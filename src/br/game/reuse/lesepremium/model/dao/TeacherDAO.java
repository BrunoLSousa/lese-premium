/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.game.reuse.lesepremium.model.dao;

import br.game.reuse.lesepremium.connection.DBConnection;
import br.game.reuse.lesepremium.model.Teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bruno
 */
public class TeacherDAO {
    
    public static int createTeacher(Teacher teacher){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("INSERT INTO teacher(cpf, user, institution, email, password) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, teacher.getCpf());
            ps.setInt(2, teacher.getIdUser());
            ps.setString(3, teacher.getInstitution());
            ps.setString(4, teacher.getEmail());
            ps.setString(5, teacher.getPassword());
            return ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBConnection.closeConnection(connection, ps);
        }
        return 0;
    }
    
    public static Teacher authentication(String[] data){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("SELECT * FROM teacher t JOIN user u WHERE t.email=? AND t.password=? AND u.id_user=t.user");
            ps.setString(1, data[0]);
            ps.setString(2, data[1]);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Teacher teacher = new Teacher();
                teacher.setCpf(rs.getString("cpf"));
                teacher.setEmail(rs.getString("email"));
                teacher.setIdUser(rs.getInt("user"));
                teacher.setInstitution(rs.getString("institution"));
                teacher.setName(rs.getString("name"));
                teacher.setPassword(rs.getString("password"));
                return teacher;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBConnection.closeConnection(connection, ps);
        }
        return null;
    }
    
    public static boolean validateEmail(String email){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("SELECT * FROM teacher t JOIN user u WHERE t.email=? AND u.id_user=t.user");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Teacher teacher = new Teacher();
                teacher.setCpf(rs.getString("cpf"));
                teacher.setEmail(rs.getString("email"));
                teacher.setIdUser(rs.getInt("user"));
                teacher.setInstitution(rs.getString("institution"));
                teacher.setName(rs.getString("name"));
                teacher.setPassword(rs.getString("password"));
                return teacher != null;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBConnection.closeConnection(connection, ps);
        }
        return false;
    }
    
    public static Teacher validatePassword(String email, String password){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("SELECT * FROM teacher t JOIN user u WHERE t.email=? AND t.password=? AND u.id_user=t.user");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Teacher teacher = new Teacher();
                teacher.setCpf(rs.getString("cpf"));
                teacher.setEmail(rs.getString("email"));
                teacher.setIdUser(rs.getInt("user"));
                teacher.setInstitution(rs.getString("institution"));
                teacher.setName(rs.getString("name"));
                teacher.setPassword(rs.getString("password"));
                return teacher;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBConnection.closeConnection(connection, ps);
        }
        return null;
    }
    
    public static int changePassword(String cpf, String password){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("UPDATE teacher SET password=? WHERE cpf=?");
            ps.setString(1, password);
            ps.setString(2, cpf);
            return ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBConnection.closeConnection(connection, ps);
        }
        return 0;
    }
    
}
