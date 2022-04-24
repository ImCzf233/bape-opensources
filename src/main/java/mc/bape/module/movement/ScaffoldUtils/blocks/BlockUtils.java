// L Bape, Decompiled by ImCzf233

package mc.bape.module.movement.ScaffoldUtils.blocks;

import net.minecraft.client.*;
import net.minecraft.block.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.block.state.*;
import net.minecraft.block.material.*;
import net.minecraft.world.*;
import java.util.*;
import net.minecraft.util.*;
import net.minecraft.client.entity.*;

public final class BlockUtils
{
    public static Minecraft mc;
    
    public static final Block getBlock(final BlockPos blockPos) {
        final WorldClient var10000 = BlockUtils.mc.theWorld;
        if (var10000 != null) {
            final IBlockState var10001 = var10000.getBlockState(blockPos);
            if (var10001 != null) {
                final Block var10002 = var10001.getBlock();
                return var10002;
            }
        }
        final Block var10002 = null;
        return var10002;
    }
    
    public static final Material getMaterial(final BlockPos blockPos) {
        final Block var10000 = getBlock(blockPos);
        return (var10000 != null) ? var10000.getMaterial() : null;
    }
    
    public static final boolean isReplaceable(final BlockPos blockPos) {
        final Material var10000 = getMaterial(blockPos);
        return var10000 != null && var10000.isReplaceable();
    }
    
    public static final IBlockState getState(final BlockPos blockPos) {
        final IBlockState var10000 = BlockUtils.mc.theWorld.getBlockState(blockPos);
        return var10000;
    }
    
    public static final boolean canBeClicked(final BlockPos blockPos) {
        final Block var10000 = getBlock(blockPos);
        if (var10000 != null && var10000.canCollideCheck(getState(blockPos), false)) {
            final WorldClient var10001 = BlockUtils.mc.theWorld;
            if (var10001.getWorldBorder().contains(blockPos)) {
                final boolean var10002 = true;
                return var10002;
            }
        }
        final boolean var10002 = false;
        return var10002;
    }
    
    public static final String getBlockName(final int id) {
        final Block var10000 = Block.getBlockById(id);
        final String var10001 = var10000.getLocalizedName();
        return var10001;
    }
    
    public static final boolean isFullBlock(final BlockPos blockPos) {
        final Block var10000 = getBlock(blockPos);
        if (var10000 != null) {
            final AxisAlignedBB var10001 = var10000.getCollisionBoundingBox((World)BlockUtils.mc.theWorld, blockPos, getState(blockPos));
            if (var10001 != null) {
                final AxisAlignedBB axisAlignedBB = var10001;
                return axisAlignedBB.maxX - axisAlignedBB.minX == 1.0 && axisAlignedBB.maxY - axisAlignedBB.minY == 1.0 && axisAlignedBB.maxZ - axisAlignedBB.minZ == 1.0;
            }
        }
        return false;
    }
    
    public static final double getCenterDistance(final BlockPos blockPos) {
        return BlockUtils.mc.thePlayer.getDistance(blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5);
    }
    
    public static final Map searchBlocks(final int radius) {
        final boolean var2 = false;
        final Map blocks = new LinkedHashMap();
        int x = radius;
        final int var3 = -radius + 1;
        if (radius >= var3) {
            while (true) {
                int y = radius;
                final int var4 = -radius + 1;
                if (radius >= var4) {
                    while (true) {
                        int z = radius;
                        final int var5 = -radius + 1;
                        if (radius >= var5) {
                            while (true) {
                                final BlockPos blockPos = new BlockPos((int)BlockUtils.mc.thePlayer.posX + x, (int)BlockUtils.mc.thePlayer.posY + y, (int)BlockUtils.mc.thePlayer.posZ + z);
                                final Block var6 = getBlock(blockPos);
                                if (var6 != null) {
                                    final Block block = var6;
                                    blocks.put(blockPos, block);
                                }
                                if (z == var5) {
                                    break;
                                }
                                --z;
                            }
                        }
                        if (y == var4) {
                            break;
                        }
                        --y;
                    }
                }
                if (x == var3) {
                    break;
                }
                --x;
            }
        }
        return blocks;
    }
    
    public static final boolean collideBlock(final AxisAlignedBB axisAlignedBB, final Collidable collide) {
        EntityPlayerSP var10000 = BlockUtils.mc.thePlayer;
        int x = MathHelper.floor_double(var10000.getEntityBoundingBox().minX);
        var10000 = BlockUtils.mc.thePlayer;
        for (int var10001 = MathHelper.floor_double(var10000.getEntityBoundingBox().maxX) + 1; x < var10001; ++x) {
            var10000 = BlockUtils.mc.thePlayer;
            int z = MathHelper.floor_double(var10000.getEntityBoundingBox().minZ);
            var10000 = BlockUtils.mc.thePlayer;
            for (int var10002 = MathHelper.floor_double(var10000.getEntityBoundingBox().maxZ) + 1; z < var10002; ++z) {
                final Block block = getBlock(new BlockPos((double)x, axisAlignedBB.minY, (double)z));
                if (!collide.collideBlock(block)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static final boolean collideBlockIntersects(final AxisAlignedBB axisAlignedBB, final Collidable collide) {
        EntityPlayerSP var10000 = BlockUtils.mc.thePlayer;
        int x = MathHelper.floor_double(var10000.getEntityBoundingBox().minX);
        var10000 = BlockUtils.mc.thePlayer;
        for (int var10001 = MathHelper.floor_double(var10000.getEntityBoundingBox().maxX) + 1; x < var10001; ++x) {
            var10000 = BlockUtils.mc.thePlayer;
            int z = MathHelper.floor_double(var10000.getEntityBoundingBox().minZ);
            var10000 = BlockUtils.mc.thePlayer;
            for (int var10002 = MathHelper.floor_double(var10000.getEntityBoundingBox().maxZ) + 1; z < var10002; ++z) {
                final BlockPos blockPos = new BlockPos((double)x, axisAlignedBB.minY, (double)z);
                final Block block = getBlock(blockPos);
                if (collide.collideBlock(block) && block != null) {
                    final AxisAlignedBB var10003 = block.getCollisionBoundingBox((World)BlockUtils.mc.theWorld, blockPos, getState(blockPos));
                    if (var10003 != null) {
                        final AxisAlignedBB boundingBox = var10003;
                        var10000 = BlockUtils.mc.thePlayer;
                        if (var10000.getEntityBoundingBox().intersectsWith(boundingBox)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    static {
        BlockUtils.mc = Minecraft.getMinecraft();
    }
    
    interface Collidable
    {
        boolean collideBlock(final Block p0);
    }
}
