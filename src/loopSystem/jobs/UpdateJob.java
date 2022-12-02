package loopSystem.jobs;

import loopSystem.ThreadedLoop;
import loopSystem.interfaces.JobSystem;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UpdateJob extends ThreadedLoop
        implements JobSystem
{
    final Vector<JobSystem> jobs;
    final ExecutorService pool;

    public UpdateJob(int threadCount)
    {
        super(threadCount);
        jobs = new Vector<>(this.threadCount);
        pool = Executors.newFixedThreadPool(this.threadCount);
    }

    public void addJob(JobSystem system)
    {
        jobs.add(system);
    }

    public void removeJob(JobSystem job)
    {
        jobs.remove(job);
    }
    public void removeAt(int index)
    {
        if(index < 0 || index > jobs.size())
            throw new IndexOutOfBoundsException("Index was out of bounds with a value of " + index);
        jobs.remove(index);
    }

    @Override
    public void run()
    {
        update();
    }

    @Override
    public void update()
    {
        for (JobSystem job : jobs)
        {
            pool.execute(job);
        }
    }
}
