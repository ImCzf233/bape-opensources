// L Bape, Decompiled by ImCzf233

package mc.bape.module.render;

import net.minecraft.client.*;
import mc.bape.module.*;
import mc.bape.values.*;
import net.minecraftforge.client.event.*;
import mc.bape.utils.*;
import net.minecraft.tileentity.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class StorageESP extends Module
{
    private Option<Boolean> Chest;
    private Option<Boolean> EnderChest;
    private Option<Boolean> iron;
    private Option<Boolean> gold;
    private Option<Boolean> diamond;
    private Option<Boolean> emerald;
    private Option<Boolean> coal;
    private static Minecraft mc;
    
    public StorageESP() {
        super("StorageESP", 0, ModuleType.Render, "Chest and Ore Renderer ESP");
        this.Chest = new Option<Boolean>("Chest", "Chest", true);
        this.EnderChest = new Option<Boolean>("EnderChest", "EnderChest", false);
        this.iron = new Option<Boolean>("Iron", "Iron", false);
        this.gold = new Option<Boolean>("Gold", "Gold", false);
        this.diamond = new Option<Boolean>("Diamond", "Diamond", false);
        this.emerald = new Option<Boolean>("Emerald", "Emerald", false);
        this.coal = new Option<Boolean>("Coal", "Coal", false);
        this.addValues(this.Chest, this.EnderChest, this.iron, this.gold, this.diamond, this.emerald, this.coal);
        StorageESP.Chinese = "ExampleModule";
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent ev) {
        for (final TileEntity te : StorageESP.mc.theWorld.loadedTileEntityList) {
            if (te instanceof TileEntityChest && this.Chest.getValue()) {
                int rgb = 0;
                rgb = ColorUtil.getRainbow().getRGB();
                RenderHelper.drawBlockESP(te.getPos(), rgb);
            }
            if (te instanceof TileEntityEnderChest && this.EnderChest.getValue()) {
                int rgb = 0;
                rgb = ColorUtil.getRainbow().getRGB();
                RenderHelper.drawBlockESP(te.getPos(), rgb);
            }
        }
    }
    
    static {
        StorageESP.mc = Minecraft.getMinecraft();
    }
}
