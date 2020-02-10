package growthcraft.core.shared.tileentity.event;

import growthcraft.core.shared.io.stream.IStreamable;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TileEventFunction {
    private Method method;

    public TileEventFunction(@Nonnull Method m) {
        this.method = m;
    }

    private Object invoke2(Object a, Object b) {
        try {
            return this.method.invoke(a, b);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(e);
        } catch (InvocationTargetException e) {
            throw new IllegalStateException(e);
        }
    }

    public void readFromNBT(Object tile, NBTTagCompound nbt) {
        invoke2(tile, nbt);
    }

    public void writeToNBT(Object tile, NBTTagCompound nbt) {
        invoke2(tile, nbt);
    }

    public boolean writeToStream(IStreamable tile, ByteBuf data) {
        return (Boolean) invoke2(tile, data);
    }

    public boolean readFromStream(IStreamable tile, ByteBuf data) {
        return (Boolean) invoke2(tile, data);
    }
}
