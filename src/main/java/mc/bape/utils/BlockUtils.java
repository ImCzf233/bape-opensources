// L Bape, Decompiled by ImCzf233

package mc.bape.utils;

import net.minecraft.client.*;
import net.minecraft.client.entity.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.entity.*;
import net.minecraft.block.state.*;
import java.util.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.util.*;
import net.minecraft.entity.item.*;

public class BlockUtils
{
    private static final Minecraft mc;
    private EntityOtherPlayerMP player;
    
    public static String getBlockName(final Block block) {
        if (block == Blocks.air) {
            return null;
        }
        final Item item = Item.getItemFromBlock(block);
        final ItemStack itemStack = (item != null) ? new ItemStack(Item.getByNameOrId(block.getUnlocalizedName()), 1, 0) : null;
        final String name = (itemStack == null) ? block.getLocalizedName() : item.getItemStackDisplayName(itemStack);
        return (name.length() > 5 && name.startsWith("tile.")) ? block.getUnlocalizedName() : name;
    }
    
    public static boolean isOnLiquid() {
        Minecraft.getMinecraft();
        final AxisAlignedBB par1AxisAlignedBB = BlockUtils.mc.thePlayer.getCollisionBoundingBox().offset(0.0, -0.01, 0.0).contract(0.001, 0.001, 0.001);
        final int var4 = MathHelper.floor_double(par1AxisAlignedBB.minX);
        final int var5 = MathHelper.floor_double(par1AxisAlignedBB.maxX + 1.0);
        final int var6 = MathHelper.floor_double(par1AxisAlignedBB.minY);
        final int var7 = MathHelper.floor_double(par1AxisAlignedBB.maxY + 1.0);
        final int var8 = MathHelper.floor_double(par1AxisAlignedBB.minZ);
        final int var9 = MathHelper.floor_double(par1AxisAlignedBB.maxZ + 1.0);
        for (int var10 = var4; var10 < var5; ++var10) {
            for (int var11 = var6; var11 < var7; ++var11) {
                for (int var12 = var8; var12 < var9; ++var12) {
                    final BlockPos pos = new BlockPos(var10, var11, var12);
                    Minecraft.getMinecraft();
                    final Block var13 = BlockUtils.mc.theWorld.getBlockState(pos).getBlock();
                    if (!(var13 instanceof BlockAir) && !(var13 instanceof BlockLiquid)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public static Block getBlock(final double x, final double y, final double z) {
        Minecraft.getMinecraft();
        return BlockUtils.mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock();
    }
    
    public static Block getBlock(final BlockPos block) {
        Minecraft.getMinecraft();
        return BlockUtils.mc.theWorld.getBlockState(block).getBlock();
    }
    
    public static Block getBlock(final Entity entity, final double offsetY) {
        if (entity == null) {
            return null;
        }
        final int y = (int)entity.getEntityBoundingBox().offset(0.0, offsetY, 0.0).minY;
        for (int x = MathHelper.floor_double(entity.getEntityBoundingBox().minX); x < MathHelper.floor_double(entity.getEntityBoundingBox().maxX) + 1; ++x) {
            final int z = MathHelper.floor_double(entity.getEntityBoundingBox().minZ);
            if (z < MathHelper.floor_double(entity.getEntityBoundingBox().maxZ) + 1) {
                return BlockUtils.mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock();
            }
        }
        return null;
    }
    
    public static boolean isInLiquid() {
        Minecraft.getMinecraft();
        final AxisAlignedBB par1AxisAlignedBB = BlockUtils.mc.thePlayer.getCollisionBoundingBox().contract(0.001, 0.001, 0.001);
        final int var4 = MathHelper.floor_double(par1AxisAlignedBB.minX);
        final int var5 = MathHelper.floor_double(par1AxisAlignedBB.maxX + 1.0);
        final int var6 = MathHelper.floor_double(par1AxisAlignedBB.minY);
        final int var7 = MathHelper.floor_double(par1AxisAlignedBB.maxY + 1.0);
        final int var8 = MathHelper.floor_double(par1AxisAlignedBB.minZ);
        final int var9 = MathHelper.floor_double(par1AxisAlignedBB.maxZ + 1.0);
        for (int var10 = var4; var10 < var5; ++var10) {
            for (int var11 = var6; var11 < var7; ++var11) {
                for (int var12 = var8; var12 < var9; ++var12) {
                    final BlockPos pos = new BlockPos(var10, var11, var12);
                    Minecraft.getMinecraft();
                    final Block var13 = BlockUtils.mc.theWorld.getBlockState(pos).getBlock();
                    if (var13 instanceof BlockLiquid) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static LinkedList<BlockPos> findBlocksNearEntity(final EntityLivingBase entity, final int blockId, final int blockMeta, final int distance) {
        final LinkedList<BlockPos> blocks = new LinkedList<BlockPos>();
        for (int x = (int)BlockUtils.mc.thePlayer.posX - distance; x <= (int)BlockUtils.mc.thePlayer.posX + distance; ++x) {
            for (int z = (int)BlockUtils.mc.thePlayer.posZ - distance; z <= (int)BlockUtils.mc.thePlayer.posZ + distance; ++z) {
                for (int height = BlockUtils.mc.theWorld.getHeight(), y = 0; y <= height; ++y) {
                    final BlockPos blockPos = new BlockPos(x, y, z);
                    final IBlockState blockState = BlockUtils.mc.theWorld.getBlockState(blockPos);
                    if (blockId == -1 || blockMeta == -1) {
                        blocks.add(blockPos);
                    }
                    else {
                        final int id = Block.getIdFromBlock(blockState.getBlock());
                        final int meta = blockState.getBlock().getMetaFromState(blockState);
                        if (id == blockId) {
                            if (meta == blockMeta) {
                                blocks.add(blockPos);
                            }
                        }
                    }
                }
            }
        }
        return blocks;
    }
    
    public static boolean CanStep() {
        Minecraft.getMinecraft();
        final AxisAlignedBB par1AxisAlignedBB = BlockUtils.mc.thePlayer.getCollisionBoundingBox().contract(0.0, 0.001, 0.0);
        final int var6 = MathHelper.floor_double(par1AxisAlignedBB.minY);
        for (int var7 = MathHelper.floor_double(par1AxisAlignedBB.maxY + 1.0), y = var6; y < var7; ++y) {
            final BlockPos pos = new BlockPos(BlockUtils.mc.thePlayer.posX, (double)y, BlockUtils.mc.thePlayer.posZ);
            Minecraft.getMinecraft();
            final Block var8 = BlockUtils.mc.theWorld.getBlockState(pos).getBlock();
            if (var8.isFullBlock()) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isOnLadder() {
        boolean onLadder = false;
        final int y = (int)BlockUtils.mc.thePlayer.getEntityBoundingBox().offset(0.0, 1.0, 0.0).minY;
        for (int x = MathHelper.floor_double(BlockUtils.mc.thePlayer.getEntityBoundingBox().minX); x < MathHelper.floor_double(BlockUtils.mc.thePlayer.getEntityBoundingBox().maxX) + 1; ++x) {
            for (int z = MathHelper.floor_double(BlockUtils.mc.thePlayer.getEntityBoundingBox().minZ); z < MathHelper.floor_double(BlockUtils.mc.thePlayer.getEntityBoundingBox().maxZ) + 1; ++z) {
                final Block block = BlockUtils.mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock();
                if (Objects.nonNull(block) && !(block instanceof BlockAir)) {
                    if (!(block instanceof BlockLadder) && !(block instanceof BlockVine)) {
                        return false;
                    }
                    onLadder = true;
                }
            }
        }
        return onLadder || BlockUtils.mc.thePlayer.isOnLadder();
    }
    
    public static boolean isInsideBlock() {
        Minecraft.getMinecraft();
        int x = MathHelper.floor_double(BlockUtils.mc.thePlayer.getCollisionBoundingBox().minX);
        while (true) {
            Minecraft.getMinecraft();
            if (x >= MathHelper.floor_double(BlockUtils.mc.thePlayer.getCollisionBoundingBox().maxX) + 1) {
                return false;
            }
            Minecraft.getMinecraft();
            int y = MathHelper.floor_double(BlockUtils.mc.thePlayer.getCollisionBoundingBox().minY);
            while (true) {
                Minecraft.getMinecraft();
                if (y >= MathHelper.floor_double(BlockUtils.mc.thePlayer.getCollisionBoundingBox().maxY) + 1) {
                    ++x;
                    break;
                }
                Minecraft.getMinecraft();
                int z = MathHelper.floor_double(BlockUtils.mc.thePlayer.getCollisionBoundingBox().minZ);
                while (true) {
                    Minecraft.getMinecraft();
                    if (z >= MathHelper.floor_double(BlockUtils.mc.thePlayer.getCollisionBoundingBox().maxZ) + 1) {
                        ++y;
                        break;
                    }
                    Minecraft.getMinecraft();
                    final Block block = BlockUtils.mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock();
                    if (block != null && !(block instanceof BlockAir)) {
                        Minecraft.getMinecraft();
                        final WorldClient var10001 = BlockUtils.mc.theWorld;
                        final BlockPos var10002 = new BlockPos(x, y, z);
                        Minecraft.getMinecraft();
                        final AxisAlignedBB boundingBox;
                        if ((boundingBox = block.getCollisionBoundingBox((World)var10001, var10002, BlockUtils.mc.theWorld.getBlockState(new BlockPos(x, y, z)))) != null) {
                            Minecraft.getMinecraft();
                            if (BlockUtils.mc.thePlayer.getCollisionBoundingBox().intersectsWith(boundingBox)) {
                                return true;
                            }
                        }
                    }
                    ++z;
                }
            }
        }
    }
    
    public static float[] getBlockRotations(final double x, final double y, final double z) {
        final double var4 = x - BlockUtils.mc.thePlayer.posX + 0.5;
        final double var5 = z - BlockUtils.mc.thePlayer.posZ + 0.5;
        final double var6 = y - (BlockUtils.mc.thePlayer.posY + BlockUtils.mc.thePlayer.getEyeHeight() - 1.0);
        final double var7 = MathHelper.sqrt_double(var4 * var4 + var5 * var5);
        final float var8 = (float)(Math.atan2(var5, var4) * 180.0 / 3.141592653589793) - 90.0f;
        return new float[] { var8, (float)(-Math.atan2(var6, var7) * 180.0 / 3.141592653589793) };
    }
    
    public static float[] getFacingRotations(final BlockPos pos, final EnumFacing facing) {
        final EntityXPOrb temp = new EntityXPOrb((World)BlockUtils.mc.theWorld);
        temp.posX = pos.getX() + 0.5;
        temp.posY = pos.getY() + 0.5;
        temp.posZ = pos.getZ() + 0.5;
        final EntityXPOrb entityXPOrb = temp;
        entityXPOrb.posX += facing.getDirectionVec().getX() * 0.25;
        final EntityXPOrb entityXPOrb2 = temp;
        entityXPOrb2.posZ += facing.getDirectionVec().getZ() * 0.25;
        final EntityXPOrb entityXPOrb3 = temp;
        entityXPOrb3.posY += 0.5;
        return getRotationsNeeded((Entity)temp);
    }
    
    private static float[] getRotationsNeeded(final Entity entity) {
        final double posX = entity.posX - BlockUtils.mc.thePlayer.posX;
        final double posY = entity.posY - (BlockUtils.mc.thePlayer.posY + BlockUtils.mc.thePlayer.getEyeHeight());
        final double posZ = entity.posZ - BlockUtils.mc.thePlayer.posZ;
        final double var14 = MathHelper.sqrt_double(posX * posX + posZ * posZ);
        final float yaw = (float)(Math.atan2(posZ, posX) * 180.0 / 3.141592653589793) - 90.0f;
        final float pitch = (float)(-(Math.atan2(posY, var14) * 180.0 / 3.141592653589793));
        return new float[] { yaw, pitch };
    }
    
    public static void updateTool(final BlockPos pos) {
        final Block block = BlockUtils.mc.theWorld.getBlockState(pos).getBlock();
        float strength = 1.0f;
        int bestItemIndex = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack itemStack = BlockUtils.mc.thePlayer.inventory.mainInventory[i];
            if (itemStack != null) {
                if (itemStack.getStrVsBlock(block) > strength) {
                    strength = itemStack.getStrVsBlock(block);
                    bestItemIndex = i;
                }
            }
        }
        if (bestItemIndex != -1) {
            BlockUtils.mc.thePlayer.inventory.currentItem = bestItemIndex;
        }
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
}
