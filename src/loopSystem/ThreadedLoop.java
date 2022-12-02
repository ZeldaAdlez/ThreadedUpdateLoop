package loopSystem;

import loopSystem.interfaces.Updateable;

public abstract class ThreadedLoop implements Runnable, Updateable
{
    protected final int threadCount;

    protected ThreadedLoop(int threadCount) throws IllegalArgumentException
    {
        if(threadCount <= 0)
            throw new IllegalArgumentException("Thread Count may not be zero or less than zero." + threadCount);
        this.threadCount = threadCount;
    }
}
