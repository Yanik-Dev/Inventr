/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import entity.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentation.DashboardInternalFrame;

/**
 *
 * @author Yanik
 */

public class ClientThread extends Thread{
    Socket socket = null;
    DashboardInternalFrame frame = null;
    public ClientThread(Socket soc, DashboardInternalFrame client){
        this.socket = soc;
        this.frame = client;
    }
    
    public void run(){
        while(true){
            try{
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                
                //read user from client
                List<User> users = (List<User>)inputStream.readObject();

                
                this.frame.users = users;
                this.frame.setOnlineUsers(users);
                System.out.println(users.size());

            }catch(Exception ex){
                disconnect();
            }
        }
    }
    
    public void disconnect(){
        try {
            this.socket.close();
            this.frame.isOnline(false);
            this.frame.setOnlineUsers(null);
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
