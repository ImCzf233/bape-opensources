// L Bape, Decompiled by ImCzf233

package mc.bape.module.combat;

import mc.bape.utils.*;
import mc.bape.module.*;
import mc.bape.utils.math.ReflectionUtil;
import mc.bape.values.*;
import net.minecraftforge.fml.common.gameevent.*;
import mc.bape.vapu.*;
import mc.bape.utils.math.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.item.*;

public class AutoPot extends Module
{
    private int stage;
    private TimerUtil timer;
    private int oldSlot;
    private int potSlot;
    public Numbers<Double> health;
    static boolean currentlyPotting;
    public boolean isUsing;
    public int slot;
    
    public AutoPot() {
        super("AutoPot", 0, ModuleType.World, "Auto use pot when you low health");
        this.stage = 0;
        this.timer = new TimerUtil();
        this.health = new Numbers<Double>("Health", "health", 3.0, 0.0, 10.0, 0.5);
        this.isUsing = true;
        this.addValues(this.health);
    }
    
    @Override
    public void enable() {
        super.enable();
        this.timer.reset();
        if (AutoPot.mc.thePlayer == null) {
            this.setState(false);
        }
        else {
            this.oldSlot = AutoPot.mc.thePlayer.inventory.currentItem;
            this.potSlot = this.getPotionSlot();
        }
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.RenderTickEvent e) {
        if (Client.nullCheck()) {
            return;
        }
        this.oldSlot = AutoPot.mc.thePlayer.inventory.currentItem;
        this.potSlot = this.getPotionSlot();
        if (this.potSlot == -1) {
            this.setState(false);
        }
        final float f = this.health.getValue().floatValue();
        if (AutoPot.mc.thePlayer.getHealth() <= f) {
            AutoPot.mc.thePlayer.inventory.currentItem = this.potSlot;
            ReflectionUtil.rightClickMouse();
            AutoPot.mc.thePlayer.inventory.currentItem = this.oldSlot;
        }
    }
    
    public int getPotionSlot() {
        for (int i = 0; i < 8; ++i) {
            final ItemStack itemstack = AutoPot.mc.thePlayer.inventory.getStackInSlot(i);
            if (itemstack != null && !itemstack.isStackable()) {
                final Item item = itemstack.getItem();
                if (item instanceof ItemPotion) {
                    final ItemPotion itempotion = (ItemPotion)item;
                    if (ItemPotion.isSplash(itemstack.getMetadata())) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    
    static {
        AutoPot.currentlyPotting = false;
    }
}
