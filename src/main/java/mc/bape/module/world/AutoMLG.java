// L Bape, Decompiled by ImCzf233

package mc.bape.module.world;

import mc.bape.utils.*;
import mc.bape.module.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraft.item.*;

public class AutoMLG extends Module
{
    private final TimerUtil timer;
    private boolean handling;
    
    public AutoMLG() {
        super("AutoMLG", 0, ModuleType.World, "Auto use bukkit when you fall");
        this.timer = new TimerUtil();
        AutoMLG.Chinese = "\u81ea\u52a8\u843d\u5730\u6c34";
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent event) {
        if (AutoMLG.mc.isGamePaused() || event.phase == TickEvent.Phase.END) {
            return;
        }
        if (this.inPosition() && this.holdWaterBucket()) {
            this.handling = true;
        }
        if (this.handling) {
            this.mlg();
            if (AutoMLG.mc.thePlayer.onGround || AutoMLG.mc.thePlayer.motionY > 0.0) {
                this.reset();
            }
        }
    }
    
    public boolean inPosition() {
        try {
            if (AutoMLG.mc.thePlayer.motionY < -0.6 && !AutoMLG.mc.thePlayer.onGround && !AutoMLG.mc.thePlayer.capabilities.isFlying && !AutoMLG.mc.thePlayer.capabilities.isCreativeMode && !this.handling) {
                final BlockPos playerPos = AutoMLG.mc.thePlayer.getPosition();
                for (int i = 1; i < 3; ++i) {
                    final BlockPos blockPos = playerPos.down(i);
                    final Block block = AutoMLG.mc.theWorld.getBlockState(blockPos).getBlock();
                    if (block.isBlockSolid((IBlockAccess)AutoMLG.mc.theWorld, blockPos, EnumFacing.UP)) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        catch (NullPointerException exception) {
            System.err.print("\u50bb\u903c\u73a9\u610f\u4f60\u4ed6\u5988\u5199\u7684\u4ec0\u4e48\u8111\u6b8b\u4ee3\u7801\u6709" + exception.toString());
            return false;
        }
    }
    
    private boolean holdWaterBucket() {
        if (this.containsItem(AutoMLG.mc.thePlayer.getHeldItem(), Items.water_bucket)) {
            return true;
        }
        for (int i = 0; i < InventoryPlayer.getHotbarSize(); ++i) {
            if (this.containsItem(AutoMLG.mc.thePlayer.inventory.mainInventory[i], Items.water_bucket)) {
                AutoMLG.mc.thePlayer.inventory.currentItem = i;
                return true;
            }
        }
        return false;
    }
    
    private void mlg() {
        final ItemStack heldItem = AutoMLG.mc.thePlayer.getHeldItem();
        if (this.containsItem(heldItem, Items.water_bucket) && AutoMLG.mc.thePlayer.rotationPitch >= 70.0f) {
            final MovingObjectPosition object = AutoMLG.mc.objectMouseOver;
            if (object.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && object.sideHit == EnumFacing.UP) {
                AutoMLG.mc.playerController.sendUseItem((EntityPlayer)AutoMLG.mc.thePlayer, (World)AutoMLG.mc.theWorld, heldItem);
            }
        }
    }
    
    private void reset() {
        final ItemStack heldItem = AutoMLG.mc.thePlayer.getHeldItem();
        if (this.containsItem(heldItem, Items.bucket)) {
            AutoMLG.mc.playerController.sendUseItem((EntityPlayer)AutoMLG.mc.thePlayer, (World)AutoMLG.mc.theWorld, heldItem);
        }
        this.handling = false;
    }
    
    private boolean containsItem(final ItemStack itemStack, final Item item) {
        return itemStack != null && itemStack.getItem() == item;
    }
}
