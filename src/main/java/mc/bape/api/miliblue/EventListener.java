package mc.bape.api.miliblue;

public interface EventListener<E extends Event>
{
    void onEvent(final E p0);
}
