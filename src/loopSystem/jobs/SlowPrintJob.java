package loopSystem.jobs;

import loopSystem.interfaces.Updateable;

public class SlowPrintJob extends Job
{
    final int ID;
    int internalTime;

    public SlowPrintJob(int ID)
    {
        this.ID = ID;
        this.internalTime = 0;
    }

    @Override
    public void update()
    {
        internalTime++;
        if(internalTime % 100 ==0)
            System.out.println("Printing Job " + ID + " On Cycle " + internalTime);
    }

    @Override
    public void run()
    {
        update();
    }
}
