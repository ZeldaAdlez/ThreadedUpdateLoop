package loopSystem;

import loopSystem.jobs.UpdateJob;

import java.util.Vector;

public class UpdateManager extends ThreadedLoop
{
    final Vector<UpdateJob> loops;

    public UpdateManager(int threadCount, int loopCount)
    {
        super(threadCount);
        loops = new Vector<>(loopCount);
        for (int i = 0; i < loops.capacity(); i++)
            loops.add(new UpdateJob(threadCount));
    }

    @Override
    public void run()
    {
        update();
    }

    @Override
    public void update()
    {
        for(UpdateJob loopSystem : loops) // these should ideally run sequentially
        {
            loopSystem.update();
        }
    }

    public void addJob()
    {

    }
}
