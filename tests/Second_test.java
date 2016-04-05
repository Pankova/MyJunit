package MyJunit.tests;

import MyJunit.annotations.After;
import MyJunit.annotations.Before;
import MyJunit.annotations.Test;
import MyJunit.main.Assert;
import MyJunit.main.Functions;

public class Second_test
{
	@Before
	public void hi(){
		System.out.print("Hi from SecondTest!\n");
	}

	@Test
	public void sub7_8test(){
		Assert.assertEquals(Functions.sub(7, 8), 55.0);
	}

	@Test
	public void sub0_5test(){
		Assert.assertNull(Functions.sub(0, 5));
	}	
	@Test
	public void sub4_9test(){
		Assert.assertEquals(Functions.sub(4, 9), 36.0);
	}

	@After
	public void bye(){
		System.out.print("Bye from SecondTest!\n");
	}
}
