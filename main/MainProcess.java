package MyJunit.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainProcess
{
	public static void main(String [] data)
	{
		int ability = Runtime.getRuntime().availableProcessors();
		int threadsCount = 0;

		List<String> queueOfTests = new ArrayList<>();

		try
		{   if(data[0].matches("\\d+"))
				threadsCount = Integer.parseInt(data[0]);
			else
			{
				System.out.println("Incorrect number of threads");
				return;
			}
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
		}

		if (threadsCount > ability)
		{
			System.out.println("The ability of processor less, than you think. Please, change the count of threads.");
			return;
		}

		Analyzer[] threads = new Analyzer[threadsCount];



		for(int i = 1; i < data.length; i++)
			queueOfTests.add(data[i]);



		for(int i = 0; i < queueOfTests.size(); i++)
		{
			for(int j = 0; j < threads.length; j++)
			{
				try
				{
					Class testClass = Class.forName(queueOfTests.get(i));
					threads[j] = new Analyzer(testClass);
					//Analyzer thread = new Analyzer(testClass);
					i++;
					try
					{
						threads[j].start();
						//thread.start();
					}
					catch(Throwable e)
					{
						e.printStackTrace();
					}
				}
				catch(ClassNotFoundException e)
				{
					e.printStackTrace();
				}
			}
		}


		for(Analyzer thread : threads)
		{
			while (thread.isAlive())
			{
				try
				{
					Thread.sleep(500);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}


	}
}
