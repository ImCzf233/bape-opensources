package mc.bape.manager;

import mc.bape.legit.hud.mod.*;
import mc.bape.legit.hud.mod.impl.*;
import java.util.*;

public class HudManager
{
    public ArrayList<HudMod> hudMods;
    
    public HudManager() {
        (this.hudMods = new ArrayList<HudMod>()).add(new ModsArrayList());
        this.hudMods.add(new InventoryHUD());
    }
    
    public void renderMods() {
        for (final HudMod m : this.hudMods) {
            m.draw();
        }
    }
}
