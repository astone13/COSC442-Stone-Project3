/**
 * 
 */
package edu.towson.cis.cosc442.project3.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author AdamStone
 *
 */
public class VendingMachineItemTest {
	
	private VendingMachineItem item;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		item = new VendingMachineItem("Test Item", 10.00);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		System.gc();
	}
	
	//Testing Constructor for Set Values
	@Test
	public void vendingMachineItemSetValuesTest() {
		assertEquals("Test Item", item.getName());
		assertEquals(10.00, item.getPrice(), 0.001);
	}
	
	//Testing that constructor throws exception if price < 0
	@Test(expected=VendingMachineException.class)
	public void vendingMachineItemLessThanZeroTest() {
		item = new VendingMachineItem("Test Item", -1.00);
	}
	
	//Testing that getName returns item name
	@Test
	public void getNameTest() {
		assertEquals("Test Item", item.getName());
	}
	
	//Testing that getPrice returns item price
	@Test
	public void getPriceTest() {
		assertEquals(10.00, item.getPrice(), 0.001);
	}

}
