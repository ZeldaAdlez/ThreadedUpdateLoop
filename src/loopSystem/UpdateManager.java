package loopSystem;

import loopSystem.jobs.UpdateJob;
import java.util.Vector;

public class UpdateManager extends ThreadedLoop
{
    final Vector<UpdateJob> loopNodes;

    public UpdateManager(int threadCount, int loopCount)
    {
        super(threadCount);
        loopNodes = new Vector<>(loopCount);
        for (int i = 0; i < loopNodes.capacity(); i++)
            loopNodes.add(new UpdateJob(this.threadCount));
    }

    @Override
    public void run()
    {
        update();
    }

    @Override
    public void update()
    {
        for(UpdateJob loopSystem : loopNodes) // these should ideally run sequentially, they do not
            loopSystem.update();
    }
    public void addLoopNode(UpdateJob job) { loopNodes.add(job); }
    public void insertLoopNode(UpdateJob job, int index)
    {
        if(index < 0 || index > loopNodes.size())
            throw new IndexOutOfBoundsException("Index was out of range! " + index);
        loopNodes.insertElementAt(job, index);
    }
    public void pushLoopNode(UpdateJob job)
    {
        loopNodes.insertElementAt(job,0);
    }
    public UpdateJob popLoopNode()
    {
        int lastIndex = loopNodes.size() - 1;
        UpdateJob job = loopNodes.elementAt(lastIndex);
        loopNodes.remove(lastIndex);
        return job;
    }
}
