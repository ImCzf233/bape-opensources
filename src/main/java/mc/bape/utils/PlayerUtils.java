// L Bape, Decompiled by ImCzf233

package mc.bape.utils;

import net.minecraft.client.*;
import net.minecraft.block.material.*;
import net.minecraft.block.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.world.*;
import net.minecraft.potion.*;
import net.minecraft.client.entity.*;
import org.lwjgl.util.vector.*;
import net.minecraft.entity.ai.attributes.*;
import com.google.common.collect.*;
import java.util.*;
import net.minecraft.entity.*;
import net.minecraft.enchantment.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.util.*;

public class PlayerUtils
{
    public static Minecraft mc;
    
    public static boolean isNotMoving() {
        return PlayerUtils.mc.thePlayer.moveForward == 0.0f && PlayerUtils.mc.thePlayer.moveStrafing == 0.0f;
    }
    
    public static boolean isInventoryFull() {
        for (int index = 9; index <= 44; ++index) {
            final ItemStack stack = PlayerUtils.mc.thePlayer.inventoryContainer.getSlot(index).getStack();
            if (stack == null) {
                return false;
            }
        }
        return true;
    }
    
    public static double getDistanceToFall() {
        double distance = 0.0;
        for (double i = PlayerUtils.mc.thePlayer.posY; i > 0.0; --i) {
            final Block block = BlockUtils.getBlock(new BlockPos(PlayerUtils.mc.thePlayer.posX, i, PlayerUtils.mc.thePlayer.posZ));
            if (block.getMaterial() != Material.air && block.isBlockNormalCube() && block.isCollidable()) {
                distance = i;
                break;
            }
            if (i < 0.0) {
                break;
            }
        }
        final double distancetofall = PlayerUtils.mc.thePlayer.posY - distance - 1.0;
        return distancetofall;
    }
    
    public static float[] aimAtLocation(final double x, final double y, final double z, final EnumFacing facing) {
        final EntitySnowball temp = new EntitySnowball((World)PlayerUtils.mc.theWorld);
        temp.posX = x + 0.5;
        temp.posY = y + 0.5;
        temp.posZ = z + 0.5;
        final EntitySnowball entitySnowball4;
        final EntitySnowball entitySnowball = entitySnowball4 = temp;
        entitySnowball4.posX += facing.getDirectionVec().getX() * 0.25;
        final EntitySnowball entitySnowball5;
        final EntitySnowball entitySnowball2 = entitySnowball5 = temp;
        entitySnowball5.posY += facing.getDirectionVec().getY() * 0.25;
        final EntitySnowball entitySnowball6;
        final EntitySnowball entitySnowball3 = entitySnowball6 = temp;
        entitySnowball6.posZ += facing.getDirectionVec().getZ() * 0.25;
        return aimAtLocation(temp.posX, temp.posY, temp.posZ);
    }
    
    public static float[] aimAtLocation(final double positionX, final double positionY, final double positionZ) {
        final double x = positionX - PlayerUtils.mc.thePlayer.posX;
        final double y = positionY - PlayerUtils.mc.thePlayer.posY;
        final double z = positionZ - PlayerUtils.mc.thePlayer.posZ;
        final double distance = MathHelper.sqrt_double(x * x + z * z);
        return new float[] { (float)(Math.atan2(z, x) * 180.0 / 3.141592653589793) - 90.0f, (float)(-(Math.atan2(y, distance) * 180.0 / 3.141592653589793)) };
    }
    
    public void portMove(final float yaw, final float multiplyer, final float up) {
        final double moveX = -Math.sin(Math.toRadians(yaw)) * multiplyer;
        final double moveZ = Math.cos(Math.toRadians(yaw)) * multiplyer;
        final double moveY = up;
        PlayerUtils.mc.thePlayer.setPosition(moveX + PlayerUtils.mc.thePlayer.posX, moveY + PlayerUtils.mc.thePlayer.posY, moveZ + PlayerUtils.mc.thePlayer.posZ);
    }
    
    public static double getBaseMoveSpeed() {
        double baseSpeed = 0.2873;
        if (PlayerUtils.mc.thePlayer.isPotionActive(Potion.moveSpeed)) {
            final int amplifier = PlayerUtils.mc.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier();
            baseSpeed *= 1.0 + 0.2 * (amplifier + 1);
        }
        return baseSpeed;
    }
    
