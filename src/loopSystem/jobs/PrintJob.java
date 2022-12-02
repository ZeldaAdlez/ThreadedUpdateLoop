package loopSystem.jobs;

import loopSystem.jobs.Job;

public class PrintJob extends Job
{
    int ID;

    public PrintJob(int ID)
    {
        this.ID = ID;
    }

    @Override
    public void run()
    {
        update();
    }

    @Override
    public void update()
    {
        System.out.println("Job "+ID);
    }
}
