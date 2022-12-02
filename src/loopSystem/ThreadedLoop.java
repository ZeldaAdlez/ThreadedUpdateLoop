package loopSystem;

import loopSystem.interfaces.Updateable;

public abstract class ThreadedLoop implements Runnable, Updateable
{
    protected final int threadCount;

    protected ThreadedLoop(int threadCount)
    {
        this.threadCount = threadCount;
    }
}
