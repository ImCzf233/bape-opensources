// L Bape, Decompiled by ImCzf233

package mc.bape.gui.font;

import net.minecraft.client.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import java.security.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.block.material.*;
import net.minecraft.block.*;

public class Utils
{
    public static boolean fuck;
    private static Minecraft mc;
    
    public static boolean isContainerEmpty(final Container container) {
        for (int i = 0, slotAmount = (container.inventorySlots.size() == 90) ? 54 : 27; i < slotAmount; ++i) {
            if (container.getSlot(i).getHasStack()) {
                return false;
            }
        }
        return true;
    }
    
    public static Minecraft getMinecraft() {
        return Utils.mc;
    }
    
    public static boolean canBlock() {
        if (Utils.mc == null) {
            Utils.mc = Minecraft.getMinecraft();
        }
        return Utils.mc.thePlayer.getHeldItem() != null && (Utils.mc.thePlayer.isBlocking() || (Utils.mc.thePlayer.isUsingItem() && Utils.mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword) || (Utils.mc.thePlayer.getHeldItem().getItem() instanceof ItemSword && Minecraft.getMinecraft().gameSettings.keyBindUseItem.isPressed()));
    }
    
    public static String getMD5(final String input) {
        final StringBuilder res = new StringBuilder();
        try {
            final MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(input.getBytes());
            final byte[] arrby;
            final byte[] md5 = arrby = algorithm.digest();
            for (final byte aMd5 : arrby) {
                final String tmp = Integer.toHexString(0xFF & aMd5);
                if (tmp.length() == 1) {
                    res.append("0").append(tmp);
                }
                else {
                    res.append(tmp);
                }
            }
        }
        catch (NoSuchAlgorithmException ex) {}
        return res.toString();
    }
    
    public static void breakAnticheats() {
        Utils.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C04PacketPlayerPosition(Utils.mc.thePlayer.posX + Utils.mc.thePlayer.motionX, Utils.mc.thePlayer.posY - 110.0, Utils.mc.thePlayer.posZ + Utils.mc.thePlayer.motionZ, true));
    }
    
    public static int add(final int number, final int add, final int max) {
        return (number + add > max) ? max : (number + add);
    }
    
    public static int remove(final int number, final int remove, final int min) {
        return (number - remove < min) ? min : (number - remove);
    }
    
    public static int check(final int number) {
        return (number <= 0) ? 1 : ((number > 255) ? 255 : number);
    }
    
    public static double getDist() {
        double distance = 0.0;
        for (double i = Utils.mc.thePlayer.posY; i > 0.0; i -= 0.1) {
            if (i < 0.0) {
                break;
            }
            final Block block = Utils.mc.theWorld.getBlockState(new BlockPos(Utils.mc.thePlayer.posX, i, Utils.mc.thePlayer.posZ)).getBlock();
            if (block.getMaterial() != Material.air && block.isCollidable() && (block.isFullBlock() || block instanceof BlockSlab || block instanceof BlockBarrier || block instanceof BlockStairs || block instanceof BlockGlass || block instanceof BlockStainedGlass)) {
                if (block instanceof BlockSlab) {
                    i -= 0.5;
                }
                distance = i;
                break;
            }
        }
        return Utils.mc.thePlayer.posY - distance;
    }
    
    static {
        Utils.fuck = true;
        Utils.mc = Minecraft.getMinecraft();
    }
}
