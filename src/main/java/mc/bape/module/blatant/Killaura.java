// L Bape, Decompiled by ImCzf233

package mc.bape.module.blatant;

import mc.bape.module.*;
import mc.bape.values.*;
import net.minecraft.entity.*;
import mc.bape.vapu.*;
import net.minecraft.entity.item.*;
import mc.bape.module.combat.*;
import java.awt.*;
import com.ibm.icu.text.*;
import net.minecraft.client.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.gameevent.*;
import mc.bape.utils.*;
import net.minecraft.util.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.eventhandler.*;
import mc.bape.utils.math.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import java.util.*;
import java.util.List;

import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import mc.bape.manager.*;
import mc.bape.module.player.*;
import net.minecraft.client.entity.*;
import net.minecraft.network.play.client.*;
import net.minecraft.enchantment.*;
import net.minecraft.potion.*;

public class Killaura extends Module
{
    public static float rotationPitch;
    public static ArrayList<EntityLivingBase> targets;
    public static EntityLivingBase target;
    public static Numbers<Double> Turnspeed;
    public static Numbers<Double> Switchdelay;
    public static Numbers<Double> aps;
    public static long lastMS;
    public static long lastMS2;
    public static float[] facing;
    public static float sYaw;
    static boolean allowCrits;
    private int ticks;
    private int tpdelay;
    public boolean criticals;
    public ArrayList<EntityLivingBase> attackedTargets;
    public Mode<Enum> Priority;
    public Mode<Enum> mode;
    public Mode<Enum> hand;
    public Numbers<Double> crack;
    public Numbers<Double> reach;
    public Option<Boolean> blocking;
    public Option<Boolean> invis;
    public Option<Boolean> autoaim;
    public Option<Boolean> raycast;
    public TimerUtil test;
    public boolean doBlock;
    public boolean unBlock;
    public long lastMs;
    public float curYaw;
    public float curPitch;
    public int tick;
    public int index;
    public TimerUtil timer;
    public float[] facing0;
    public float[] facing1;
    public float[] facing2;
    public float[] facing3;
    
    public Killaura() {
        super("Killaura", 0, ModuleType.Blatant, "Auto Attack entity near you");
        this.attackedTargets = new ArrayList<EntityLivingBase>();
        this.Priority = new Mode<Enum>("Priority", "Priority", priority.values(), priority.Range);
        this.mode = new Mode<Enum>("Mode", "Mode", AuraMode.values(), AuraMode.Switch);
        this.hand = new Mode<Enum>("Mode", "Mode", handMode.values(), handMode.Vow);
        this.crack = new Numbers<Double>("CrackSize", "CrackSize", 1.0, 0.0, 5.0, 1.0);
        this.reach = new Numbers<Double>("Reach", "Reach", 4.5, 1.0, 6.0, 0.1);
        this.blocking = new Option<Boolean>("Autoblock", "Autoblock", true);
        this.invis = new Option<Boolean>("Invisibles", "Invisibles", false);
        this.autoaim = new Option<Boolean>("AutoAim", "AutoAim", false);
        this.raycast = new Option<Boolean>("Raycast", "Raycast", true);
        this.test = new TimerUtil();
        this.doBlock = false;
        this.unBlock = false;
        this.curYaw = 0.0f;
        this.curPitch = 0.0f;
        this.tick = 0;
        this.timer = new TimerUtil();
        this.addValues(this.hand, this.mode, Killaura.aps, this.reach, this.blocking, this.raycast);
    }
    
    public static List<Entity> getEntityList() {
        if (Client.nullCheck()) {
            return null;
        }
        if (Killaura.mc.theWorld != null) {
            return (List<Entity>)Killaura.mc.theWorld.getLoadedEntityList();
        }
        return null;
    }
    
    public boolean check(final EntityLivingBase entity) {
        return !Client.nullCheck() && !(entity instanceof EntityArmorStand) && entity != Killaura.mc.thePlayer && !entity.isDead && entity.getHealth() != 0.0f && !AntiBot.isServerBot((Entity)entity) && entity.getDistanceToEntity((Entity)Killaura.mc.thePlayer) <= this.reach.getValue() && Killaura.mc.thePlayer.canEntityBeSeen((Entity)entity);
    }
    
