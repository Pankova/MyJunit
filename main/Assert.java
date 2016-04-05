package MyJunit.main;

public class Assert extends RuntimeException
{
	String aCause;

	Assert(String cause){
		aCause = cause;
	}

	@Override
	public String getMessage(){ return  aCause; }

	public static void assertEquals(Object expected, Object actual){
		if  (!expected.equals(actual))
			throw new Assert("Objects are not equal. ");
	}

	public static void assertNull(double number){
		if (!(number == 0.0))
			throw new Assert("Object is not null. ");
	}

	public static void assertTrue(boolean condition){
		if (!condition)
			throw new Assert("Condition is not true. ");
	}

	public static void assertNull(Object object){
		if (!object.equals(null))
			throw new Assert("Object is not null. ");
	}

	public static void assertNotNull(Object object){
		if (object.equals(null))
			throw new Assert("Object is not null. ");
	}

	public static void assertNotNull(double number){
		if (number == 0.0)
			throw new Assert("Object is not null.  ");
	}

	public static void assertTrue(String message, String condition){
		if(! message.equals(condition))
			throw new Assert("Conditions are not equal. ");
	}

}

