/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author Yanik
 */
public class Validator {
    private static String phoneNumberRegex = "^(([(]?(\\d{2,4})[)]?)|(\\d{2,4})|([+1-9]+\\d{1,2}))?[-\\s]?(\\d{2,3})?[-\\s]?((\\d{7,8})|(\\d{3,4}[-\\s]\\d{3,4}))$";
    private static String emailRegex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$"; 
    
    public static boolean isValidURL(String url) {  
        URL u = null;

        try {  
            u = new URL(url);  
        } catch (MalformedURLException e) {  
            return false;  
        }

        try {  
            u.toURI();  
        } catch (URISyntaxException e) {  
            return false;  
        }  

        return true;  
    } 
    
    public static boolean isValidEmail(String email){
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(emailRegex);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
    
    public static boolean isValidPhoneNumber(String number){
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(phoneNumberRegex);
        java.util.regex.Matcher m = p.matcher(number);
        return m.matches();
    }
    
    public static boolean isDateValid(String dateToValidate, String dateFromat){

        if(dateToValidate == null){
                return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);

        try {
            Date date = sdf.parse(dateToValidate);
            System.out.println(date);
        } catch (ParseException e) {

                e.printStackTrace();
                return false;
        }

        return true;
    }
}
