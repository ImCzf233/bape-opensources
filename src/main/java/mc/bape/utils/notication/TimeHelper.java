package mc.bape.utils.notication;

public class TimeHelper
{
    public long lastMs;
    private long prevMS;
    
    public TimeHelper() {
        this.lastMs = 0L;
    }
    
    public boolean isDelayComplete(final long delay) {
        return System.currentTimeMillis() - this.lastMs > delay;
    }
    
    public boolean isDelayComplete(final double delay) {
        return System.currentTimeMillis() - this.lastMs > delay;
    }
    
    public long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }
    
    public void reset() {
        this.lastMs = System.currentTimeMillis();
    }
    
    public long getLastMs() {
        return this.lastMs;
    }
    
    public void setLastMs(final int i) {
        this.lastMs = System.currentTimeMillis() + i;
    }
    
    public boolean hasReached(final long milliseconds) {
        return System.currentTimeMillis() - this.lastMs >= milliseconds;
    }
    
    public boolean hasReached(final double milliseconds) {
        return System.currentTimeMillis() - this.lastMs >= milliseconds;
    }
    
    public boolean delay(final float milliSec) {
        return this.getTime() - this.prevMS >= milliSec;
    }
    
    public long getTime() {
        return System.nanoTime() / 1000000L;
    }
    
    public long getDifference() {
        return this.getTime() - this.prevMS;
    }
    
    public boolean check(final float milliseconds) {
        return this.getTime() >= milliseconds;
    }
    
    public long getCurrentTime() {
        return System.currentTimeMillis();
    }
}
