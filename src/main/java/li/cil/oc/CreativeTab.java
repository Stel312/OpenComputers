package li.cil.oc;

import li.cil.oc.api.Items;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CreativeTab extends ItemGroup {
    private static final ItemStack _iconStack = Items.get(Constants.BlockName.CaseTier1).createItemStack(1);

    public CreativeTab() {
        super("OpenComputers");
    }

    @Override
    public ItemStack createIcon() {
        return _iconStack;
    }
}
