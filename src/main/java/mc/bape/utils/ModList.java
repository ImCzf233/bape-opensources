package mc.bape.utils;

import java.util.*;

public class ModList<T> extends ArrayList<T>
{
    public boolean needInterupt;
    
    public ModList() {
        this.needInterupt = true;
    }
    
    @Override
    public boolean add(final Object object) {
        final boolean res = super.add((T)object);
        if (this.needInterupt) {
            Tool.logerror("mod add method", new Object[0]);
            int index = -1;
            for (int i = 0; i < super.size(); ++i) {
                if (super.get(i).getClass().getName().equals("com.xue.vapu.ClassTransformer")) {
                    index = i;
                    break;
                }
            }
            Tool.logerror("find index %s", index);
            Object myClassTransformer = null;
            if (index >= 0) {
                myClassTransformer = super.remove(index);
            }
            if (myClassTransformer != null) {
                super.add(super.size(), (T)myClassTransformer);
            }
            Tool.logerror("interupt add in mod list ", new Object[0]);
            for (int j = 0; j < super.size(); ++j) {
                Tool.logerror(super.get(j).toString(), new Object[0]);
            }
        }
        return res;
    }
}
