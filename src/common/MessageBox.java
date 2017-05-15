/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Yanik
 */
public class MessageBox {
    
    public static void errorBox(Component component, String titleBar, String message)
    {
        JOptionPane.showMessageDialog(component, message, "Error " + titleBar, JOptionPane.ERROR_MESSAGE);
    }    
    
    public static void infoBox(Component component, String titleBar , String message)
    {
        JOptionPane.showMessageDialog(component, message, "Info " + titleBar,  JOptionPane.INFORMATION_MESSAGE);
    } 
    
    public static void warningBox(Component component, String titleBar , String message)
    {
        JOptionPane.showMessageDialog(component, message, "Info " + titleBar, JOptionPane.WARNING_MESSAGE);
    }
}
