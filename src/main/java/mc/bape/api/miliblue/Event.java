package mc.bape.api.miliblue;

public abstract class Event
{
    private boolean cancelled;
    public byte type;
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    public byte getType() {
        return this.type;
    }
    
    public void setType(final byte type) {
        this.type = type;
    }
    
    public void fire() {
        this.cancelled = false;
        EventSystem.fire(this);
    }
}
