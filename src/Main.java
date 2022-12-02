import loopSystem.UpdateManager;
import loopSystem.jobs.PrintJob;
import loopSystem.jobs.SlowPrintJob;
import loopSystem.jobs.UpdateJob;
import loopSystem.jobs.UpdateJobBuilder;

public class Main
{
    static final int TICKS_PER_SECOND = 10;
    static final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
    static final int MAX_FRAMESKIP = 10;

    static long nextTick;
    static int loops;

    static boolean isRunning = true;

    public static void main(String[] args)
    {
        UpdateManager manager = new UpdateManager(12,2);

        manager.addLoopNode(new UpdateJobBuilder(4)
                .addJobs(new SlowPrintJob[]{new SlowPrintJob(1), new SlowPrintJob(2), new SlowPrintJob(3), new SlowPrintJob(4)})
                .build());
        manager.addLoopNode(new UpdateJobBuilder(4)
                .addJobs(new SlowPrintJob[]{new SlowPrintJob(10), new SlowPrintJob(20), new SlowPrintJob(30), new SlowPrintJob(40)})
                .build());

        while(isRunning)
        {
            loops = 0;
            while(getTicks() > nextTick && loops < MAX_FRAMESKIP)
            {
                manager.update();
                nextTick += SKIP_TICKS;
                loops++;
            }
        }
    }

    public static long getTicks()
    {
        return System.currentTimeMillis();
    }
}