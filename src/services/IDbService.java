/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author Yanik
 */
public interface IDbService {
    /**
     * maps an object decorated with the @Entity annotation to a database table
     * @param obj Entity Object 
     * @return true if object is successfully mapped to database
     */
    public <E> boolean create(E obj);
    
   /**
     * Finds all records relating to the Entity class by its status
     * @param type Entity class     
     * @param status 0 for ACTIVE and 1 for INACTIVE
     * @return List of type Entity class
     */
    public <E> List<E> findAll(Class<E> type, int status);
    
    /**
     * Finds all records relating to the Entity class 
     * @param type Entity class
     * @return List of type Entity class
     */
    public <E> List<E> findAll(Class<E> type);
    
    /**
     * Finds an Entity record by its primary key
     * @param id primary key
     * @param type Entity class
     * @return an Entity object
     */
    public  <I, E> E findOne(I id, Class<E> type);
    
   /**
     * Updates a record relating to the Entity object
     * @param obj Entity object
     * @return List of type Entity class
     */
    public  <E> boolean update(E obj);
    
    /**
     * deletes an object decorated with the @Entity annotation from the corresponding database table
     * @param obj Entity Object 
     * @return true if object is successfully mapped to database
     */
    public <E> boolean delete(E obj);
}