    public static Color blendColors(final float[] fractions, final Color[] colors, final float progress) {
        final Object color = null;
        if (fractions == null) {
            throw new IllegalArgumentException("Fractions can't be null");
        }
        if (colors == null) {
            throw new IllegalArgumentException("Colours can't be null");
        }
        if (fractions.length != colors.length) {
            throw new IllegalArgumentException("Fractions and colours must have equal number of elements");
        }
        final int[] indicies = getFractionIndicies(fractions, progress);
        final float[] range = { fractions[indicies[0]], fractions[indicies[1]] };
        final Color[] colorRange = { colors[indicies[0]], colors[indicies[1]] };
        final float max = range[1] - range[0];
        final float value = progress - range[0];
        final float weight = value / max;
        return blend(colorRange[0], colorRange[1], 1.0f - weight);
    }
    
    public static int[] getFractionIndicies(final float[] fractions, final float progress) {
        final int[] range = new int[2];
        int startPoint;
        for (startPoint = 0; startPoint < fractions.length && fractions[startPoint] <= progress; ++startPoint) {}
        if (startPoint >= fractions.length) {
            startPoint = fractions.length - 1;
        }
        range[0] = startPoint - 1;
        range[1] = startPoint;
        return range;
    }
    
    public static Color blend(final Color color1, final Color color2, final double ratio) {
        final float r = (float)ratio;
        final float ir = 1.0f - r;
        final float[] rgb1 = new float[3];
        final float[] rgb2 = new float[3];
        color1.getColorComponents(rgb1);
        color2.getColorComponents(rgb2);
        float red = rgb1[0] * r + rgb2[0] * ir;
        float green = rgb1[1] * r + rgb2[1] * ir;
        float blue = rgb1[2] * r + rgb2[2] * ir;
        if (red < 0.0f) {
            red = 0.0f;
        }
        else if (red > 255.0f) {
            red = 255.0f;
        }
        if (green < 0.0f) {
            green = 0.0f;
        }
        else if (green > 255.0f) {
            green = 255.0f;
        }
        if (blue < 0.0f) {
            blue = 0.0f;
        }
        else if (blue > 255.0f) {
            blue = 255.0f;
        }
        Color color3 = null;
        try {
            color3 = new Color(red, green, blue);
        }
        catch (IllegalArgumentException exp) {
            final NumberFormat nf = NumberFormat.getNumberInstance();
            System.out.println(nf.format((double)red) + "; " + nf.format((double)green) + "; " + nf.format((double)blue));
            exp.printStackTrace();
        }
        return color3;
    }
    
    public static double random(final double min, final double max) {
        final Random random = new Random();
        return min + (int)(random.nextDouble() * (max - min));
    }
    
