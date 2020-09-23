package li.cil.oc.api.prefab;

import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.driver.DriverBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.world.World;

/**
 * If you wish to create a block component for a third-party block, i.e. a block
 * for which you do not control the tile entity, such as vanilla blocks, you
 * will need a block driver.
 * <p/>
 * This prefab allows creating a driver that works for a specified list of item
 * stacks (to support different blocks with the same id but different metadata
 * values).
 * <p/>
 * You still have to provide the implementation for creating its environment, if
 * any.
 * <p/>
 * To limit sidedness, I recommend overriding {@link #worksWith(World, BlockPos, Direction)}
 * and calling <code>super.worksWith</code> in addition to the side check.
 *
 * @see ManagedEnvironment
 */
@SuppressWarnings("UnusedDeclaration")
public abstract class DriverSidedBlock implements DriverBlock {
    protected final ItemStack[] blocks;

    protected DriverSidedBlock(final ItemStack... blocks) {
        this.blocks = blocks.clone();
    }

    @Override
    public boolean worksWith(final World world, final BlockPos pos, final Direction side) {
        final BlockState state = world.getBlockState(pos);
        final Block block = state.getBlock();

        // state.getBlockState()
        return worksWith(block, 0);
    }

    protected boolean worksWith(final Block referenceBlock, final int referenceMetadata) {
        for (ItemStack stack : blocks) {
            if (!stack.isEmpty() && stack.getItem() instanceof BlockItem) {
                final BlockItem item = (BlockItem) stack.getItem();
                final Block supportedBlock = item.getBlock();
                // final int supportedMetadata = item.getMetadata();

                // (referenceMetadata == supportedMetadata || stack.getDamage() == OreDictionary.WILDCARD_VALUE)
                if (referenceBlock == supportedBlock && true) {
                    return true;
                }
            }
        }
        return false;
    }
}
