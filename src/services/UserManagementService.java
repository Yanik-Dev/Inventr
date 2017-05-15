/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import common.Database;
import entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Yanik
 */
public class UserManagementService {
    PreparedStatement _preparedStatement = null;
    Statement _statement = null;
    public void insert(User user) throws SQLException{
        String sql = "INSERT INTO users set username = ?, password=?, salt=?, question=? answer=?, status=?";
        this._preparedStatement = Database.getTInstance().prepareStatement(sql);
        this._preparedStatement.setString(0, user.getPassword()); 
        this._preparedStatement.setString(0, user.getUsername()); 
        this._preparedStatement.setString(0, user.getSalt()); 
    }
    
    public ArrayList<User> findAll() throws SQLException{
        ArrayList<User> users = null;
        String sql = "SELECT * FROM users";
        ResultSet resultSet = this._statement.executeQuery(sql);
        while(resultSet.next()){
            
        }
        return users;
    }
}