    public static long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }
    
    public static boolean hit(final long milliseconds) {
        return getCurrentMS() - Killaura.lastMS >= milliseconds;
    }
    
    public static void revert() {
        Killaura.lastMS = getCurrentMS();
    }
    
    public static int randomNumber(final double min, final double max) {
        final Random random = new Random();
        return (int)(min + random.nextDouble() * (max - min));
    }
    
    public static int randomNumber1(final double min, final double max) {
        final Random random = new Random();
        return (int)(min + random.nextDouble() * (max - min));
    }
    
    public static float[] getRotationsNeededBlock(final double n, final double n2, final double n3) {
        final double n4 = n + 0.5 - Minecraft.getMinecraft().thePlayer.posX;
        final double n5 = n3 + 0.5 - Minecraft.getMinecraft().thePlayer.posZ;
        return new float[] { Minecraft.getMinecraft().thePlayer.rotationYaw + MathHelper.wrapAngleTo180_float((float)(Math.atan2(n5, n4) * 180.0 / 3.141592653589793) - 90.0f - Minecraft.getMinecraft().thePlayer.rotationYaw), Minecraft.getMinecraft().thePlayer.rotationPitch + MathHelper.wrapAngleTo180_float((float)(-Math.atan2(n2 + 0.5 - (Minecraft.getMinecraft().thePlayer.posY + Minecraft.getMinecraft().thePlayer.getEyeHeight()), MathHelper.sqrt_double(n4 * n4 + n5 * n5)) * 180.0 / 3.141592653589793) - Minecraft.getMinecraft().thePlayer.rotationPitch) };
    }
    
    public static float[] getRotationFromPosition(final double n, final double n2, final double n3) {
        final double n4 = n - Minecraft.getMinecraft().thePlayer.posX;
        final double n5 = n2 - Minecraft.getMinecraft().thePlayer.posZ;
        return new float[] { (float)(Math.atan2(n5, n4) * 180.0 / 3.141592653589793) - 90.0f, (float)(-Math.atan2(n3 - Minecraft.getMinecraft().thePlayer.posY - 1.2, MathHelper.sqrt_double(n4 * n4 + n5 * n5)) * 180.0 / 3.141592653589793) };
    }
    
    public static float[] getRotations(final Entity entity) {
        if (Client.nullCheck()) {
            return null;
        }
        if (entity == null) {
            return null;
        }
        return getRotationFromPosition(entity.posX, entity.posZ, entity.posY + entity.getEyeHeight() / 2.0f);
    }
    
    public static double getRandomDoubleInRange(final double minDouble, final double maxDouble) {
        return (minDouble >= maxDouble) ? minDouble : (new Random().nextDouble() * (maxDouble - minDouble) + minDouble);
    }
    
    public static float[] getRotationToEntity(final Entity target) {
        Minecraft.getMinecraft();
        final double xDiff = target.posX - Killaura.mc.thePlayer.posX;
        Minecraft.getMinecraft();
        final double yDiff = target.posY - Killaura.mc.thePlayer.posY;
        Minecraft.getMinecraft();
        final double zDiff = target.posZ - Killaura.mc.thePlayer.posZ;
        final float yaw = (float)(Math.atan2(zDiff, xDiff) * 180.0 / 3.141592653589793) - 90.0f;
        Minecraft.getMinecraft();
        Minecraft.getMinecraft();
        float pitch = (float)(-Math.atan2(target.posY + target.getEyeHeight() / 0.0 - (Killaura.mc.thePlayer.posY + Killaura.mc.thePlayer.getEyeHeight()), Math.hypot(xDiff, zDiff)) * 180.0 / 3.141592653589793);
        if (yDiff > -0.2 && yDiff < 0.2) {
            Minecraft.getMinecraft();
            Minecraft.getMinecraft();
            pitch = (float)(-Math.atan2(target.posY + target.getEyeHeight() / HitLocation.CHEST.getOffset() - (Killaura.mc.thePlayer.posY + Killaura.mc.thePlayer.getEyeHeight()), Math.hypot(xDiff, zDiff)) * 180.0 / 3.141592653589793);
        }
        else if (yDiff > -0.2) {
            Minecraft.getMinecraft();
            Minecraft.getMinecraft();
            pitch = (float)(-Math.atan2(target.posY + target.getEyeHeight() / HitLocation.FEET.getOffset() - (Killaura.mc.thePlayer.posY + Killaura.mc.thePlayer.getEyeHeight()), Math.hypot(xDiff, zDiff)) * 180.0 / 3.141592653589793);
        }
        else if (yDiff < 0.3) {
            Minecraft.getMinecraft();
            Minecraft.getMinecraft();
            pitch = (float)(-Math.atan2(target.posY + target.getEyeHeight() / HitLocation.HEAD.getOffset() - (Killaura.mc.thePlayer.posY + Killaura.mc.thePlayer.getEyeHeight()), Math.hypot(xDiff, zDiff)) * 180.0 / 3.141592653589793);
        }
        return new float[] { yaw, pitch };
    }
    
    public static float ROL(final float angle1, final float angle2) {
        float angle3 = Math.abs(angle1 - angle2) % 360.0f;
        if (angle3 > 180.0f) {
            angle3 = 0.0f;
        }
        return angle3;
    }
    
    public static float getYawDifference(final float current, final float target) {
        float rot = 0.0f;
        return rot + (((rot = (target + 180.0f - current) % 360.0f) > 0.0f) ? -180.0f : 180.0f);
    }
    
    public void color(final int color) {
        final float f = (color >> 24 & 0xFF) / 255.0f;
        final float f2 = (color >> 16 & 0xFF) / 255.0f;
        final float f3 = (color >> 8 & 0xFF) / 255.0f;
        final float f4 = (color & 0xFF) / 255.0f;
        GL11.glColor4f(f2, f3, f4, f);
    }
    
    public void drawRect(final double x1, final double y1, final double x2, final double y2, final int color) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        this.color(color);
        GL11.glBegin(7);
        GL11.glVertex2d(x2, y1);
        GL11.glVertex2d(x1, y1);
        GL11.glVertex2d(x1, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
    
    public void rectangleBordered(final double x, final double y, final double x1, final double y1, final double width, final int internalColor, final int borderColor) {
        this.drawRect(x + width, y + width, x1 - width, y1 - width, internalColor);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.drawRect(x + width, y, x1 - width, y + width, borderColor);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.drawRect(x, y, x + width, y1, borderColor);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.drawRect(x1 - width, y, x1, y1, borderColor);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.drawRect(x + width, y1 - width, x1 - width, y1, borderColor);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public boolean shouldAttack() {
        return this.timer.hasReached(1000 / Killaura.aps.getValue().intValue());
    }
    
    public void drawShadow(final Entity entity, final float partialTicks, final float pos, final boolean direction) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        GL11.glShadeModel(7425);
        final double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks - Killaura.mc.getRenderManager().viewerPosX;
        final double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks - Killaura.mc.getRenderManager().viewerPosY + pos;
        final double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks - Killaura.mc.getRenderManager().viewerPosZ;
        GL11.glBegin(8);
        for (int i = 0; i <= 180; ++i) {
            final double c1 = i * 3.141592653589793 * 2.0 / 180.0;
            final double c2 = (i + 1) * 3.141592653589793 * 2.0 / 180.0;
            GlStateManager.color(1.0f, 1.0f, 1.0f, 0.3f);
            GL11.glVertex3d(x + 0.5 * Math.cos(c1), y, z + 0.5 * Math.sin(c1));
            GL11.glVertex3d(x + 0.5 * Math.cos(c2), y, z + 0.5 * Math.sin(c2));
            GlStateManager.color(1.0f, 1.0f, 1.0f, 0.0f);
            GL11.glVertex3d(x + 0.5 * Math.cos(c1), y + (direction ? -0.2 : 0.2), z + 0.5 * Math.sin(c1));
            GL11.glVertex3d(x + 0.5 * Math.cos(c2), y + (direction ? -0.2 : 0.2), z + 0.5 * Math.sin(c2));
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glShadeModel(7424);
        GL11.glEnable(2929);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public void drawCircle(final Entity entity, final float partialTicks, final float pos) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        GL11.glShadeModel(7425);
        GL11.glLineWidth(1.0f);
        final double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks - Killaura.mc.getRenderManager().viewerPosX;
        final double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks - Killaura.mc.getRenderManager().viewerPosY + pos;
        final double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks - Killaura.mc.getRenderManager().viewerPosZ;
        GL11.glBegin(3);
        for (int i = 0; i <= 180; ++i) {
            final double c1 = i * 3.141592653589793 * 2.0 / 180.0;
            GlStateManager.color(2.0f, 1.0f, 1.0f, 1.0f);
            GL11.glVertex3d(x + 0.5 * Math.cos(c1), y, z + 0.5 * Math.sin(c1));
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glShadeModel(7424);
        GL11.glEnable(2929);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public boolean canBlock() {
        return Killaura.mc.thePlayer.getCurrentEquippedItem() != null && Killaura.mc.thePlayer.inventory.getCurrentItem().getItem() instanceof ItemSword;
    }
    
    @SubscribeEvent
    public void onUpdate(final TickEvent.PlayerTickEvent event) {
        this.status = this.mode.getValue().toString();
        if (Killaura.mc.thePlayer.ticksExisted % Killaura.Switchdelay.getValue().intValue() == 0 && Killaura.targets.size() > 1) {
            ++this.index;
        }
        if (!Killaura.targets.isEmpty() && this.index >= Killaura.targets.size()) {
            this.index = 0;
        }
        if (this.autoaim.getValue() && Killaura.target != null) {
            final float[] rotations = CombatUtil.getRotations((Entity)Killaura.target);
            Killaura.mc.thePlayer.rotationYawHead = rotations[0];
            Killaura.mc.thePlayer.rotationYaw = rotations[0];
        }
        this.doBlock = false;
        this.clear();
        this.findTargets(event);
        this.setCurTarget();
        if (this.hand.getValue() == handMode.Vow) {
            if (Killaura.target != null) {
                if (Client.nullCheck()) {
                    return;
                }
                final Random rand = new Random();
                this.facing0 = getRotationsNeededBlock(Killaura.target.posX, Killaura.target.posY, Killaura.target.posZ);
                this.facing1 = getRotationFromPosition(Killaura.target.posX, Killaura.target.posY, Killaura.target.posZ);
                this.facing2 = getRotationsNeededBlock(Killaura.target.posX, Killaura.target.posY, Killaura.target.posZ);
                this.facing3 = getRotations((Entity)Killaura.target);
                for (int i2 = 0; i2 <= 3; ++i2) {
                    switch (randomNumber(0.0, i2)) {
                        case 0: {
                            Killaura.facing = this.facing0;
                        }
                        case 1: {
                            Killaura.facing = this.facing1;
                        }
                        case 2: {
                            Killaura.facing = this.facing2;
                        }
                        case 3: {
                            Killaura.facing = this.facing3;
                            break;
                        }
                    }
                }
                if (Killaura.facing.length >= 0) {
                    event.player.setRotationYawHead(Killaura.facing[0]);
                    event.player.cameraPitch = Killaura.facing[1];
                }
                if (Killaura.target != null) {
                    Killaura.mc.thePlayer.renderYawOffset = Killaura.facing[0];
                    Killaura.mc.thePlayer.rotationYawHead = Killaura.facing[0];
                }
            }
            else {
                Killaura.targets.clear();
                this.attackedTargets.clear();
                this.lastMs = System.currentTimeMillis();
                if (this.unBlock) {
                    Killaura.mc.getNetHandler().addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
                    Killaura.mc.thePlayer.setItemInUse(Killaura.mc.thePlayer.getItemInUse(), 0);
                    this.unBlock = false;
                }
            }
        }
        if (this.hand.getValue() == handMode.Nov) {
            if (Killaura.target != null) {
                final Random rand = new Random();
                this.facing0 = getRotationsNeededBlock(Killaura.target.posX, Killaura.target.posY, Killaura.target.posZ);
                this.facing1 = getRotationFromPosition(Killaura.target.posX, Killaura.target.posY, Killaura.target.posZ);
                this.facing2 = getRotationsNeededBlock(Killaura.target.posX, Killaura.target.posY, Killaura.target.posZ);
                this.facing3 = getRotations((Entity)Killaura.target);
                for (int j = 0; j <= 3; ++j) {
                    switch (randomNumber(0.0, j)) {
                        case 0: {
                            Killaura.facing = this.facing0;
                        }
                        case 1: {
                            Killaura.facing = this.facing1;
                        }
                        case 2: {
                            Killaura.facing = this.facing2;
                        }
                        case 3: {
                            Killaura.facing = this.facing3;
                            break;
                        }
                    }
                }
                if (Killaura.facing.length >= 0) {
                    Killaura.Turnspeed.getValue().intValue();
                    event.player.setRotationYawHead(Killaura.facing[0]);
                    Killaura.Turnspeed.getValue().intValue();
                    event.player.cameraPitch = Killaura.facing[1];
                }
                if (Killaura.target != null) {
                    final Minecraft mc = Killaura.mc;
                    mc.thePlayer.renderYawOffset = Killaura.facing[0];
                    final Minecraft mc2 = Killaura.mc;
                    mc.thePlayer.rotationYawHead = Killaura.facing[0];
                }
                final int maxAngleStep = Killaura.Turnspeed.getValue().intValue();
                final int xz = (int)(randomNumber1(maxAngleStep, maxAngleStep) / 100.0);
                final Random rand2 = new Random();
                this.facing0 = getRotationsNeededBlock(Killaura.target.posX, Killaura.target.posY, Killaura.target.posZ);
                this.facing1 = getRotationFromPosition(Killaura.target.posX, Killaura.target.posY, Killaura.target.posZ);
                this.facing2 = getRotationsNeededBlock(Killaura.target.posX, Killaura.target.posY, Killaura.target.posZ);
                this.facing3 = getRotations((Entity)Killaura.target);
                for (int k = 0; k <= 3; ++k) {
                    switch (randomNumber1(0.0, k)) {
                        case 0: {
                            Killaura.facing = this.facing0;
                        }
                        case 1: {
                            Killaura.facing = this.facing1;
                        }
                        case 2: {
                            Killaura.facing = this.facing2;
                        }
                        case 3: {
                            Killaura.facing = this.facing3;
                            break;
                        }
                    }
                }
                if (Killaura.facing.length >= 0) {
                    event.player.setRotationYawHead(Killaura.facing[0]);
                    event.player.cameraPitch = Killaura.facing[1];
                }
                if (Killaura.target != null) {
                    Killaura.mc.thePlayer.renderYawOffset = Killaura.facing[0];
                    Killaura.mc.thePlayer.rotationYawHead = Killaura.facing[0];
                }
            }
            else {
                Killaura.targets.clear();
                this.attackedTargets.clear();
                this.lastMs = System.currentTimeMillis();
                if (this.unBlock) {
                    Killaura.mc.getNetHandler().addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
                    Killaura.mc.thePlayer.setItemInUse(Killaura.mc.thePlayer.getItemInUse(), 0);
                    this.unBlock = false;
                }
            }
        }
    }
    
    public void doAttack() {
        if (Client.nullCheck()) {
            return;
        }
        final int aps = Killaura.aps.getValue().intValue();
        final int delayValue = (int)(1000 / Killaura.aps.getValue().intValue() + MathUtil.randomDouble(-1.0, 2.0));
        if (Killaura.mc.thePlayer.getDistanceToEntity((Entity)Killaura.target) <= this.reach.getValue() + 0.4 && this.tick == 0 && this.test.delay((float)(delayValue - 1))) {
            final boolean miss = false;
            this.test.reset();
            if (Killaura.mc.thePlayer.isBlocking() || (Killaura.mc.thePlayer.getHeldItem() != null && Killaura.mc.thePlayer.getHeldItem().getItem() instanceof ItemSword && this.blocking.getValue())) {
                if (new Random().nextBoolean()) {
                    if (Killaura.mc.thePlayer.getCurrentEquippedItem() == null) {
                        return;
                    }
                    if (!(Killaura.mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword)) {
                        return;
                    }
                    if (Killaura.mc.objectMouseOver.entityHit != null && Killaura.mc.objectMouseOver.entityHit.isEntityAlive() && Killaura.mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword && this.timer.delay(100.0f)) {
                        Killaura.mc.thePlayer.getCurrentEquippedItem().useItemRightClick((World)Killaura.mc.theWorld, (EntityPlayer)Killaura.mc.thePlayer);
                        this.timer.reset();
                    }
                }
                this.unBlock = false;
            }
            if (!Killaura.mc.thePlayer.isBlocking() && !this.blocking.getValue() && Killaura.mc.thePlayer.getItemInUseCount() > 0) {
                Killaura.mc.thePlayer.setItemInUse(Killaura.mc.thePlayer.getItemInUse(), 0);
            }
            this.attack(miss);
            this.doBlock = true;
            if (!miss) {
                for (final Object o : Killaura.mc.theWorld.loadedEntityList) {
                    if (o instanceof EntityLivingBase) {
                        final EntityLivingBase entity;
                        if (!this.isValidEntity(entity = (EntityLivingBase)o)) {
                            continue;
                        }
                        this.attackedTargets.add(Killaura.target);
                    }
                }
            }
        }
    }
    
    public void swap(final int slot, final int hotbarNum) {
        Killaura.mc.playerController.windowClick(Killaura.mc.thePlayer.inventoryContainer.windowId, slot, hotbarNum, 2, (EntityPlayer)Killaura.mc.thePlayer);
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.PlayerTickEvent event) {
        if (Client.nullCheck()) {
            return;
        }
        this.sortList(Killaura.targets);
        if (Killaura.target != null) {
            this.doAttack();
            this.newAttack();
        }
        if (Killaura.target != null && ((Killaura.mc.thePlayer.getHeldItem() != null && Killaura.mc.thePlayer.getHeldItem().getItem() instanceof ItemSword && this.blocking.getValue()) || Killaura.mc.thePlayer.isBlocking()) && this.doBlock) {
            Killaura.mc.thePlayer.setItemInUse(Killaura.mc.thePlayer.getItemInUse(), Killaura.mc.thePlayer.getHeldItem().getMaxItemUseDuration());
            Killaura.mc.getNetHandler().addToSendQueue((Packet)new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, Killaura.mc.thePlayer.inventory.getCurrentItem(), 0.0f, 0.0f, 0.0f));
            this.unBlock = true;
        }
        final int i2 = 0;
    }
    
    public void attack(final boolean fake) {
        if (Client.nullCheck()) {
            return;
        }
        Killaura.mc.thePlayer.swingItem();
        if (!fake) {
            this.doBlock = true;
            if (ModuleManager.getModule("Criticals").getState() && Killaura.mc.thePlayer.onGround && !Criticals.canJump() && Killaura.target.hurtTime <= 1) {
                if (Criticals.mode.getValue() == Criticals.CriticalsMode.Jump && Criticals.mode.getValue() == Criticals.CriticalsMode.Legit) {
                    Killaura.mc.thePlayer.jump();
                }
                else if (Criticals.mode.getValue() == Criticals.CriticalsMode.Watchdog) {
                    Criticals.doWatchdogCirt();
                }
            }
            if (!Client.nullCheck() && Killaura.target != null) {
                Killaura.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C02PacketUseEntity((Entity)Killaura.target, C02PacketUseEntity.Action.ATTACK));
                if (Killaura.mc.thePlayer.isBlocking() && this.blocking.getValue() && Killaura.mc.thePlayer.inventory.getCurrentItem() != null && Killaura.mc.thePlayer.inventory.getCurrentItem().getItem() instanceof ItemSword) {
                    if (new Random().nextBoolean()) {
                        Killaura.mc.getNetHandler().addToSendQueue((Packet)new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, Killaura.mc.thePlayer.inventory.getCurrentItem(), 0.0f, 0.0f, 0.0f));
                    }
                    this.unBlock = true;
                }
                if (!Killaura.mc.thePlayer.isBlocking() && !this.blocking.getValue() && Killaura.mc.thePlayer.getItemInUseCount() > 0) {
                    Killaura.mc.thePlayer.setItemInUse(Killaura.mc.thePlayer.getItemInUse(), 0);
                }
            }
        }
    }
    
    public void newAttack() {
        if (Client.nullCheck()) {
            return;
        }
        if (Killaura.mc.thePlayer.isBlocking()) {
            for (int i = 0; i <= 2; ++i) {
                if (new Random().nextBoolean()) {
                    Killaura.mc.getNetHandler().addToSendQueue((Packet)new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, Killaura.mc.thePlayer.inventory.getCurrentItem(), 0.0f, 0.0f, 0.0f));
                }
            }
        }
        if (Killaura.mc.thePlayer.isBlocking()) {
            for (int i = 0; i <= 2; ++i) {
                if (new Random().nextBoolean()) {
                    Killaura.mc.getNetHandler().addToSendQueue((Packet)new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, Killaura.mc.thePlayer.inventory.getCurrentItem(), 0.0f, 0.0f, 0.0f));
                }
            }
        }
        if (Killaura.mc.thePlayer.isBlocking() && this.timer.delay(100.0f)) {
            for (int i = 0; i <= 2; ++i) {
                Killaura.mc.getNetHandler().addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
            }
        }
        if (!Killaura.mc.thePlayer.isBlocking() && !this.blocking.getValue() && Killaura.mc.thePlayer.getItemInUseCount() > 0) {
            Killaura.mc.thePlayer.setItemInUse(Killaura.mc.thePlayer.getItemInUse(), 0);
        }
    }
    
    public void setCurTarget() {
        if (Client.nullCheck()) {
            return;
        }
        for (final Entity object : getEntityList()) {
            if (object instanceof EntityLivingBase) {
                final EntityLivingBase entity;
                if (!this.check(entity = (EntityLivingBase)object)) {
                    continue;
                }
                Killaura.target = entity;
            }
        }
    }
    
    public void clear() {
        if (Client.nullCheck()) {
            return;
        }
        Killaura.target = null;
        Killaura.targets.clear();
        for (final EntityLivingBase ent : Killaura.targets) {
            if (this.isValidEntity(ent)) {
                continue;
            }
            Killaura.targets.remove(ent);
            if (!this.attackedTargets.contains(ent)) {
                continue;
            }
            this.attackedTargets.remove(ent);
        }
    }
    
    public void findTargets(final TickEvent.PlayerTickEvent event) {
        if (Client.nullCheck()) {
            return;
        }
        final int maxSize = (this.mode.getValue() == AuraMode.Switch) ? 4 : 1;
        for (final Entity o3 : Killaura.mc.theWorld.loadedEntityList) {
            final EntityLivingBase curEnt;
            if (o3 instanceof EntityLivingBase && this.isValidEntity(curEnt = (EntityLivingBase)o3) && !Killaura.targets.contains(curEnt)) {
                Killaura.targets.add(curEnt);
            }
            if (Killaura.targets.size() >= maxSize) {
                break;
            }
        }
        try {
            Killaura.targets.sort((o1, o2) -> (int)(((EntityLivingBase)o1).getDistanceToEntity((Entity)o2) - o2.getDistanceToEntity(o1)));
        }
        catch (ConcurrentModificationException ex) {}
    }
    
    public boolean isValidEntity(final EntityLivingBase ent) {
        if (Client.nullCheck()) {
            return true;
        }
        final AntiBot ab = (AntiBot)ModuleManager.getModule("AntiBot");
        if (ent != null && ent != Killaura.mc.thePlayer) {
            if (ent instanceof EntityMob || ent instanceof EntityVillager || ent instanceof EntityBat) {}
            if (Killaura.mc.thePlayer.getDistanceToEntity((Entity)ent) <= this.reach.getValue() + 0.4 && (!(ent instanceof EntityPlayer) || !FriendManager.isFriend(ent.getName())) && !ent.isDead && ent.getHealth() > 0.0f && (!ent.isInvisible() || this.invis.getValue()) && !AntiBot.isServerBot((Entity)ent) && !Killaura.mc.thePlayer.isDead && (!(ent instanceof EntityPlayer) || !Teams.isOnSameTeam((Entity)ent))) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void enable() {
        this.index = 0;
        this.curYaw = Killaura.mc.thePlayer.rotationYaw;
        this.curPitch = Killaura.mc.thePlayer.rotationPitch;
    }
    
    @Override
    public void disable() {
        Killaura.targets.clear();
        this.attackedTargets.clear();
        Killaura.target = null;
        Killaura.mc.thePlayer.setItemInUse(Killaura.mc.thePlayer.getItemInUse(), 0);
        Killaura.allowCrits = true;
        Killaura.mc.thePlayer.renderYawOffset = Killaura.mc.thePlayer.rotationYaw;
        Killaura.rotationPitch = 0.0f;
        this.curYaw = Killaura.mc.thePlayer.rotationYaw;
        this.curPitch = Killaura.mc.thePlayer.rotationPitch;
    }
    
    public void sortList(final List<EntityLivingBase> weed) {
        if (this.Priority.getValue() == priority.Range) {
            try {
                weed.sort((o1, o2) -> (int)(o1.getDistanceToEntity((Entity)Killaura.mc.thePlayer) - o2.getDistanceToEntity((Entity)Killaura.mc.thePlayer)));
            }
            catch (ConcurrentModificationException ex) {}
        }
    }
    
    public float getYawDifference(final float yaw, final EntityLivingBase target) {
        return getYawDifference(yaw, getRotationToEntity((Entity)target)[0]);
    }
    
    @SubscribeEvent
    public void doMultiAttack(final TickEvent.PlayerTickEvent event) {
        if (Client.nullCheck()) {
            return;
        }
        if (this.mode.getValue() == AuraMode.Multi) {
            try {
                ++this.ticks;
                ++this.tpdelay;
                if (this.ticks >= 20 - this.speed()) {
                    this.ticks = 0;
                    for (final Object object : Killaura.mc.theWorld.loadedEntityList) {
                        final EntityLivingBase entity;
                        if (object instanceof EntityLivingBase && !((entity = (EntityLivingBase)object) instanceof EntityPlayerSP) && Killaura.mc.thePlayer.getDistanceToEntity((Entity)entity) <= 10.0f) {
                            if (!entity.isEntityAlive()) {
                                continue;
                            }
                            if (this.tpdelay >= 4) {
                                Killaura.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C04PacketPlayerPosition(entity.posX, entity.posY, entity.posZ, false));
                            }
                            if (Killaura.mc.thePlayer.getDistanceToEntity((Entity)entity) >= 10.0f) {
                                continue;
                            }
                            this.attack(entity);
                        }
                    }
                }
            }
            catch (ConcurrentModificationException ex) {}
        }
    }
    
    public void attack(final EntityLivingBase entity) {
        if (Client.nullCheck()) {
            return;
        }
        this.attack(entity, false);
    }
    
    public void attack(final EntityLivingBase entity, final boolean crit) {
        if (Client.nullCheck()) {
            return;
        }
        try {
            Killaura.mc.thePlayer.swingItem();
            final boolean vanillaCrit = Killaura.mc.thePlayer.fallDistance > 0.0f && !Killaura.mc.thePlayer.onGround && !Killaura.mc.thePlayer.isOnLadder() && !Killaura.mc.thePlayer.isInWater() && !Killaura.mc.thePlayer.isPotionActive(Potion.blindness) && Killaura.mc.thePlayer.ridingEntity == null;
            Killaura.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C02PacketUseEntity((Entity)entity, C02PacketUseEntity.Action.ATTACK));
            if (crit || vanillaCrit) {
                Killaura.mc.thePlayer.onCriticalHit((Entity)entity);
            }
        }
        catch (ConcurrentModificationException ex) {}
    }
    
    private int speed() {
        return 8;
    }
    
    static {
        Killaura.targets = new ArrayList<EntityLivingBase>();
        Killaura.target = null;
        Killaura.Turnspeed = new Numbers<Double>("TurnSpeed", "TurnSpeed", 90.0, 1.0, 180.0, 1.0);
        Killaura.Switchdelay = new Numbers<Double>("Switchdelay", "switchdelay", 11.0, 0.0, 50.0, 1.0);
        Killaura.aps = new Numbers<Double>("CPS", "CPS", 10.0, 1.0, 20.0, 0.5);
    }
    
    enum HitLocation
    {
        AUTO(0.0), 
        HEAD(1.0), 
        CHEST(1.5), 
        FEET(3.5);
        
        public double offset;
        
        private HitLocation(final double offset) {
            this.offset = offset;
        }
        
        public double getOffset() {
            return this.offset;
        }
    }
    
    enum priority
    {
        Range;
    }
    
    enum AuraMode
    {
        Switch, 
        Single, 
        Multi;
    }
    
    enum handMode
    {
        Vow, 
        Nov;
    }
}
