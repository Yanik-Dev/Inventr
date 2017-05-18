/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import entity.User;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author rahiem
 */
public class ClientThread implements Runnable {
    
    ObjectOutputStream clientOutputStream = null;
    ObjectInputStream clientInputStream = null;
    Socket socketConnection = null;
    Client client;

    public ClientThread(Socket newSocket, Client client){
        socketConnection = newSocket;
        this.client = client;
    }
    
    @Override
    public void run() {
    
        try
        {
            try
            {
                clientOutputStream = new ObjectOutputStream(socketConnection.getOutputStream());
                clientInputStream = new ObjectInputStream(socketConnection.getInputStream());
                clientOutputStream.flush();
                checkStream();
            }
            finally{
                DisconnectFormServer();
            }
        }
        catch(Exception ex)
        {
            System.out.print(ex);
        }
    
    }
    

    public void checkStream()
    {
        try
        {
            while(true){
             Recieve();
            }
        }
        catch(Exception e){
            
        }
    }
    
    public void Recieve()throws IOException {
        
   
        try {
            User response = (User)clientInputStream.readObject();
            
            if(!response.isIsConnected())
            {
                this.client.getUserList().remove(response);
            }
            else{
                //Client.chatArea.append(Message+"\n");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void DisconnectFormServer(){
        try
        {
          clientOutputStream.flush();
          socketConnection.close();
        }
        catch(Exception ex)
        {
        }
    }

}
