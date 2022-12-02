package loopSystem;

import loopSystem.interfaces.JobSystem;
import java.util.Hashtable;
import java.util.Vector;

public class SynchronizedLoop extends ThreadedLoop
{
    int currentThread = 0;
    final Hashtable<Integer, Vector<JobSystem>> subLoops;

    public SynchronizedLoop(int threadCount) throws IllegalArgumentException
    {
        super(threadCount); // internally checks if threadCount is less or equal to zero and throws if so
        subLoops = new Hashtable<>();
        for (int i = 0; i < threadCount; i++)
            subLoops.put(i,new Vector<>());
    }

    public void addJob(JobSystem job)
    {
        subLoops.get(currentThread).add(job);
        currentThread++;
        if(currentThread >= threadCount)
            currentThread = 0;
    }

    public void addJob(JobSystem job, int threadID)
    {
        if(threadID >= threadCount || threadID < 0)
        {
            threadID = 0;
            throw new IndexOutOfBoundsException("Thread ID was out of range! Thread ID: " + threadID + " Thread Count " + threadCount);
        }
        subLoops.get(threadID).add(job);
    }

    @Override
    public void run()
    {
        update();
    }

    public void update()
    {
        for (Integer key : subLoops.keySet()) // iterate over all sub loops
        {
            System.out.println("Printing set " + key);
            int j = 0;
            while (j <= subLoops.get(key).size() - 1)
            {
                Thread thread = new Thread(subLoops.get(key).get(j)); // grab the current job from the current subLoop
                thread.start();
                try
                {
                    thread.join();
                } catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
                j++;
            }
        }
    }
}
