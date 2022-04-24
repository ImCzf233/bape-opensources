// L Bape, Decompiled by ImCzf233

package mc.bape.module.combat;

import mc.bape.utils.*;
import mc.bape.module.*;
import mc.bape.values.*;
import net.minecraftforge.fml.common.gameevent.*;
import mc.bape.module.blatant.*;
import net.minecraft.client.settings.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AutoClicker extends Module
{
    private final TimerUtil timer;
    private Numbers<Double> cps;
    private Numbers<Double> cpsMin;
    private Option<Boolean> autoblock;
    public boolean doBlock;
    
    public AutoClicker() {
        super("AutoClicker", 0, ModuleType.Combat, "auto Attack when you hold the attack button");
        this.timer = new TimerUtil();
        this.cps = new Numbers<Double>("CPSMax", "CpsMax", 5.0, 1.0, 20.0, 1.0);
        this.cpsMin = new Numbers<Double>("CPSMin", "CpsMin", 5.0, 1.0, 20.0, 1.0);
        this.autoblock = new Option<Boolean>("AutoBlock", "AutoBlock", false);
        this.doBlock = true;
        this.addValues(this.cps, this.cpsMin, this.autoblock);
        AutoClicker.Chinese = "\u8fde\u70b9\u5668";
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent event) {
        try {
            this.status = this.cps.getValue().toString();
            final int key = AutoClicker.mc.gameSettings.keyBindAttack.getKeyCode();
            if (AutoClicker.mc.gameSettings.keyBindAttack.isKeyDown()) {
                final float delays = (float)(Killaura.getRandomDoubleInRange(this.cpsMin.getValue(), this.cps.getValue()) + 2.0);
                if (this.timer.delay(delays * 10.0f)) {
                    AutoClicker.mc.thePlayer.swingItem();
                    KeyBinding.onTick(key);
                    try {
                        if (this.autoblock.getValue()) {
                            if (AutoClicker.mc.thePlayer.getCurrentEquippedItem() == null) {
                                return;
                            }
                            if (!(AutoClicker.mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword)) {
                                return;
                            }
                            if (this.autoblock.getValue() && AutoClicker.mc.objectMouseOver.entityHit != null && AutoClicker.mc.objectMouseOver.entityHit.isEntityAlive() && AutoClicker.mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword && this.timer.delay(100.0f)) {
                                AutoClicker.mc.thePlayer.getCurrentEquippedItem().useItemRightClick((World)AutoClicker.mc.theWorld, (EntityPlayer)AutoClicker.mc.thePlayer);
                                this.timer.reset();
                            }
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    this.timer.reset();
                }
            }
        }
        catch (NullPointerException ex) {}
    }
}
