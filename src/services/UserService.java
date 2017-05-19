/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import common.AppLogger;
import common.Database;
import entity.User;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.Query;

/**
 *
 * @author Yanik
 */
public class UserService extends DatabaseService {
     
    
    public  boolean exist(User item){
        boolean result = false;
        try{
            Query query = Database.getEMInstance().createQuery("Select obj from User  obj where obj.username = :code")
                                                  .setParameter("code", item.getUsername());

            List<User> i = (List<User>)query.getResultList();
            if(i != null) { result = true; }
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
            Database.closeEM();
        }
        
        return result;
    }
    
    public  List<User> search(String q){
        List<User> list = null;
        try{
            Query query = Database.getEMInstance().createQuery("Select obj from User  obj where obj.username LIKE :q or obj.firstname LIKE :q or obj.lastname LIKE :q")
                                                  .setParameter("q", "%"+q+"%");

             list = (List<User>)query.getResultList();
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
            Database.closeEM();
        }
        
        return list;
    }
    
     /**
     * Finds all records relating to User by a returning a specified set
     * @param limit
     * @return List of Users
     */
    public List<User> findByLimit(int limit){
        List<User> list = null;
        
        try{
            Query query = Database.getEMInstance().createQuery("Select obj from User obj Order by obj.id DESC");
            list = (List<User>)query.setMaxResults(limit).getResultList();
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
            Database.closeEM();
        }
        
        return list;
    }
}
