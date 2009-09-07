/**
 * 
 */
package mancala;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Tamal Saha
 * 
 */
public class MancalaTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_topMove() {
		System.out.println("Test if pricePerMonth rounds up correctly...");
		ManCala game = new ManCala();
		game.move(0);
		assertTrue(game.topBoard.get(0) == 0);
	}

	@Test
	public void test_bottomMove() {
		System.out.println("Test if pricePerMonth rounds up correctly...");
		ManCala game = new ManCala();
		game.move(12);
		assertTrue(game.bottomBoard.get(6) == 0);
	}
}
