package ckathode.weaponmod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import ckathode.weaponmod.entity.EntityDummy;

public class ItemDummy extends WMItem
{
	public ItemDummy(String id)
	{
		super(id);
		maxStackSize = 1;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		if (world.isRemote) return itemstack;
		float f = 1.0F;
		float f1 = entityplayer.prevRotationPitch + (entityplayer.rotationPitch - entityplayer.prevRotationPitch) * f;
		float f2 = entityplayer.prevRotationYaw + (entityplayer.rotationYaw - entityplayer.prevRotationYaw) * f;
		double d = entityplayer.prevPosX + (entityplayer.posX - entityplayer.prevPosX) * f;
		double d1 = (entityplayer.prevPosY + (entityplayer.posY - entityplayer.prevPosY) * f + 1.62D) - entityplayer.getYOffset();
		double d2 = entityplayer.prevPosZ + (entityplayer.posZ - entityplayer.prevPosZ) * f;
		Vec3 vec3d = new Vec3(d, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
		float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
		float f5 = -MathHelper.cos(-f1 * 0.01745329F);
		float f6 = MathHelper.sin(-f1 * 0.01745329F);
		float f7 = f4 * f5;
		float f8 = f6;
		float f9 = f3 * f5;
		double d3 = 5D;
		Vec3 vec3d1 = vec3d.addVector(f7 * d3, f8 * d3, f9 * d3);
		MovingObjectPosition movingobjectposition = world.rayTraceBlocks(vec3d, vec3d1, true);
		if (movingobjectposition != null)
		{
			if (movingobjectposition.typeOfHit == MovingObjectType.BLOCK)
			{
				BlockPos pos = movingobjectposition.getBlockPos();
				int i = pos.getY();
				if (world.getBlockState(pos).getBlock() == Blocks.snow)
				{
					i--;
				}
				EntityDummy entitydummy = new EntityDummy(world, pos.getX() + 0.5F, i + 1.0F, pos.getZ() + 0.5F);
				entitydummy.rotationYaw = entityplayer.rotationYaw;
				world.spawnEntityInWorld(entitydummy);
				if (!entityplayer.capabilities.isCreativeMode || !world.isRemote)
				{
					itemstack.stackSize--;
				}
			}
		}
		return itemstack;
	}
}
