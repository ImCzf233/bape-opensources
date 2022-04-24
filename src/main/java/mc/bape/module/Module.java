// L Bape, Decompiled by ImCzf233

package mc.bape.module;

import net.minecraft.client.*;
import mc.bape.values.*;
import mc.bape.vapu.*;
import mc.bape.utils.notication.*;
import mc.bape.utils.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.*;
import org.lwjgl.input.*;
import mc.bape.manager.*;
import java.util.*;
import net.minecraftforge.client.event.*;

public class Module
{
    public static final Minecraft mc;
    public boolean state;
    public int key;
    public List<Value> values;
    public static String Chinese;
    public static String About;
    public static boolean NoToggle;
    public String name;
    public String status;
    public String Descript;
    public ModuleType category;
    public float optionAnim;
    public float optionAnimNow;
    public float hoverPercent;
    public float lastHoverPercent;
    
    public Module(final String name, final int key, final ModuleType category, final String Descript) {
        this.state = false;
        this.values = new ArrayList<Value>();
        this.status = "";
        this.optionAnim = 0.0f;
        this.optionAnimNow = 0.0f;
        this.name = name;
        this.key = key;
        this.category = category;
        this.Descript = Descript;
    }
    
    protected void addValues(final Value... values) {
        final Value[] var5 = values;
        for (int var6 = values.length, var7 = 0; var7 < var6; ++var7) {
            final Value value = var5[var7];
            this.values.add(value);
        }
    }
    
    public List<Value> getValues() {
        return this.values;
    }
    
    public void toggle() {
        if (Module.NoToggle && Client.MessageON) {
            if (this.state) {
                ClientUtil.sendClientMessage(this.name + " Disabled", Notification.Type.ERROR);
            }
            else {
                ClientUtil.sendClientMessage(this.name + " Enabled", Notification.Type.SUCCESS);
            }
        }
        this.setState(!this.state);
    }
    
    public void setState(final boolean state) {
        Module.mc.thePlayer.playSound("random.click", 0.3f, 0.5f);
        if (this.state == state) {
            return;
        }
        this.state = state;
        if (state) {
            MinecraftForge.EVENT_BUS.register((Object)this);
            FMLCommonHandler.instance().bus().register((Object)this);
            this.enable();
        }
        else {
            MinecraftForge.EVENT_BUS.unregister((Object)this);
            FMLCommonHandler.instance().bus().unregister((Object)this);
            this.disable();
        }
    }
    
    public void enable() {
    }
    
    public void disable() {
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDes() {
        return Module.About;
    }
    
    public String getChinese() {
        return Module.Chinese;
    }
    
    public int getKey() {
        return this.key;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public boolean getState() {
        return this.state;
    }
    
    public void setKey(final int key) {
        this.key = key;
        String content = "";
        for (final Module m : ModuleManager.getModules()) {
            content += String.format("%s:%s%s", m.getName(), Keyboard.getKeyName(m.getKey()), System.lineSeparator());
        }
        ConfigManager.save(Client.CLIENT_CONFIG + "-binds.cfg", content, false);
    }
    
    public ModuleType getCategory() {
        return this.category;
    }
    
    public void setCategory(final ModuleType category) {
        this.category = category;
    }
    
    public void onRenderWorldLast(final RenderWorldLastEvent event) {
    }
    
    public String getDescription() {
        return Module.About;
    }
    
    static {
        mc = Minecraft.getMinecraft();
        Module.NoToggle = false;
    }
}
