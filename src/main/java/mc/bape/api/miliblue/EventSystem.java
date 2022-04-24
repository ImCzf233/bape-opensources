package mc.bape.api.miliblue;

import java.util.*;

public class EventSystem
{
    private static final HashMap<Event, EventSubscription> registry;
    private static final HashMap<Class, Event> instances;
    
    public static Event fire(final Event event) {
        final EventSubscription subscription = EventSystem.registry.get(event);
        if (subscription != null) {
            subscription.fire(event);
        }
        return event;
    }
    
    public static Event getInstance(final Class eventClass) {
        return EventSystem.instances.get(eventClass);
    }
    
    private static boolean isEventRegistered(final Event event) {
        return EventSystem.registry.containsKey(event);
    }
    
    static {
        registry = new HashMap<Event, EventSubscription>();
        (instances = new HashMap<Class, Event>()).put(EventUpdate.class, new EventUpdate());
    }
}
