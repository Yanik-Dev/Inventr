/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import common.AppLogger;
import common.Database;
import entity.User;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yanik
 */
public class Server {
    public static List<User> users = new ArrayList<>();   
    public static List<Socket> sockets = new ArrayList<>();

    
    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(6789);
            System.out.println("Server started");
            while(true){
                try {
                    Socket socket = serverSocket.accept();
                    RequestHandler requestHandler= new RequestHandler(socket);
                    requestHandler.start();
                } catch (IOException ex) {
                    AppLogger.getLogger(Server.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
                }
            }
        } catch (IOException ex) {
            AppLogger.getLogger(Server.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", ex);
        }
    }
}
