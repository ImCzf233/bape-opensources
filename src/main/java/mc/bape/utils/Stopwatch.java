package mc.bape.utils;

public class Stopwatch
{
    private long lastMS;
    
    public Stopwatch() {
        this.lastMS = 0L;
    }
    
    public int convertToMS(final int d) {
        return 1000 / d;
    }
    
    public long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }
    
    public boolean hasReached(final long milliseconds) {
        return this.getCurrentMS() - this.lastMS >= milliseconds;
    }
    
    public boolean hasTimeReached(final long delay) {
        return System.currentTimeMillis() - this.lastMS >= delay;
    }
    
    public long getDelay() {
        return System.currentTimeMillis() - this.lastMS;
    }
    
    public void reset() {
        this.lastMS = this.getCurrentMS();
    }
    
    public void setLastMS() {
        this.lastMS = System.currentTimeMillis();
    }
    
    public void setLastMS(final long lastMS) {
        this.lastMS = lastMS;
    }
}
