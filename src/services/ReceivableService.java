/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import common.AppLogger;
import common.Database;
import entity.Receivable;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.Query;

/**
 *
 * @author Yanik
 */
public class ReceivableService extends DatabaseService {
     
    

    
    public  List<Receivable> search(String q){
        List<Receivable> list = null;
        try{
            Query query = Database.getEMInstance().createQuery("Select obj from Receivable  obj where obj.item Like :q or obj.dateReceived LIKE :q")
                                                  .setParameter("q", "%"+q+"%");

             list = (List<Receivable>)query.getResultList();
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
            Database.closeEM();
        }
        
        return list;
    }
    
     /**
     * Finds all records relating to Receivable by a returning a specified set
     * @param limit
     * @return List of Receivables
     */
    public List<Receivable> findByLimit(int limit){
        List<Receivable> list = null;
        
        try{
            Query query = Database.getEMInstance().createQuery("Select obj from Receivable obj Order by obj.id DESC");
            list = (List<Receivable>)query.setMaxResults(limit).getResultList();
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
            Database.closeEM();
        }
        
        return list;
    }
}
