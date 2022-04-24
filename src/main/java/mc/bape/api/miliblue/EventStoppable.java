package mc.bape.api.miliblue;

public abstract class EventStoppable extends Event
{
    private boolean stopped;
    
    public void stop() {
        this.stopped = true;
    }
    
    public boolean isStopped() {
        return this.stopped;
    }
}