    public static float getDirection() {
        float yaw = PlayerUtils.mc.thePlayer.rotationYaw;
        if (PlayerUtils.mc.thePlayer.moveForward < 0.0f) {
            yaw += 180.0f;
        }
        float forward = 1.0f;
        if (PlayerUtils.mc.thePlayer.moveForward < 0.0f) {
            forward = -0.5f;
        }
        else if (PlayerUtils.mc.thePlayer.moveForward > 0.0f) {
            forward = 0.5f;
        }
        if (PlayerUtils.mc.thePlayer.moveStrafing > 0.0f) {
            yaw -= 90.0f * forward;
        }
        if (PlayerUtils.mc.thePlayer.moveStrafing < 0.0f) {
            yaw += 90.0f * forward;
        }
        yaw *= 0.017453292f;
        return yaw;
    }
    
    public static boolean isInWater() {
        return PlayerUtils.mc.theWorld.getBlockState(new BlockPos(PlayerUtils.mc.thePlayer.posX, PlayerUtils.mc.thePlayer.posY, PlayerUtils.mc.thePlayer.posZ)).getBlock().getMaterial() == Material.water;
    }
    
    public static void toFwd(final double speed) {
        final float yaw = PlayerUtils.mc.thePlayer.rotationYaw * 0.017453292f;
        EntityPlayerSP thePlayer = thePlayer = PlayerUtils.mc.thePlayer;
        thePlayer.motionX -= MathHelper.sin(yaw) * speed;
        EntityPlayerSP thePlayer2 = thePlayer2 = PlayerUtils.mc.thePlayer;
        thePlayer2.motionZ += MathHelper.cos(yaw) * speed;
    }
    
    public static void setSpeed(final double speed) {
        PlayerUtils.mc.thePlayer.motionX = -(Math.sin(getDirection()) * speed);
        PlayerUtils.mc.thePlayer.motionZ = Math.cos(getDirection()) * speed;
    }
    
    public static double getSpeed() {
        return getSpeed((Entity)PlayerUtils.mc.thePlayer);
    }
    
    public static double getSpeed(final Entity entity) {
        return Math.sqrt(entity.motionX * entity.motionX + entity.motionZ * entity.motionZ);
    }
    
    public static Block getBlockUnderPlayer(final EntityPlayer inPlayer) {
        return getBlock(new BlockPos(inPlayer.posX, inPlayer.posY - 1.0, inPlayer.posZ));
    }
    
    public static Block getBlock(final BlockPos pos) {
        return PlayerUtils.mc.theWorld.getBlockState(pos).getBlock();
    }
    
    public static Block getBlockAtPosC(final EntityPlayer inPlayer, final double x, final double y, final double z) {
        return getBlock(new BlockPos(inPlayer.posX - x, inPlayer.posY - y, inPlayer.posZ - z));
    }
    
    public static ArrayList<Vector3f> vanillaTeleportPositions(final double tpX, final double tpY, final double tpZ, final double speed) {
        final ArrayList<Vector3f> positions = new ArrayList<Vector3f>();
        final double posX = tpX - PlayerUtils.mc.thePlayer.posX;
        final double posY = tpY - (PlayerUtils.mc.thePlayer.posY + PlayerUtils.mc.thePlayer.getEyeHeight() + 1.1);
        final double posZ = tpZ - PlayerUtils.mc.thePlayer.posZ;
        final float yaw = (float)(Math.atan2(posZ, posX) * 180.0 / 3.141592653589793 - 90.0);
        final float pitch = (float)(-Math.atan2(posY, Math.sqrt(posX * posX + posZ * posZ)) * 180.0 / 3.141592653589793);
        double tmpX = PlayerUtils.mc.thePlayer.posX;
        double tmpY = PlayerUtils.mc.thePlayer.posY;
        double tmpZ = PlayerUtils.mc.thePlayer.posZ;
        double steps = 1.0;
        for (double d = speed; d < getDistance(PlayerUtils.mc.thePlayer.posX, PlayerUtils.mc.thePlayer.posY, PlayerUtils.mc.thePlayer.posZ, tpX, tpY, tpZ); d += speed) {
            ++steps;
        }
        for (double d = speed; d < getDistance(PlayerUtils.mc.thePlayer.posX, PlayerUtils.mc.thePlayer.posY, PlayerUtils.mc.thePlayer.posZ, tpX, tpY, tpZ); d += speed) {
            tmpX = PlayerUtils.mc.thePlayer.posX - Math.sin(getDirection(yaw)) * d;
            tmpZ = PlayerUtils.mc.thePlayer.posZ + Math.cos(getDirection(yaw)) * d;
            tmpY -= (PlayerUtils.mc.thePlayer.posY - tpY) / steps;
            positions.add(new Vector3f((float)tmpX, (float)tmpY, (float)tmpZ));
        }
        positions.add(new Vector3f((float)tpX, (float)tpY, (float)tpZ));
        return positions;
    }
    
