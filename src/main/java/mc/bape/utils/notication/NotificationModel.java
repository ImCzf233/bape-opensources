// L Bape, Decompiled by ImCzf233

package mc.bape.utils.notication;

import net.minecraft.client.*;
import net.minecraft.client.gui.*;

public class NotificationModel
{
    public String content;
    public NotificationType type;
    public static float x;
    public static float y;
    
    public NotificationModel(final String content, final NotificationType type) {
        this.content = content;
        this.type = type;
    }
    
    public float getX() {
        return NotificationModel.x;
    }
    
    public float getY() {
        return NotificationModel.y;
    }
    
    public void setX(final float x) {
        NotificationModel.x = x;
    }
    
    public void setY(final float y) {
        NotificationModel.y = y;
    }
    
    static {
        NotificationModel.x = (float)new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth_double();
    }
}
