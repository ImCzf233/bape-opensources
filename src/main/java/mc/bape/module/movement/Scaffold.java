// L Bape, Decompiled by ImCzf233

package mc.bape.module.movement;

import mc.bape.module.*;
import mc.bape.utils.Helper;
import mc.bape.values.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.block.*;
import net.minecraft.client.settings.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.*;
import net.minecraftforge.fml.relauncher.*;
import org.lwjgl.input.*;
import net.minecraft.world.*;
import mc.bape.module.movement.ScaffoldUtils.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.client.entity.*;
import net.minecraft.item.*;
import net.minecraft.block.state.*;
import net.minecraft.util.*;
import java.util.*;

public class Scaffold extends Module
{
    ArrayList<String> sites;
    public int godBridgeTimer;
    private Mode<Enum> mode;
    
    public Scaffold() {
        super("Scaffold", 0, ModuleType.Movement, "Make you bridge faster");
        this.sites = new ArrayList<String>();
        this.mode = new Mode<Enum>("Mode", "mode", ScaffoldMode.values(), ScaffoldMode.Legit);
        this.addValues(this.mode);
        Scaffold.Chinese = "ExampleModule";
    }
    
    public Block getBlock(final BlockPos pos) {
        return Scaffold.mc.theWorld.getBlockState(pos).getBlock();
    }
    
    public Block getBlockUnderPlayer(final EntityPlayer player) {
        return this.getBlock(new BlockPos(player.posX, player.posY - 1.0, player.posZ));
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.PlayerTickEvent event) {
        if (this.mode.getValue() == ScaffoldMode.Legit) {
            if (this.getBlockUnderPlayer((EntityPlayer)Scaffold.mc.thePlayer) instanceof BlockAir) {
                if (Scaffold.mc.thePlayer.onGround) {
                    KeyBinding.setKeyBindState(Scaffold.mc.gameSettings.keyBindSneak.getKeyCode(), true);
                }
            }
            else if (Scaffold.mc.thePlayer.onGround) {
                KeyBinding.setKeyBindState(Scaffold.mc.gameSettings.keyBindSneak.getKeyCode(), false);
            }
        }
        else if (this.mode.getValue() == ScaffoldMode.Assist) {
            Helper.sendMessageWithoutPrefix("Stop cheating loser");
        }
    }
    
    @Override
    public void enable() {
        Scaffold.mc.thePlayer.setSneaking(false);
        super.enable();
    }
    
    @Override
    public void disable() {
        KeyBinding.setKeyBindState(Scaffold.mc.gameSettings.keyBindSneak.getKeyCode(), false);
        super.disable();
    }
    
    enum ScaffoldMode
    {
        Legit, 
        Assist;
    }
}
