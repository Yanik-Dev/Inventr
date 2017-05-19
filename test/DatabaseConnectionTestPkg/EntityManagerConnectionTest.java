/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseConnectionTestPkg;

import common.Database;
import javax.persistence.EntityManager;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Yanik
 */
public class EntityManagerConnectionTest {
    EntityManager em = null;
    public EntityManagerConnectionTest() {
    }
    
    
    @Before
    public void setUpConnection() {
        em = Database.getEMInstance();
        assertNotNull(em);
    }
    
    @Test
    public void tearDownConnection() {
        Database.closeEM();
       assertFalse(em.isOpen());
    }

}
