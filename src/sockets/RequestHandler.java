/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import common.AppLogger;
import entity.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yanik
 */
public class RequestHandler extends Thread{
    Socket socket = null;
    
    public RequestHandler(Socket soc){
        socket = soc;
    }
    
    public void run(){
        try{
            while(true){
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                
                //read user from client
                User user = (User)inputStream.readObject();

                //add a new user along with the associated socket
                Server.users.add(user);
                Server.sockets.add(this.socket);

                //echo back user to client
                for(Socket soc : Server.sockets){
                    ObjectOutputStream outputStream = new ObjectOutputStream(soc.getOutputStream());
                    outputStream.writeObject(Server.users);
                    outputStream.flush();
                }
            }
        }catch(IOException ex){
            AppLogger.getLogger(RequestHandler.class.getName()).log(Level.INFO, "A Client has discounted", ex);
            //remove disconnected client
            removeDisconnectedClient(this.socket);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }
    
    public void removeDisconnectedClient(Socket soc){
        if(Server.sockets.contains(soc)){
                System.out.println("Socket: "+soc.getLocalAddress()+" has disconnected");
                
                //remove client from list
                int index =Server.sockets.indexOf(soc);
                Server.sockets.remove(soc);
                Server.users.remove(index);
                
                //send the updated user list to the remaining clients 
                for(Socket remainingSockets : Server.sockets){
                     try{
                        ObjectOutputStream outputStream = new ObjectOutputStream(remainingSockets.getOutputStream());
                        outputStream.writeObject(Server.users);
                        outputStream.flush();
                     }catch(IOException e){
                         removeDisconnectedClient(remainingSockets);
                         AppLogger.getLogger(RequestHandler.class.getName()).log(Level.INFO, "A Client has discounted", e);
                     }
                }
        }
    }
}

