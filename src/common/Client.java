/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import entity.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yanik
 */
public class Client {
    ObjectOutputStream clientOutputStream = null;
    ObjectInputStream clientInputStream = null;
    Socket socketConnection = null;
    List<User> userList = new ArrayList<>();
    
    public Client(User user) {
        try {
            clientOutputStream = new ObjectOutputStream(socketConnection.getOutputStream());
            clientInputStream = new ObjectInputStream(socketConnection.getInputStream());
            this.clientOutputStream.writeObject(user);
            
            User response = (User)clientInputStream.readObject();
            
            if(response != null){
                userList.add(response);
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<User> getUserList(){
        return userList;
    }
}
