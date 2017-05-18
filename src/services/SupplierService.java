/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import common.AppLogger;
import common.Database;
import entity.Supplier;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.Query;

/**
 *
 * @author Yanik
 */
public class SupplierService extends DatabaseService {
     
    
    public  boolean exist(Supplier supplier){
        boolean result = false;
        try{
            Query query = Database.getEMInstance().createQuery("Select obj from Supplier  obj where obj.name = :name")
                                                  .setParameter("name", supplier.getName()); 

            List<Supplier> i = (List<Supplier>)query.getResultList();
            if(i != null) { result = true; }
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
            Database.closeEM();
        }
        
        return result;
    }
    
    public  List<Supplier> search(String q){
        List<Supplier> list = null;
        try{
            Query query = Database.getEMInstance().createQuery("Select obj from Supplier  obj where obj.name = :q ")
                                                  .setParameter("q", "%"+q+"%");

             list = (List<Supplier>)query.getResultList();
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
            Database.closeEM();
        }
        
        return list;
    }
    
    /* Finds all records relating to the Entity class by a returning a specified set
     * @param type Entity class
     * @param limit
     * @return List of type Entity class
     */
    public List<Supplier> findByLimit(int limit){
        List<Supplier> list = null;
        
        try{
            Query query = Database.getEMInstance().createQuery("Select obj from Supplier obj Order by obj.Id DESC");
            list = (List<Supplier>)query.setMaxResults(limit).getResultList();
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
            Database.closeEM();
        }
        
        return list;
    }
}
