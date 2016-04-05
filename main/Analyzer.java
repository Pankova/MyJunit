package MyJunit.main;

import java.io.FileWriter;
import java.lang.reflect.Method;

import MyJunit.annotations.After;
import MyJunit.annotations.Before;
import MyJunit.annotations.Test;

class Analyzer extends Thread
{
	private Class clazz;

	Analyzer(Class testClass){ clazz = testClass; }

	@Override
	public void run(){
		try{
			parse();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void parse() throws Exception{

		Method[] methods = clazz.getMethods();

		int pass = 0;
		int fail = 0;

		FileWriter testWriter = new FileWriter(clazz.getName()+"_out.txt", false);

		testWriter.write("* " + clazz.getName() + " * :\n\n");

		Method before = Analyzer.class.getDeclaredMethod("nothing");
		Method after = Analyzer.class.getDeclaredMethod("nothing");

		for (Method method : methods){

			if (method.isAnnotationPresent(Before.class))
				before = method;

			if (method.isAnnotationPresent(After.class))
				after = method;
		}


		for (Method method: methods){

			if (method.isAnnotationPresent(Test.class)){
				Test an = method.getAnnotation(Test.class);
				try{

					before.invoke(before.getDeclaringClass().newInstance());

					method.invoke(method.getDeclaringClass().newInstance());
					pass++;

					after.invoke(after.getDeclaringClass().newInstance());

				}
				catch(Throwable e){
					if(!an.expected().getName().equals("MyJunit.annotations.Null"))
					{
						pass++;
						after.invoke(after.getDeclaringClass().newInstance());
						continue;
					}

					testWriter.write("- Test " + method.getName() + ": " + e.getCause().getMessage());
					fail++;
				}
			}
		}

		testWriter.write("\n\nTotal: " + (fail+pass) + "\n" +
						 "- Passed: " + pass + "\n" +
						 "- Failed: " + fail + "\n");

		testWriter.flush();
	}

	private void nothing(){}
}
