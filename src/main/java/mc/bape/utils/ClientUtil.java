// L Bape, Decompiled by ImCzf233

package mc.bape.utils;

import mc.bape.utils.notication.*;
import mc.bape.module.render.*;
import net.minecraft.util.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import java.awt.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import java.util.*;

public class ClientUtil
{
    private static ArrayList<Notification> notifications;
    static HUD hud;
    
    public static float[] getDirectionToBlock(final int var0, final int var1, final int var2, final EnumFacing var3) {
        final EntityEgg var4 = new EntityEgg((World)Helper.mc.theWorld);
        var4.posX = var0 + 0.5;
        var4.posY = var1 + 0.5;
        var4.posZ = var2 + 0.5;
        final EntityEgg entityEgg = var4;
        entityEgg.posX += var3.getDirectionVec().getX() * 0.25;
        final EntityEgg entityEgg2 = var4;
        entityEgg2.posY += var3.getDirectionVec().getY() * 0.25;
        final EntityEgg entityEgg3 = var4;
        entityEgg3.posZ += var3.getDirectionVec().getZ() * 0.25;
        return getDirectionToEntity((Entity)var4);
    }
    
    public static int reAlpha(final int color, final float alpha) {
        final Color c = new Color(color);
        final float r = 0.003921569f * c.getRed();
        final float g = 0.003921569f * c.getGreen();
        final float b = 0.003921569f * c.getBlue();
        return new Color(r, g, b, alpha).getRGB();
    }
    
    public static void drawNotifications() {
        try {
            final ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
            final double lastY;
            double startY = lastY = res.getScaledHeight() - 25;
            for (int i = 0; i < ClientUtil.notifications.size(); ++i) {
                final Notification not = ClientUtil.notifications.get(i);
                if (not.shouldDelete()) {
                    ClientUtil.notifications.remove(i);
                }
                if (ClientUtil.hud.NotificationType.getValue() == HUD.NofiType.Black1) {
                    not.drawBlack1(startY, lastY);
                }
                else if (ClientUtil.hud.NotificationType.getValue() == HUD.NofiType.Black2) {
                    not.drawBlack2(startY, lastY);
                }
                startY -= not.getHeight() + 1.0;
            }
        }
        catch (Throwable t) {}
    }
    
    public static void sendClientMessage(final String message, final Notification.Type type) {
        Helper.mc.thePlayer.playSound("random.click", 1.0f, 1.0f);
        if (ClientUtil.notifications.size() > 8) {
            ClientUtil.notifications.remove(0);
        }
        boolean has = false;
        for (final Notification n : ClientUtil.notifications) {
            if (n.getMessage().equals(message)) {
                has = true;
            }
        }
        if (!has) {
            ClientUtil.notifications.add(new Notification(message, type));
        }
    }
    
    private static float[] getDirectionToEntity(final Entity var0) {
        return new float[] { getYaw(var0) + Helper.mc.thePlayer.rotationYaw, getPitch(var0) + Helper.mc.thePlayer.rotationPitch };
    }
    
    public static float getYaw(final Entity entity) {
        final double x = entity.posX - Helper.mc.thePlayer.posX;
        final double y = entity.posY - Helper.mc.thePlayer.posY;
        final double z = entity.posZ - Helper.mc.thePlayer.posZ;
        double yaw = Math.atan2(x, z) * 57.29577951308232;
        yaw = -yaw;
        return (float)yaw;
    }
    
    public static float getPitch(final Entity entity) {
        final double x = entity.posX - Helper.mc.thePlayer.posX;
        double y = entity.posY - Helper.mc.thePlayer.posY;
        final double z = entity.posZ - Helper.mc.thePlayer.posZ;
        double pitch = Math.asin(y /= Helper.mc.thePlayer.getDistance(entity.posX, entity.posY, entity.posZ)) * 57.29577951308232;
        pitch = -pitch;
        return (float)pitch;
    }
    
    public static float getDirection() {
        float var1 = Helper.mc.thePlayer.rotationYaw;
        if (Helper.mc.thePlayer.moveForward < 0.0f) {
            var1 += 180.0f;
        }
        float forward = 1.0f;
        if (Helper.mc.thePlayer.moveForward < 0.0f) {
            forward = -0.5f;
        }
        else if (Helper.mc.thePlayer.moveForward > 0.0f) {
            forward = 0.5f;
        }
        if (Helper.mc.thePlayer.moveStrafing > 0.0f) {
            var1 -= 90.0f * forward;
        }
        if (Helper.mc.thePlayer.moveStrafing < 0.0f) {
            var1 += 90.0f * forward;
        }
        return var1 *= 0.017453292f;
    }
    
    static {
        ClientUtil.notifications = new ArrayList<Notification>();
        ClientUtil.hud = new HUD();
    }
}
