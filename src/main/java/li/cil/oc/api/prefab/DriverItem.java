package li.cil.oc.api.prefab;

import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.internal.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

/**
 * If you wish to create item components such as the network card or hard drives
 * you will need an item driver.
 * <p/>
 * This prefab allows creating a driver that works for a specified list of item
 * stacks (to support different items with the same id but different damage
 * values). It also takes care of creating and getting the tag compound on an
 * item stack to save data to or load data from.
 * <p/>
 * You still have to specify your component's slot type and provide the
 * implementation for creating its environment, if any.
 *
 * @see ManagedEnvironment
 */
@SuppressWarnings("UnusedDeclaration")
public abstract class DriverItem implements li.cil.oc.api.driver.DriverItem {
    protected final ItemStack[] items;

    protected DriverItem(final ItemStack... items) {
        this.items = items.clone();
    }

    @Override
    public boolean worksWith(final ItemStack stack) {
        if (!stack.isEmpty()) {
            for (ItemStack item : items) {
                if (!item.isEmpty() && item.isItemEqual(stack)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int tier(final ItemStack stack) {
        return 0;
    }

    @Override
    public CompoundNBT dataTag(final ItemStack stack) {
        if (!stack.hasTag()) {
            stack.setTag(new CompoundNBT());
        }
        final CompoundNBT nbt = stack.getTag();
        // This is the suggested key under which to store item component data.
        // You are free to change this as you please.
        if (!nbt.contains("oc:data")) {
            nbt.put("oc:data", new CompoundNBT());
        }
        return nbt.getCompound("oc:data");
    }

    // Convenience methods provided for HostAware drivers.

    protected boolean isAdapter(Class<? extends EnvironmentHost> host) {
        return Adapter.class.isAssignableFrom(host);
    }

    protected boolean isComputer(Class<? extends EnvironmentHost> host) {
        return Case.class.isAssignableFrom(host);
    }

    protected boolean isRobot(Class<? extends EnvironmentHost> host) {
        return Robot.class.isAssignableFrom(host);
    }

    protected boolean isRotatable(Class<? extends EnvironmentHost> host) {
        return Rotatable.class.isAssignableFrom(host);
    }

    protected boolean isServer(Class<? extends EnvironmentHost> host) {
        return Server.class.isAssignableFrom(host);
    }

    protected boolean isTablet(Class<? extends EnvironmentHost> host) {
        return Tablet.class.isAssignableFrom(host);
    }
}
