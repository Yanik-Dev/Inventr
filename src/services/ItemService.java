/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import common.AppLogger;
import common.Database;
import entity.Item;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.Query;

/**
 *
 * @author Yanik
 */
public class ItemService extends DatabaseService {
     
    
    public  boolean exist(Item item){
        boolean result = false;
        try{
            Query query = Database.getEMInstance().createQuery("Select obj from Item  obj where obj.itemCode = :code or obj.itemName = :name")
                                                  .setParameter("code", item.getItemCode())
                                                  .setParameter("name", item.getItemName()); 

            List<Item> i = (List<Item>)query.getResultList();
            if(!i.isEmpty()) { result = true; }
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
            Database.closeEM();
        }
        
        return result;
    }
    
    public  List<Item> search(String q){
        List<Item> list = null;
        try{
            Query query = Database.getEMInstance().createQuery("Select obj from Item  obj where obj.itemCode LIKE :q or obj.itemName LIKE :q")
                                                  .setParameter("q", "%"+q+"%");

             list = (List<Item>)query.getResultList();
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
            Database.closeEM();
        }
        
        return list;
    }
    
     /**
     * Finds all records relating to Item by a returning a specified set
     * @param limit
     * @return List of Items
     */
    public List<Item> findByLimit(int limit){
        List<Item> list = null;
        
        try{
            Query query = Database.getEMInstance().createQuery("Select obj from Item obj Order by obj.insertedDate DESC");
            list = (List<Item>)query.setMaxResults(limit).getResultList();
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
            Database.closeEM();
        }
        
        return list;
    }
}
