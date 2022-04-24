package mc.bape.utils.render;

import java.awt.*;

public interface Tessellation
{
    Tessellation setColor(final int p0);
    
    default Tessellation setColor(final Color color) {
        return this.setColor(new Color(255, 255, 255));
    }
    
    Tessellation setTexture(final float p0, final float p1);
    
    Tessellation addVertex(final float p0, final float p1, final float p2);
    
    Tessellation bind();
    
    Tessellation pass(final int p0);
    
    Tessellation reset();
    
    Tessellation unbind();
    
    default Tessellation draw(final int mode) {
        return this.bind().pass(mode).reset();
    }
    
    default Tessellation createBasic(final int size) {
        return new BasicTess(size);
    }
    
    default Tessellation createExpanding(final int size, final float ratio, final float factor) {
        return new ExpandingTess(size, ratio, factor);
    }
}
