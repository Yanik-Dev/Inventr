/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

import services.interfaces.IDbService;
import common.AppLogger;
import common.Database;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.Query;

/**
 *
 * @author Vice Principal
 */
public class  DatabaseService implements IDbService{
    
    /**
     * maps an object decorated with the @Entity annotation to a database table
     * @param obj Entity Object 
     * @return true if object is successfully mapped to database
     */
    public <E> boolean create(E obj){
        boolean isSaved = false;
        try{
            Database.getEMInstance().getTransaction().begin();
            Database.getEMInstance().persist(obj);
            Database.getEMInstance().getTransaction().commit();
            isSaved = true;
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);  
        }finally{
            Database.closeEM();
        }
        return isSaved;
    }
    
   /**
     * Finds all records relating to the Entity class by its status
     * @param type Entity class     
     * @param status 0 for ACTIVE and 1 for INACTIVE
     * @return List of type Entity class
     */
    public <E> List<E> findAll(Class<E> type, int status){
        List<E> list = null;
        
        try{
            Query query = Database.getEMInstance().createQuery("Select obj from "+type.getSimpleName()+" obj where status = ?");
            query.setParameter(1, status);
            list = (List<E>)query.getResultList();
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
            Database.closeEM();
        }
        
        return list;
    }
    
    /**
     * Finds all records relating to the Entity class 
     * @param type Entity class
     * @return List of type Entity class
     */
    public <E> List<E> findAll(Class<E> type){
        List<E> list = null;
        
        try{
            Query query = Database.getEMInstance().createQuery("Select obj from "+type.getSimpleName()+" obj");
            list = (List<E>)query.getResultList();
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
            Database.closeEM();
        }
        
        return list;
    }
    
    
    
    /**
     * Finds an Entity record by its primary key
     * @param id primary key
     * @param type Entity class
     * @return an Entity object
     */
    public  <I, E> E findOne(I id, Class<E> type){
        E obj = null;
        try{
            obj = Database.getEMInstance().find(type, id);
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
            Database.closeEM();
        }
        return obj;
    }
    
   /**
     * Updates a record relating to the Entity object
     * @param obj Entity object
     * @return List of type Entity class
     */
    public <E> boolean update(E obj){
        boolean isUpdated = false;
        try{
            Database.getEMInstance().getTransaction().begin();
            Database.getEMInstance().merge(obj);
            Database.getEMInstance().getTransaction().commit();
            isUpdated = true;
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
            Database.closeEM();
        }
        return isUpdated;
    }
    
    /**
     * deletes an object decorated with the @Entity annotation from the corresponding database table
     * @param obj Entity Object 
     * @return true if object is successfully mapped to database
     */
    public <E> boolean delete(E obj){
        boolean result = false;
        try{
            Database.getEMInstance().getTransaction().begin();
            Database.getEMInstance().remove(Database.getEMInstance().merge(obj));
            Database.getEMInstance().getTransaction().commit();
            result = true;
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
            Database.closeEM();
        }
        return result;
    }
}
