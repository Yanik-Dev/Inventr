/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ValidatorTestPkg;

import common.Validator;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Yanik
 */
public class EmailValidatorTest {
    private Validator dateValidator;

	@Before
	public void init() {
		dateValidator = new Validator();
	}

	@Test
	public void dateIsNullTest() {
		assertFalse(dateValidator.isDateValid(null, "dd/MM/yyyy"));
	}

	@Test
	public void dayIsInvalidTest() {
		assertFalse(dateValidator.isDateValid("32/02/2017", "dd/MM/yyyy"));
	}

	@Test
	public void monthIsInvalidTest() {
		assertFalse(dateValidator.isDateValid("31/20/2017", "dd/MM/yyyy"));
	}

	@Test
	public void yearIsInvalidTest() {
		assertFalse(dateValidator.isDateValid("31/20/19991", "dd/MM/yyyy"));
	}

	@Test
	public void dateFormatIsInvalidTest() {
		assertFalse(dateValidator.isDateValid("2017/02/20", "dd/MM/yyyy"));
	}


	@Test
	public void dateIsValid_1Test() {
		assertTrue(dateValidator.isDateValid("31/01/2017", "dd/MM/yyyy"));
	}

	@Test
	public void dateIsValid_2Test() {
		assertTrue(dateValidator.isDateValid("30/04/2017", "dd/MM/yyyy"));
	}

	@Test
	public void dateIsValid_3Test() {
		assertTrue(dateValidator.isDateValid("31/05/2017", "dd/MM/yyyy"));
	}

}
