package ckathode.weaponmod.item;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHalberd extends ItemMelee {
    @SideOnly(Side.CLIENT)
    public ModelResourceLocation	modelLocationStabbing;
    private String					id;

    public ItemHalberd(String id, MeleeComponent meleecomponent) {
        super(id, meleecomponent);
        this.id = id;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining)
    {
        if (meleeComponent.shouldRotateAroundWhenRendering())
        {
            if (modelLocationStabbing == null)
            {
                modelLocationStabbing = new ModelResourceLocation("weaponmod:" + id + "-stabbing", "inventory");
            }

            return modelLocationStabbing;
        }
        return super.getModel(stack, player, useRemaining);
    }
}
