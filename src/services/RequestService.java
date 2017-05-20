/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import common.AppLogger;
import common.Database;
import entity.Request;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.Query;

/**
 *
 * @author Yanik
 */
public class RequestService extends DatabaseService {
     
    

    
    public  List<Request> search(String q){
        List<Request> list = null;
        try{
            Query query = Database.getEMInstance().createQuery("Select obj from Request  obj where obj.item.itemName Like :q or obj.requester.username LIKE :q or obj.dateRequested LIKE :q")
                                                  .setParameter("q", "%"+q+"%");

             list = (List<Request>)query.getResultList();
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
            Database.closeEM();
        }
        
        return list;
    }
    
     /**
     * Finds all records relating to Request by a returning a specified set
     * @param limit
     * @return List of Requests
     */
    public List<Request> findByLimit(int limit){
        List<Request> list = null;
        
        try{
            Query query = Database.getEMInstance().createQuery("Select obj from Request obj Order by obj.id DESC");
            list = (List<Request>)query.setMaxResults(limit).getResultList();
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
            Database.closeEM();
        }
        
        return list;
    }
}
