package mc.bape.utils.render;

public class AnimationUtil
{
    private static float defaultSpeed;
    
    public static float calculateCompensation(final float target, float current, long delta, final double speed) {
        final float diff = current - target;
        if (delta < 1L) {
            delta = 1L;
        }
        if (delta > 1000L) {
            delta = 16L;
        }
        if (diff > speed) {
            final double xD = (speed * delta / 16.0 < 0.5) ? 0.5 : (speed * delta / 16.0);
            if ((current -= (float)xD) < target) {
                current = target;
            }
        }
        else if (diff < -speed) {
            final double xD = (speed * delta / 16.0 < 0.5) ? 0.5 : (speed * delta / 16.0);
            if ((current += (float)xD) > target) {
                current = target;
            }
        }
        else {
            current = target;
        }
        return current;
    }
    
    public static float mvoeUD(final float current, final float end, final float minSpeed) {
        return moveUD(current, end, AnimationUtil.defaultSpeed, minSpeed);
    }
    
    public static float moveUD(final float current, final float end, final float smoothSpeed, final float minSpeed) {
        float movement = (end - current) * smoothSpeed;
        if (movement > 0.0f) {
            movement = Math.max(minSpeed, movement);
            movement = Math.min(end - current, movement);
        }
        else if (movement < 0.0f) {
            movement = Math.min(-minSpeed, movement);
            movement = Math.max(end - current, movement);
        }
        return current + movement;
    }
    
    static {
        AnimationUtil.defaultSpeed = 0.125f;
    }
}
