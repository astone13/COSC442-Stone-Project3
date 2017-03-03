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
public class VendingMachineTest {
	
	private VendingMachine vendingMachine;
	private VendingMachineItem item;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		vendingMachine = new VendingMachine();
		item = new VendingMachineItem("Crunch Bar", 1.25);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		System.gc();
	}

	//Test that addItem adds item
	@Test
	public void addItemTest() {
		vendingMachine.addItem(item,VendingMachine.A_CODE);
		assertEquals(item, vendingMachine.getItem(VendingMachine.A_CODE));
	}
	
	//Test that addItem throws an exception when you add a duplicate item
	@Test(expected=VendingMachineException.class)
	public void addDuplicateItemTest() {
		vendingMachine.addItem(item, VendingMachine.A_CODE);
		vendingMachine.addItem(item, VendingMachine.A_CODE);
	}
	
	//Test that removeItem sets item to null
	@Test
	public void removeItemTest() {
		vendingMachine.addItem(item,VendingMachine.A_CODE);
		vendingMachine.removeItem(VendingMachine.A_CODE);
		assertEquals(null, vendingMachine.getItem(VendingMachine.A_CODE));
	}
	
	//Test that removeItem throws an exception if you try to remove a null item
	@Test(expected=VendingMachineException.class)
	public void removeNullItemTest() {
		vendingMachine.removeItem(VendingMachine.A_CODE);
	}
	
	//Test that insertMoney adds money to balance
	@Test
	public void insertMoneyTest() {
		vendingMachine.insertMoney(10.00);
		assertEquals(10.00, vendingMachine.getBalance(), 0.001);
	}
	
	//Test that insertMoney throws an exception if money < zero
	@Test(expected=VendingMachineException.class)
	public void insertMoneyLessThanZeroTest() {
		vendingMachine.insertMoney(-5.00);
	}
	
	//Test that getBalance returns correct balance
	@Test
	public void getBalanceTest() {
		assertEquals(0, vendingMachine.getBalance(), 0.001);
	}
	
	//Test that makePurchase works
	@Test
	public void makePurchaseTest() {
		vendingMachine.addItem(item,VendingMachine.A_CODE);
		vendingMachine.insertMoney(10.00);
		assertEquals(true, vendingMachine.makePurchase(VendingMachine.A_CODE));
		assertEquals(null, vendingMachine.getItem(VendingMachine.A_CODE));
		assertEquals(8.75, vendingMachine.getBalance(), 0.001);
	}
	
	//Test that makePurchase fails when balance < item price
	@Test
	public void makePurchaseInsufficientBalanceTest() {
		vendingMachine.addItem(item,VendingMachine.A_CODE);
		vendingMachine.insertMoney(0.50);
		assertEquals(false, vendingMachine.makePurchase(VendingMachine.A_CODE));
	}
	
	//Test that makePurchase fails when item == null
	@Test
	public void makePurchaseItemExistsTest() {
		assertEquals(false, vendingMachine.makePurchase(VendingMachine.A_CODE));
	}
	
	//Test that returnChange sets balance = 0 and returns the previous balance
	@Test
	public void returnChangeTest() {
		vendingMachine.insertMoney(5.00);
		assertEquals(5.00, vendingMachine.returnChange(), 0.001);
		assertEquals(0, vendingMachine.getBalance(), 0.001);
	}
	
	//Test to make sure that getSlotIndex returns the correct index
	@Test
	public void getSlotIndexTest() {
		assertEquals(0, vendingMachine.getSlotIndex(VendingMachine.A_CODE));
		assertEquals(1, vendingMachine.getSlotIndex(VendingMachine.B_CODE));
		assertEquals(2, vendingMachine.getSlotIndex(VendingMachine.C_CODE));
		assertEquals(3, vendingMachine.getSlotIndex(VendingMachine.D_CODE));
	}
	
	//Test that if you try to get the slot index of a code that doesn't exist, an exception is thrown
	@Test(expected=VendingMachineException.class)
	public void invalidSlotIndex() {
		vendingMachine.getSlotIndex("50");
	}

}
