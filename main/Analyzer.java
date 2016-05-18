package MyJunit.main;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import MyJunit.annotations.After;
import MyJunit.annotations.Before;
import MyJunit.annotations.Test;

class Analyzer
{
	private final Class clazz;
	private final long id;

	Analyzer(Class testClass, long testId)
	{
		clazz = testClass;
		id = testId;
	}


	public void parse() throws Exception
	{

		Method[] methods = clazz.getMethods();

		int pass = 0;
		int fail = 0;

		try
		{
			FileWriter testWriter = new FileWriter(clazz.getName() + "_out.txt", false);

			testWriter.write("* " + clazz.getName() + " * " + getId() + ":\n\n");

			Method before = Analyzer.class.getDeclaredMethod("nothing");
			Method after = Analyzer.class.getDeclaredMethod("nothing");

			for (Method method : methods)
			{
				if (method.isAnnotationPresent(Before.class))
					before = method;

				if (method.isAnnotationPresent(After.class))
					after = method;
			}


			for (Method method : methods)
			{
				if (method.isAnnotationPresent(Test.class))
				{
					Test an = method.getAnnotation(Test.class);
					try
					{
						before.invoke(before.getDeclaringClass().newInstance());

						method.invoke(method.getDeclaringClass().newInstance());
						pass++;

						callAfter(after);

					}
					catch (InvocationTargetException e)
					{
						Throwable waitExc = e.getTargetException();
						if (waitExc.getClass().equals(an.expected()))
						{
							pass++;
							callAfter(after);
							continue;
						}

						testWriter.write("- Test " + method.getName() + ": " + waitExc.getMessage());
						fail++;
					}
					catch (IllegalAccessException | IllegalArgumentException | InstantiationException e)
					{
						testWriter.write("- Test " + method.getName() + ": " + e.getMessage());
						fail++;
					}
				}
			}

			testWriter.write("\n\nTotal: " + (fail + pass) + "\n" +
					"- Passed: " + pass + "\n" +
					"- Failed: " + fail + "\n");

			testWriter.flush();
		}
		catch(IOException e)
		{
			System.out.println("Lost access to the file");
		}
	}

	private long getId()
	{
		return id;
	}

	private void nothing(){}

	private void callAfter(Method after)
	{
		try
		{
			after.invoke(after.getDeclaringClass().newInstance());
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			System.out.println("Invalid after method");
		}
	}

}