    public static float getDirection(float yaw) {
        if (PlayerUtils.mc.thePlayer.moveForward < 0.0f) {
            yaw += 180.0f;
        }
        float forward = 1.0f;
        if (PlayerUtils.mc.thePlayer.moveForward < 0.0f) {
            forward = -0.5f;
        }
        else if (PlayerUtils.mc.thePlayer.moveForward > 0.0f) {
            forward = 0.5f;
        }
        if (PlayerUtils.mc.thePlayer.moveStrafing > 0.0f) {
            yaw -= 90.0f * forward;
        }
        if (PlayerUtils.mc.thePlayer.moveStrafing < 0.0f) {
            yaw += 90.0f * forward;
        }
        yaw *= 0.017453292f;
        return yaw;
    }
    
    public static double getDistance(final double x1, final double y1, final double z1, final double x2, final double y2, final double z2) {
        final double d0 = x1 - x2;
        final double d2 = y1 - y2;
        final double d3 = z1 - z2;
        return MathHelper.sqrt_double(d0 * d0 + d2 * d2 + d3 * d3);
    }
    
    public static boolean MovementInput() {
        return PlayerUtils.mc.gameSettings.keyBindForward.isPressed() || PlayerUtils.mc.gameSettings.keyBindLeft.isPressed() || PlayerUtils.mc.gameSettings.keyBindRight.isPressed() || PlayerUtils.mc.gameSettings.keyBindBack.isPressed();
    }
    
    public static void blockHit(final Entity en, final boolean value) {
        final ItemStack stack = PlayerUtils.mc.thePlayer.getCurrentEquippedItem();
        if (PlayerUtils.mc.thePlayer.getCurrentEquippedItem() != null && en != null && value && stack.getItem() instanceof ItemSword && PlayerUtils.mc.thePlayer.swingProgress > 0.2) {
            PlayerUtils.mc.thePlayer.getCurrentEquippedItem().useItemRightClick((World)PlayerUtils.mc.theWorld, (EntityPlayer)PlayerUtils.mc.thePlayer);
        }
    }
    
    public static float getItemAtkDamage(final ItemStack itemStack) {
        final Multimap multimap = itemStack.getAttributeModifiers();
        if (!multimap.isEmpty()) {
            final Iterator iterator = multimap.entries().iterator();
            if (iterator.hasNext()) {
                final Map.Entry entry = (Map.Entry) iterator.next();
                final AttributeModifier attributeModifier = (AttributeModifier) entry.getValue();
                final double damage = (attributeModifier.getOperation() != 1 && attributeModifier.getOperation() != 2) ? attributeModifier.getAmount() : (attributeModifier.getAmount() * 100.0);
                if (attributeModifier.getAmount() > 1.0) {
                    return 1.0f + (float)damage;
                }
                return 1.0f;
            }
        }
        return 1.0f;
    }
    
    public static int bestWeapon(final Entity target) {
        final InventoryPlayer inventory = PlayerUtils.mc.thePlayer.inventory;
        final int currentItem = 0;
        inventory.currentItem = currentItem;
        final int firstSlot = currentItem;
        int bestWeapon = -1;
        int j = 1;
        for (byte i = 0; i < 9; ++i) {
            PlayerUtils.mc.thePlayer.inventory.currentItem = i;
            final ItemStack itemStack = PlayerUtils.mc.thePlayer.getHeldItem();
            if (itemStack != null) {
                int itemAtkDamage = (int)getItemAtkDamage(itemStack);
            }
        }
        return firstSlot;
    }
    
