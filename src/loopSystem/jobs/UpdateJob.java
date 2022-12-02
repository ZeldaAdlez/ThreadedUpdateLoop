package loopSystem.jobs;

import loopSystem.ThreadedLoop;
import loopSystem.interfaces.JobSystem;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UpdateJob extends ThreadedLoop
        implements JobSystem
{
    Vector<JobSystem> jobs; // Technically due to this things design you can have infinite recursion, neat! :)
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
    public void addJobs(Vector<JobSystem> jobSystems)
    {
        if(jobSystems == null || jobSystems.size() == 0)
            return;
        int size = jobs.size() + jobSystems.size() + 1;
        Vector<JobSystem> newSystem = new Vector<>(size);
        newSystem.addAll(jobs);
        newSystem.addAll(jobSystems);
        jobs = newSystem;
    }
    public void addJobs(JobSystem[] jobSystems)
    {
        if(jobSystems == null || jobSystems.length == 0)
            return;
        int size = jobs.size() + jobSystems.length + 1;
        Vector<JobSystem> newSystem = new Vector<>(size);
        newSystem.addAll(jobs);
        for (int i = 0; i < jobSystems.length; i++)
        {
            JobSystem system = jobSystems[i];
            if(system == null)
                continue;
            newSystem.add(system);
        }
        jobs = newSystem;
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
            pool.execute(job);
    }
}
