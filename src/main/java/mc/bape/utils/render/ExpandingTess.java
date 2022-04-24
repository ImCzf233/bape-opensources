package mc.bape.utils.render;

import java.nio.*;

public class ExpandingTess extends BasicTess
{
    private final float ratio;
    private final float factor;
    
    ExpandingTess(final int initial, final float ratio, final float factor) {
        super(initial);
        this.ratio = ratio;
        this.factor = factor;
    }
    
    @Override
    public Tessellation addVertex(final float x, final float y, final float z) {
        int capacity = this.raw.length;
        if (this.index * 6 >= capacity * this.ratio) {
            capacity *= (int)this.factor;
            final int[] newBuffer = new int[capacity];
            System.arraycopy(this.raw, 0, newBuffer, 0, this.raw.length);
            this.raw = newBuffer;
            this.buffer = ByteBuffer.allocateDirect(capacity * 4).order(ByteOrder.nativeOrder());
            this.iBuffer = this.buffer.asIntBuffer();
            this.fBuffer = this.buffer.asFloatBuffer();
        }
        return super.addVertex(x, y, z);
    }
}
