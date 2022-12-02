import loopSystem.UpdateManager;
import loopSystem.jobs.PrintJob;
import loopSystem.jobs.UpdateJob;

public class Main
{
    public static void main(String[] args) throws InterruptedException // Because lazy
    {
        UpdateManager loop = new UpdateManager(12,4); /// 0 based so 0-11
        for (int i = 0; i < 150; i++)
        {
            //UpdateJob job = new PrintJob(i);
            //loop.addJob(job,11); // Puts all jobs into thread 11 leaving threads 0 - 10 empty, they'll still run however

        }
        loop.update(); // Run the loop
    }
}