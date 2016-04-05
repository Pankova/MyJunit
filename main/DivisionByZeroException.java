package MyJunit.main;

public class DivisionByZeroException extends RuntimeException
{
	String message = "Division by zero!\n";

	DivisionByZeroException(){}

	public String getMessage(){ return message; }
}
