/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseServicesTestPkg;

import entity.Unit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import services.UnitService;

/**
 *
 * @author Yanik
 */
public class CategoryServiceTest {
    UnitService _unitService = null;
    Unit unit = null;
    public CategoryServiceTest() {}
    
    @Before
    public void setUp() {
        unit = new Unit();
        _unitService = new UnitService();
        unit.setName("Test Unit");
        Boolean result = _unitService.create(unit);
        assertTrue(result);
    }
    
    @Test
    public void findOneTest(){
        Unit unit = new Unit();
        _unitService = new UnitService();
        unit.setId(1);
        unit.setName("Test Unit");
         this.unit = _unitService.findOne(unit);
        assertNotNull(this.unit);
    }
  
    @After
    public void tearDown() {
        _unitService = new UnitService();
        Unit unit = new Unit();
        unit.setName("Test Unit");
        Boolean result = _unitService.delete(unit);
        assertTrue(result);
    }
    
}
