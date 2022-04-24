// L Bape, Decompiled by ImCzf233

package mc.bape.api.miliblue;

import java.util.*;
import java.util.concurrent.*;
import net.minecraft.client.*;

public class EventSubscription<T extends Event>
{
    private final T event;
    private final List<EventListener> subscribed;
    
    public EventSubscription(final T event) {
        this.subscribed = new CopyOnWriteArrayList<EventListener>();
        this.event = event;
    }
    
    public void fire(final Event event) {
        if (Minecraft.getMinecraft().thePlayer == null) {
            return;
        }
        this.subscribed.forEach(listener -> listener.onEvent(event));
    }
    
    public void add(final EventListener listener) {
        this.subscribed.add(listener);
    }
    
    public void remove(final EventListener listener) {
        if (this.subscribed.contains(listener)) {
            this.subscribed.remove(listener);
        }
    }
    
    public List<EventListener> getSubscribed() {
        return this.subscribed;
    }
    
    public Event getEvent() {
        return this.event;
    }
}