    public static void shiftClick(final Item i) {
        for (int i2 = 9; i2 < 37; ++i2) {
            final ItemStack itemstack = PlayerUtils.mc.thePlayer.inventoryContainer.getSlot(i2).getStack();
            if (itemstack != null && itemstack.getItem() == i) {
                PlayerUtils.mc.playerController.windowClick(0, i2, 0, 1, (EntityPlayer)PlayerUtils.mc.thePlayer);
                break;
            }
        }
    }
    
    public static boolean hotbarIsFull() {
        for (int i = 0; i <= 36; ++i) {
            final ItemStack itemstack = PlayerUtils.mc.thePlayer.inventory.getStackInSlot(i);
            if (itemstack == null) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isMoving() {
        return !PlayerUtils.mc.thePlayer.isCollidedHorizontally && !PlayerUtils.mc.thePlayer.isSneaking() && (PlayerUtils.mc.thePlayer.movementInput.moveForward != 0.0f || PlayerUtils.mc.thePlayer.movementInput.moveStrafe != 0.0f);
    }
    
    public static boolean isMoving2() {
        return PlayerUtils.mc.thePlayer.moveForward != 0.0f || PlayerUtils.mc.thePlayer.moveStrafing != 0.0f;
    }
    
    public static boolean isAirUnder(final Entity ent) {
        return PlayerUtils.mc.theWorld.getBlockState(new BlockPos(ent.posX, ent.posY - 1.0, ent.posZ)).getBlock() == Blocks.air;
    }
    
    public static void blinkToPos(final double[] startPos, final BlockPos endPos, final double slack, final double[] pOffset) {
        double curX = startPos[0];
        double curY = startPos[1];
        double curZ = startPos[2];
        final double endX = endPos.getX() + 0.5;
        final double endY = endPos.getY() + 1.0;
        final double endZ = endPos.getZ() + 0.5;
        double distance = Math.abs(curX - endX) + Math.abs(curY - endY) + Math.abs(curZ - endZ);
        int count = 0;
        while (distance > slack) {
            distance = Math.abs(curX - endX) + Math.abs(curY - endY) + Math.abs(curZ - endZ);
            if (count > 120) {
                break;
            }
            final boolean next = false;
            final double diffX = curX - endX;
            final double diffY = curY - endY;
            final double diffZ = curZ - endZ;
            final double offset = ((count & 0x1) == 0x0) ? pOffset[0] : pOffset[1];
            if (diffX < 0.0) {
                if (Math.abs(diffX) > offset) {
                    curX += offset;
                }
                else {
                    curX += Math.abs(diffX);
                }
            }
            if (diffX > 0.0) {
                if (Math.abs(diffX) > offset) {
                    curX -= offset;
                }
                else {
                    curX -= Math.abs(diffX);
                }
            }
            if (diffY < 0.0) {
                if (Math.abs(diffY) > 0.25) {
                    curY += 0.25;
                }
                else {
                    curY += Math.abs(diffY);
                }
            }
            if (diffY > 0.0) {
                if (Math.abs(diffY) > 0.25) {
                    curY -= 0.25;
                }
                else {
                    curY -= Math.abs(diffY);
                }
            }
            if (diffZ < 0.0) {
                if (Math.abs(diffZ) > offset) {
                    curZ += offset;
                }
                else {
                    curZ += Math.abs(diffZ);
                }
            }
            if (diffZ > 0.0) {
                if (Math.abs(diffZ) > offset) {
                    curZ -= offset;
                }
                else {
                    curZ -= Math.abs(diffZ);
                }
            }
            PlayerUtils.mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C04PacketPlayerPosition(curX, curY, curZ, true));
            ++count;
        }
    }
    
    public static void disconnectServer(final String customMessage) {
        PlayerUtils.mc.thePlayer.sendQueue.getNetworkManager().closeChannel((IChatComponent)new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "[Flux] " + EnumChatFormatting.GRAY + customMessage));
    }
    
    static {
        PlayerUtils.mc = Minecraft.getMinecraft();
    }
}
