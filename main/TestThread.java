package MyJunit.main;

import static MyJunit.main.MainProcess.queueOfTests;

/**
 * Created by Мария on 18.05.2016.
 */
public class TestThread extends Thread
{
	@Override
	public void run()
	{
		while(!isInterrupted())
		{
			try
			{
				String testName = queueOfTests.remove(0);
				Analyzer an = new Analyzer(Class.forName(testName), getId());
				an.parse();
			}
			catch (ArrayIndexOutOfBoundsException e)
			{
				// попали в момент, когда очередь пустая, но main еще, вероятно, положит туда чего-нибудь
			}
			catch (ClassNotFoundException e)
			{
				System.out.println("Test class is not found");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

		}
	}
}
