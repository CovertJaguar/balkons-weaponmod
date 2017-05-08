package ckathode.weaponmod.item;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCrossbow extends ItemShooter
{
	@SideOnly(Side.CLIENT)
	public ModelResourceLocation	modelLocationLoaded = new ModelResourceLocation("weaponmod:crossbow-loaded", "inventory");

	public ItemCrossbow(String id, RangedComponent rangedcomponent, MeleeComponent meleecomponent)
	{
		super(id, rangedcomponent, meleecomponent);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining)
	{
		if (RangedComponent.isReloaded(stack))
		{
			return modelLocationLoaded;
		}
		return super.getModel(stack, player, useRemaining);
	}
}
