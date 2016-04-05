package MyJunit.main;

public class Functions
{

	public static double division(double x, double y){
		if (y == 0.0)
			throw new DivisionByZeroException();
		return x/y;
	}


	public static double sub(double x, double y){
		return x*y;
	}

}
