/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import common.AppLogger;
import common.Database;
import entity.Category;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.Query;

/**
 *
 * @author Yanik
 */
public class CategoryService extends DatabaseService {
     
    public  boolean exist(Category category){
        boolean result = false;
        try{
            Query query = Database.getEMInstance().createQuery("Select obj from Category  obj where obj.name = :name")
                                                  .setParameter("name", category.getName());

            List<Category> i = (List<Category>)query.getResultList();
            if(i != null) { result = true; }
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
            Database.closeEM();
        }
        
        return result;
    }
    
    public  List<Category> search(String q){
        List<Category> list = null;
        try{
            Query query = Database.getEMInstance().createQuery("Select obj from Category  obj where obj.name = :q")
                                                  .setParameter("q", "%"+q+"%");

             list = (List<Category>)query.getResultList();
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
            Database.closeEM();
        }
        
        return list;
    }
    
     /**
     * Finds all records relating to Category by a returning a specified set
     * @param limit
     * @return List of Categories
     */
    public List<Category> findByLimit(int limit){
        List<Category> list = null;
        
        try{
            Query query = Database.getEMInstance().createQuery("Select obj from Category obj Order by obj.categoryId DESC");
            list = (List<Category>)query.setMaxResults(limit).getResultList();
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
            Database.closeEM();
        }
        
        return list;
    }
}
