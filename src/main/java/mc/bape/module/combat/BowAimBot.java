// L Bape, Decompiled by ImCzf233

package mc.bape.module.combat;

import mc.bape.utils.*;
import mc.bape.module.*;
import net.minecraftforge.fml.common.gameevent.*;
import mc.bape.vapu.*;
import net.minecraft.entity.*;
import mc.bape.module.player.*;
import mc.bape.manager.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.item.*;
import java.util.*;

public class BowAimBot extends Module
{
    private final TimerUtil timer;
    public EntityLivingBase target;
    public float rangeAimVelocity;
    
    public BowAimBot() {
        super("BowAimBot", 0, ModuleType.Combat, "AutoAim Target when you using bow");
        this.timer = new TimerUtil();
        this.rangeAimVelocity = 0.0f;
        BowAimBot.Chinese = "\u5f13\u7bad\u81ea\u7784";
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent event) {
        if (Client.nullCheck()) {
            return;
        }
        if (this.target == null) {
            return;
        }
        if (AntiBot.isServerBot((Entity)this.target)) {
            return;
        }
        if (Teams.isOnSameTeam((Entity)this.target)) {
            return;
        }
        if (FriendManager.isFriend(this.target.getName())) {
            return;
        }
        if (this.target.getHealth() == 0.0f) {
            return;
        }
        final ItemStack itemStack = BowAimBot.mc.thePlayer.inventory.getCurrentItem();
        if (itemStack == null || !(itemStack.getItem() instanceof ItemBow)) {
            return;
        }
        if (!BowAimBot.mc.gameSettings.keyBindUseItem.isKeyDown()) {
            return;
        }
        this.target = this.getClosestEntity();
        if (this.target == null) {
            return;
        }
        final int rangeCharge = BowAimBot.mc.thePlayer.getItemInUseCount();
        this.rangeAimVelocity = (float)(rangeCharge / 20);
        this.rangeAimVelocity = (this.rangeAimVelocity * this.rangeAimVelocity + this.rangeAimVelocity * 2.0f) / 3.0f;
        this.rangeAimVelocity = 1.0f;
        if (this.rangeAimVelocity > 1.0f) {
            this.rangeAimVelocity = 1.0f;
        }
        final double posX = this.target.posX - BowAimBot.mc.thePlayer.posX;
        final double posY = this.target.posY + this.target.getEyeHeight() - 0.15 - BowAimBot.mc.thePlayer.posY - BowAimBot.mc.thePlayer.getEyeHeight();
        final double posZ = this.target.posZ - BowAimBot.mc.thePlayer.posZ;
        final double y2 = Math.sqrt(posX * posX + posZ * posZ);
        final float g = 0.006f;
        final float tmp = (float)(this.rangeAimVelocity * this.rangeAimVelocity * this.rangeAimVelocity * this.rangeAimVelocity - g * (g * (y2 * y2) + 2.0 * posY * (this.rangeAimVelocity * this.rangeAimVelocity)));
        final float pitch = (float)(-Math.toDegrees(Math.atan((this.rangeAimVelocity * this.rangeAimVelocity - Math.sqrt(tmp)) / (g * y2))));
        Aimbot.assistFaceEntity((Entity)this.target, 22.0f, 0.0f);
        BowAimBot.mc.thePlayer.rotationPitch = pitch;
    }
    
    public boolean check(final EntityLivingBase entity) {
        return !(entity instanceof EntityArmorStand) && entity != BowAimBot.mc.thePlayer && !entity.isDead && !AntiBot.isServerBot((Entity)entity) && BowAimBot.mc.thePlayer.canEntityBeSeen((Entity)entity);
    }
    
    EntityLivingBase getClosestEntity() {
        EntityLivingBase closestEntity = null;
        for (final Entity o : Aimbot.getEntityList()) {
            final EntityLivingBase entity;
            if (o instanceof EntityLivingBase && !(o instanceof EntityArmorStand) && this.check(entity = (EntityLivingBase)o)) {
                if (closestEntity != null && BowAimBot.mc.thePlayer.getDistanceToEntity((Entity)entity) >= BowAimBot.mc.thePlayer.getDistanceToEntity((Entity)closestEntity)) {
                    continue;
                }
                closestEntity = entity;
            }
        }
        return closestEntity;
    }
}
