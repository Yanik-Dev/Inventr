/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import common.AppLogger;
import common.Database;
import entity.Location;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.Query;

/**
 *
 * @author Yanik
 */
public class LocationService extends DatabaseService {
     
    public  boolean exist(Location location){
        boolean result = false;
        try{
            Query query = Database.getEMInstance().createQuery("Select obj from Location  obj where obj.name = :name")
                                                  .setParameter("name", location.getName());

            List<Location> i = (List<Location>)query.getResultList();
            if(!i.isEmpty()) { result = true; }
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
            Database.closeEM();
        }
        
        return result;
    }
    
    public  List<Location> search(String q){
        List<Location> list = null;
        try{
            Query query = Database.getEMInstance().createQuery("Select obj from Location  obj where obj.name LIKE :q")
                                                  .setParameter("q", "%"+q+"%");

             list = (List<Location>)query.getResultList();
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
            Database.closeEM();
        }
        
        return list;
    }
    
     /**
     * Finds all records relating to Location by a returning a specified set
     * @param limit
     * @return List of Locations
     */
    public List<Location> findByLimit(int limit){
        List<Location> list = null;
        
        try{
            Query query = Database.getEMInstance().createQuery("Select obj from Location obj Order by obj.locationId DESC");
            list = (List<Location>)query.setMaxResults(limit).getResultList();
        }catch(Exception ex){
             AppLogger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }finally{
            Database.closeEM();
        }
        
        return list;
    }
}
