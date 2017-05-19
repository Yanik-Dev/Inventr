/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseConnectionTestPkg;

import com.mysql.jdbc.Connection;
import common.Database;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Yanik
 */
public class JBDCConnectionTest {
    
    Connection connection =null;
    
    public JBDCConnectionTest() {
    }
    

 
    
    @Before
    public void testConnection(){
        connection = Database.getTInstance();
        assertNotNull(connection);
    }

    @Test
    public void tearDownConnection() {
        Database.closeT();
        try {
            assertTrue(connection.isClosed());
        } catch (SQLException ex) {
            Logger.getLogger(JBDCConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
