package MyJunit.main;

import java.util.Vector;

public class MainProcess
{
	public static Vector<String> queueOfTests = new Vector<>();

	public static void main(String [] data)
	{
		int ability = Runtime.getRuntime().availableProcessors();
		int threadsCount = 0;


		try
		{
			threadsCount = Integer.parseInt(data[0]);
		}
		catch(NumberFormatException e)
		{
			System.out.println("Incorrect number of threads");
		}


		if (threadsCount > ability)
		{
			System.out.println("The ability of processor less, than you think. Please, change the count of threads.");
			return;
		}


		TestThread[] threadsList = new TestThread[threadsCount];
		for(int i = 0; i < threadsCount; i++)
		{
			threadsList[i] = new TestThread();
			threadsList[i].start();
		}

		for(int i = 1; i < data.length; i++)
			queueOfTests.add(data[i]);

		try
		{
			while (!queueOfTests.isEmpty())
			{
				Thread.sleep(300);
			}

			for(TestThread thread : threadsList)
			{
				thread.interrupt();
			}

			for(int i = 0; i < threadsList.length; i++)
			{
				if (threadsList[i].isAlive())
				{
					i--;
					Thread.sleep(300);
				}
			}

		}
		catch(InterruptedException e)
		{
			//any thread has interrupted the current thread
			e.printStackTrace();
		}

	}
}
