/*
 * Decompiled with CFR 0_132.
 */
package mc.bape.values;

public abstract class Value<V> {
    private String displayName;
    private String name;
    private V value;
    public float optionAnim = 0;//present
    public float optionAnimNow = 0;//present
    public float animX1;
    public float animX;

    public Value(String displayName, String name) {
        this.displayName = displayName;
        this.name = name;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getName() {
        return this.name;
    }

    public V getValue() {
        return this.value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}

