package MyJunit.tests;
import MyJunit.annotations.After;
import MyJunit.annotations.Before;
import MyJunit.annotations.Test;
import MyJunit.main.Assert;
import MyJunit.main.DivisionByZeroException;
import MyJunit.main.Functions;

public class First_test
{
	@Before
	public void hi(){
		System.out.println("Hi from FirstTest!\n");
	}

	@Test
	public void division100_5test(){
		Assert.assertEquals(Functions.division(100, 5), 25.0);
	}

	@Test(expected = DivisionByZeroException.class)
	public void division7_0test(){
		Assert.assertNull(Functions.division(7, 0));
	}

	@After
	public void bye(){
		System.out.println("Bye from FirstTest.\n");
	}
}
