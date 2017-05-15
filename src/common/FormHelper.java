/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.awt.Component;
import java.awt.Container;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Yanik
 */
public class FormHelper {
    
    public static void clear(Container container){
        for(Component c : container.getComponents()){
            if(c instanceof JTextField){
               JTextField f = (JTextField) c;
               f.setText("");
            } 
            else if(c instanceof JComboBox){
                JComboBox b = (JComboBox)c;
                b.setSelectedIndex(0);
            }
            else if(c instanceof JTextArea){
                JTextArea t = (JTextArea)c;
                t.setText("");
            }
            else if (c instanceof Container){
               clear((Container)c);
            }
            
        }
    }
}
