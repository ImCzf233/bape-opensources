package mc.bape.api.miliblue;

import net.minecraft.entity.*;

public class EventAttack extends Event
{
    public static Entity entity;
    private boolean preAttack;
    
    public void fire(final Entity targetEntity, final boolean preAttack) {
        EventAttack.entity = targetEntity;
        this.preAttack = preAttack;
        super.fire();
    }
    
    public Entity getEntity() {
        return EventAttack.entity;
    }
}
