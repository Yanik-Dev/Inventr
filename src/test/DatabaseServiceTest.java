/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import entity.User;
import services.DatabaseService;

/**
 *
 * @author Vice Principal
 */
public class DatabaseServiceTest {
    
    public static void main(String[] args){
        User user = new User();
        
        user.setUsername("nik");
        //System.out.print(""+user.getUsername());
    }
}
