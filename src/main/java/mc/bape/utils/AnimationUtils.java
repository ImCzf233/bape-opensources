package mc.bape.utils;

public class AnimationUtils
{
    public static float rotateDirection;
    public static boolean animationDone;
    public static double delta;
    
    public static float getAnimationState(float animation, final float finalState, final float speed) {
        final float add = (float)(AnimationUtils.delta * (speed / 1000.0f));
        if (animation < finalState) {
            if (animation + add < finalState) {
                animation += add;
            }
            else {
                animation = finalState;
            }
        }
        else if (animation - add > finalState) {
            animation -= add;
        }
        else {
            animation = finalState;
        }
        return animation;
    }
    
    public static float smoothAnimation(final float ani, final float finalState, final float speed, final float scale) {
        return getAnimationState(ani, finalState, Math.max(10.0f, Math.abs(ani - finalState) * speed) * scale);
    }
    
    public static float getRotateDirection() {
        AnimationUtils.rotateDirection += (float)AnimationUtils.delta;
        if (AnimationUtils.rotateDirection > 360.0f) {
            AnimationUtils.rotateDirection = 0.0f;
        }
        return AnimationUtils.rotateDirection;
    }
    
    static {
        AnimationUtils.rotateDirection = 0.0f;
        AnimationUtils.animationDone = false;
    }
}
