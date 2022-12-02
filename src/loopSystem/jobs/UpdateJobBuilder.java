package loopSystem.jobs;

import loopSystem.interfaces.JobSystem;

import java.util.Vector;

public class UpdateJobBuilder
{
    UpdateJob root;

    public UpdateJobBuilder(int threadCount)
    {
        if(threadCount <= 0)
            throw new IllegalArgumentException("Thread Count may not be zero! " + threadCount);
        root = new UpdateJob(threadCount);
    }
    // fix this so it uses an interface so you can actually use the builder pattern correctly

    public UpdateJobBuilder addJob(JobSystem job){ root.addJob(job); return this; }
    public UpdateJobBuilder addJobs(JobSystem[] jobs) { root.addJobs(jobs); return this; }
    public UpdateJobBuilder addJobs(Vector<JobSystem> jobs) { root.addJobs(jobs); return this; }

    public UpdateJob build() { return root; }
}
