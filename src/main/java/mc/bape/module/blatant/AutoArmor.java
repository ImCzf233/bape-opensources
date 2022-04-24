// L Bape, Decompiled by ImCzf233

package mc.bape.module.blatant;

import mc.bape.utils.*;
import mc.bape.module.*;
import mc.bape.values.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.enchantment.*;

public class AutoArmor extends Module
{
    public static Numbers<Double> DELAY;
    public static Mode<Enum> MODE;
    private TimerUtil timer;
    
    public AutoArmor() {
        super("AutoArmor", 0, ModuleType.Blatant, "Auto put armor on");
        this.timer = new TimerUtil();
        this.addValues(AutoArmor.DELAY, AutoArmor.MODE);
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.PlayerTickEvent event) {
        if (this.isMoving()) {
            return;
        }
        final long delay = AutoArmor.DELAY.getValue().longValue() * 50L;
        if (AutoArmor.MODE.getValue() == EMode.OpenInv && !(AutoArmor.mc.currentScreen instanceof GuiInventory)) {
            return;
        }
        if ((AutoArmor.mc.currentScreen == null || AutoArmor.mc.currentScreen instanceof GuiInventory || AutoArmor.mc.currentScreen instanceof GuiChat) && this.timer.hasReached((double)delay)) {
            this.getBestArmor();
        }
    }
    
    public void getBestArmor() {
        for (int type = 1; type < 5; ++type) {
            if (AutoArmor.mc.thePlayer.inventoryContainer.getSlot(4 + type).getHasStack()) {
                final ItemStack is = AutoArmor.mc.thePlayer.inventoryContainer.getSlot(4 + type).getStack();
                if (isBestArmor(is, type)) {
                    continue;
                }
                if (AutoArmor.MODE.getValue() == EMode.FakeInv) {
                    final C16PacketClientStatus p = new C16PacketClientStatus(C16PacketClientStatus.EnumState.OPEN_INVENTORY_ACHIEVEMENT);
                    AutoArmor.mc.thePlayer.sendQueue.addToSendQueue((Packet)p);
                }
                this.drop(4 + type);
            }
            for (int i = 9; i < 45; ++i) {
                if (AutoArmor.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                    final ItemStack is2 = AutoArmor.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                    if (isBestArmor(is2, type) && getProtection(is2) > 0.0f) {
                        this.shiftClick(i);
                        this.timer.reset();
                        if (AutoArmor.DELAY.getValue().longValue() > 0L) {
                            return;
                        }
                    }
                }
            }
        }
    }
    
    public static boolean isBestArmor(final ItemStack stack, final int type) {
        final float prot = getProtection(stack);
        String strType = "";
        if (type == 1) {
            strType = "helmet";
        }
        else if (type == 2) {
            strType = "chestplate";
        }
        else if (type == 3) {
            strType = "leggings";
        }
        else if (type == 4) {
            strType = "boots";
        }
        if (!stack.getUnlocalizedName().contains(strType)) {
            return false;
        }
        for (int i = 5; i < 45; ++i) {
            if (AutoArmor.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                final ItemStack is = AutoArmor.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                if (getProtection(is) > prot && is.getUnlocalizedName().contains(strType)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void shiftClick(final int slot) {
        AutoArmor.mc.playerController.windowClick(AutoArmor.mc.thePlayer.inventoryContainer.windowId, slot, 0, 1, (EntityPlayer)AutoArmor.mc.thePlayer);
    }
    
    public void drop(final int slot) {
        AutoArmor.mc.playerController.windowClick(AutoArmor.mc.thePlayer.inventoryContainer.windowId, slot, 1, 4, (EntityPlayer)AutoArmor.mc.thePlayer);
    }
    
    public static float getProtection(final ItemStack stack) {
        float prot = 0.0f;
        if (stack.getItem() instanceof ItemArmor) {
            final ItemArmor armor = (ItemArmor)stack.getItem();
            prot += (float)(armor.damageReduceAmount + (100 - armor.damageReduceAmount) * EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, stack) * 0.0075);
            prot += (float)(EnchantmentHelper.getEnchantmentLevel(Enchantment.blastProtection.effectId, stack) / 100.0);
            prot += (float)(EnchantmentHelper.getEnchantmentLevel(Enchantment.fireProtection.effectId, stack) / 100.0);
            prot += (float)(EnchantmentHelper.getEnchantmentLevel(Enchantment.thorns.effectId, stack) / 100.0);
            prot += (float)(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack) / 50.0);
            prot += (float)(EnchantmentHelper.getEnchantmentLevel(Enchantment.featherFalling.effectId, stack) / 100.0);
        }
        return prot;
    }
    
    public boolean isMoving() {
        return AutoArmor.mc.thePlayer.moveForward != 0.0f || AutoArmor.mc.thePlayer.moveStrafing != 0.0f;
    }
    
    static {
        AutoArmor.DELAY = new Numbers<Double>("DELAY", "DELAY", 1.0, 0.0, 10.0, 1.0);
        AutoArmor.MODE = new Mode<Enum>("MODE", "MODE", EMode.values(), EMode.Basic);
    }
    
    public enum EMode
    {
        Basic, 
        OpenInv, 
        FakeInv;
    }
}
