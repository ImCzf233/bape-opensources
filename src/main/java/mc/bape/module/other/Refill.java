// L Bape, Decompiled by ImCzf233

package mc.bape.module.other;

import mc.bape.utils.*;
import mc.bape.module.*;
import mc.bape.values.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.init.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.item.*;
import net.minecraft.entity.player.*;

public class Refill extends Module
{
    TimerUtil timer;
    Item TargetItem;
    private final Numbers<Double> delay;
    private final Option<Boolean> OpenInv;
    private final Mode<Enum> mode;
    
    public Refill() {
        super("Refill", 0, ModuleType.Other, "Refill your hot bar soup or pot");
        this.timer = new TimerUtil();
        this.delay = new Numbers<Double>("Delay", "Delay", 100.0, 50.0, 1000.0, 1.0);
        this.OpenInv = new Option<Boolean>("OpenInv", "OpenInv", false);
        this.mode = new Mode<Enum>("Mode", "mode", RefillMode.values(), RefillMode.Pot);
        this.addValues(this.delay, this.OpenInv, this.mode);
    }
    
    @SubscribeEvent
    public void onUpdate(final TickEvent.PlayerTickEvent event) {
        if (this.mode.getValue() == RefillMode.Soup) {
            this.TargetItem = Items.mushroom_stew;
        }
        else if (this.mode.getValue() == RefillMode.Pot) {
            final ItemPotion itempotion = Items.potionitem;
            this.TargetItem = ItemPotion.getItemById(373);
        }
        this.refill();
    }
    
    private void refill() {
        if ((!this.OpenInv.getValue() || Refill.mc.currentScreen instanceof GuiInventory) && !isHotbarFull() && this.timer.hasReached(this.delay.getValue())) {
            refill(this.TargetItem);
            this.timer.reset();
        }
    }
    
    public static boolean isHotbarFull() {
        for (int i = 0; i <= 36; ++i) {
            final ItemStack itemstack = Refill.mc.thePlayer.inventory.getStackInSlot(i);
            if (itemstack == null) {
                return false;
            }
        }
        return true;
    }
    
    public static void refill(final Item value) {
        for (int i = 9; i < 37; ++i) {
            final ItemStack itemstack = Refill.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
            if (itemstack != null && itemstack.getItem() == value) {
                Refill.mc.playerController.windowClick(0, i, 0, 1, (EntityPlayer)Refill.mc.thePlayer);
                break;
            }
        }
    }
    
    enum RefillMode
    {
        Pot, 
        Soup;
    }
}
