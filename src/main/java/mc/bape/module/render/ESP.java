// L Bape, Decompiled by ImCzf233

package mc.bape.module.render;

import mc.bape.module.*;
import mc.bape.utils.RenderHelper;
import mc.bape.values.*;
import net.minecraftforge.client.event.*;
import net.minecraft.entity.player.*;
import mc.bape.module.combat.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import mc.bape.vapu.*;
import net.minecraft.util.*;
import net.minecraft.client.renderer.*;
import mc.bape.utils.*;
import java.util.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ESP extends Module
{
    private final Option<Boolean> invisible;
    private final Option<Boolean> redOnDamage;
    private final Option<Boolean> item;
    
    public ESP() {
        super("ESP", 0, ModuleType.Player, "Draw boxes for other player and item");
        this.invisible = new Option<Boolean>("Invisible", "invisible", true);
        this.redOnDamage = new Option<Boolean>("RedOnDamage", "redOnDamage", true);
        this.item = new Option<Boolean>("Item", "item", true);
        this.addValues(this.invisible, this.redOnDamage);
        ESP.Chinese = "\u900f\u89c6";
    }
    
    @SubscribeEvent
    public void onTick(final RenderWorldLastEvent event) {
        for (final EntityPlayer entity : ESP.mc.theWorld.playerEntities) {
            if (entity != ESP.mc.thePlayer && !AntiBot.isServerBot((Entity)entity)) {
                if (!this.invisible.getValue() && entity.isInvisible()) {
                    return;
                }
                final int rgb = ColorUtil.getRainbow().getRGB();
                RenderHelper.drawESP((Entity)entity, rgb, this.redOnDamage.getValue(), 2);
            }
        }
        if (this.item.getValue()) {
            for (final Entity e : ESP.mc.theWorld.loadedEntityList) {
                if (!(e instanceof EntityItem)) {
                    continue;
                }
                final RenderManager renderManager = ESP.mc.getRenderManager();
                try {
                    final double renderPosX = (double)ReflectionUtil.getFieldValue(renderManager, "renderPosX");
                    final double renderPosY = (double)ReflectionUtil.getFieldValue(renderManager, "renderPosY");
                    final double renderPosZ = (double)ReflectionUtil.getFieldValue(renderManager, "renderPosZ");
                    final double x = e.lastTickPosX + (e.posX - e.lastTickPosX) * Objects.requireNonNull(Client.getTimer()).renderPartialTicks - renderPosX;
                    final double y = e.lastTickPosY + (e.posY - e.lastTickPosY) * Objects.requireNonNull(Client.getTimer()).renderPartialTicks - renderPosY;
                    final double z = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * Objects.requireNonNull(Client.getTimer()).renderPartialTicks - renderPosZ;
                    final AxisAlignedBB entityBox = e.getEntityBoundingBox();
                    final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(entityBox.minX - e.posX + x - 0.05, entityBox.minY - e.posY + y, entityBox.minZ - e.posZ + z - 0.05, entityBox.maxX - e.posX + x + 0.05, entityBox.maxY - e.posY + y + 0.15, entityBox.maxZ - e.posZ + z + 0.05);
                    GlStateManager.pushMatrix();
                    RenderUtil.R2DUtils.enableGL2D();
                    RenderUtils.glColor(ColorUtil.getRainbow());
                    RenderUtil.drawOutlinedBoundingBox(axisAlignedBB);
                    RenderUtil.R2DUtils.disableGL2D();
                    GlStateManager.popMatrix();
                }
                catch (NullPointerException ex) {}
            }
        }
    }
}
