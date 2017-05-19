/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseConnectionTestPkg;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 *
 * @author Yanik
 */
@RunWith(Suite.class)
@SuiteClasses({
        EntityManagerConnectionTest.class,
        JBDCConnectionTest.class })
public class ConnectionTestSuite { 
   
}
