/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import common.AppLogger;
import common.Database;
import entity.Unit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author Yanik
 */
public class UnitService {
    PreparedStatement _preparedStatement = null;
    Statement _statement = null;
    
    /**
     * insert a unit in the database
     * @param unit
     * @return 
     */
    public boolean create(Unit unit){
        boolean result = false;
        try{
            String sql = "INSERT INTO units SET name = ?";
            this._preparedStatement = Database.getTInstance().prepareStatement(sql);
            this._preparedStatement.setString(1, unit.getName());
            this._preparedStatement.execute();
            result = true;
        }catch(SQLException ex){
                AppLogger.getLogger(UnitService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
           try{
                if(this._preparedStatement != null){
                    this._preparedStatement.close();
                }
            }catch(SQLException ex2){
                AppLogger.getLogger(UnitService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex2);
            }
            Database.closeT(); 
        }
        return result;
    }
    
    /**
     * check if a unit already exists and returns true if it does otherwise returns false
     * @param unit
     * @return 
     */
    public boolean exist(Unit unit){
        boolean result = false;
        try{
            String sql = "SELECT * units WHERE name = ?";
            this._preparedStatement = Database.getTInstance().prepareStatement(sql);
            this._preparedStatement.setString(1, unit.getName());
            ResultSet resultSet = this._preparedStatement.getResultSet();
            if(resultSet.first()){
                result = true;
            }
        }catch(SQLException ex){
            AppLogger.getLogger(UnitService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);  
        }finally{
           try{
                if(this._preparedStatement != null){
                    this._preparedStatement.close();
                }
            }catch(SQLException ex2){
                AppLogger.getLogger(UnitService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex2);
            }
            Database.closeT(); 
        }
        return result;
    }
    
    /**
     * updates a specific unit
     * @param unit
     * @return 
     */
    public boolean update(Unit unit){
        boolean result = false;
        try{
            String sql = "UPDATE INTO units SET name = ? WHERE unit_id = ?";
            this._preparedStatement = Database.getTInstance().prepareStatement(sql);
            this._preparedStatement.setString(1, unit.getName());
            this._preparedStatement.setInt(1, unit.getId()); 
            this._preparedStatement.execute();
            result = true;
        }catch(SQLException ex){
            AppLogger.getLogger(UnitService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
           try{
                if(this._preparedStatement != null){
                    this._preparedStatement.close();
                }
            }catch(SQLException ex2){
                AppLogger.getLogger(UnitService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex2);
            }
            Database.closeT(); 
        }
        return result;
    }
    
    /**
     * remove a unit from the database
     * @param unit
     * @return 
     */
    public boolean delete(Unit unit){
        boolean result = false;
        try{
            Database.getTInstance().setAutoCommit(false);
            
            //set all items' foriegn key that uses this unit to null
            this._preparedStatement = Database.getTInstance().prepareStatement("UPDATE items SET unit_id = NULL WHERE unit_id = ?");
            this._preparedStatement.setInt(1, unit.getId()); 
            this._preparedStatement.execute();
            
            //delete unit
            this._preparedStatement = Database.getTInstance().prepareStatement("DELETE FROM units WHERE unit_id = ?");
            this._preparedStatement.setInt(1, unit.getId()); 
            this._preparedStatement.execute();
            Database.getTInstance().commit();
            result = true;
        }catch(SQLException ex){
            AppLogger.getLogger(UnitService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
            try{
                Database.getTInstance().rollback();
            }catch(SQLException ex2){
                AppLogger.getLogger(UnitService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex2);
            }
        }finally{
           try{
                if(this._preparedStatement != null){
                    this._preparedStatement.close();
                }
            }catch(SQLException ex2){
                AppLogger.getLogger(UnitService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex2);
            }
            Database.closeT(); 
        }
        return result;
    }
        
    public List<Unit> findAll(){
        List<Unit> units = new ArrayList<>();
        String sql = "SELECT * FROM units";
        try{
            ResultSet resultSet = this._statement.executeQuery(sql);
            while(resultSet.next()){
                Unit unit = new Unit(resultSet.getInt(0), resultSet.getString(1));
                units.add(unit);
            }
        }catch(SQLException ex){
            AppLogger.getLogger(UnitService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
           try{
                if(this._statement != null){
                    this._statement.close();
                }
            }catch(SQLException ex2){
                AppLogger.getLogger(UnitService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex2);
            }
            Database.closeT(); 
        }
        return units;
    }
    
    /**
     * Returns a specific unit
     * @param unitRef
     * @return 
     */
    public Unit findOne(Unit unitRef){
        Unit unit = null;
        try{
            String sql = "SELECT * FROM units WHERE unit_id = ?";
            this._preparedStatement = Database.getTInstance().prepareStatement(sql);
            this._preparedStatement.setInt(1, unitRef.getId()); 
            ResultSet resultSet = this._preparedStatement.executeQuery(sql);
            while(resultSet.next()){
                unit = new Unit(resultSet.getInt(0), resultSet.getString(1));
            }
        }catch(SQLException ex){
            AppLogger.getLogger(UnitService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
           try{
                if(this._preparedStatement != null){
                    this._preparedStatement.close();
                }
            }catch(SQLException ex2){
                AppLogger.getLogger(UnitService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex2);
            }
            Database.closeT(); 
        }
        return unit;
    }
    
    /**
     * search for units that have characters stored in q
     * @param q
     * @return 
     */
    public List<Unit> search(String q){
        List<Unit> units = new ArrayList<>();
        String sql = "SELECT * FROM units WHERE name LIKE ?";
        try{
            this._preparedStatement = Database.getTInstance().prepareStatement(sql);
            this._preparedStatement.setString(1, "%"+q+"%"); 
            ResultSet resultSet = this._preparedStatement.executeQuery(sql);
            while(resultSet.next()){
                Unit unit = new Unit(resultSet.getInt(0), resultSet.getString(1));
                units.add(unit);
            }
        }catch(SQLException ex){
                AppLogger.getLogger(UnitService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
           try{
                if(this._preparedStatement != null){
                    this._preparedStatement.close();
                }
            }catch(SQLException ex2){
                AppLogger.getLogger(UnitService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred",ex2);
            }
            Database.closeT(); 
        }
        return units;
    }
    
}
