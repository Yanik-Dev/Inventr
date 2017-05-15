/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package common;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Vice Principal
 */
public class Database {
    
    private static Connection _connection = null;
    private static EntityManagerFactory _entityManagerFactoryInstance = null;
    private static EntityManager _entityManagerInstance = null;
    
    /** 
     * returns Traditional JBDC Connection to Mysql database
     * @return 
     */
    public static Connection getTInstance(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            _connection = (Connection) DriverManager.getConnection("", "", "");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return _connection;
    }
    
    
    /**
     * close Traditional JBDC Mysql Connection
     */
    public static void closeT(){
        if(_connection != null){
            try {
                _connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /** 
     * returns Entity Manager Connection
     * @return 
     */
    public static EntityManager getEMInstance(){
        if(_entityManagerInstance == null || !_entityManagerInstance.isOpen()){
            _entityManagerFactoryInstance = Persistence.createEntityManagerFactory("inventrPU");
            _entityManagerInstance = _entityManagerFactoryInstance.createEntityManager();
        }
        return _entityManagerInstance;
    }
    
    /**
     * close Entity Manager Connection
     */
    public static void closeEM(){
        
        if(_entityManagerInstance != null){
            _entityManagerInstance.close();
        }
        if(_entityManagerFactoryInstance != null){
            _entityManagerFactoryInstance.close();
        }
    }
    
    /**
     * configures database properties
     */
    public static void setDatabaseXMLProperties(){
        Map properties = new HashMap();
        
        properties.put("", "");
        _entityManagerFactoryInstance = Persistence.createEntityManagerFactory("inventrPU", properties);
        _entityManagerInstance = _entityManagerFactoryInstance.createEntityManager();
    }
}
