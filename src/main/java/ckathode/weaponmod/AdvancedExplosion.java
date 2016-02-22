package ckathode.weaponmod;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class AdvancedExplosion extends Explosion
{
	public World		worldObj;
	public Entity		exploder;
	public float		explosionSize;
	protected boolean	blocksCalculated;

	public AdvancedExplosion(World world, Entity entity, double explosionX, double explosionY, double explosionZ, float explosionSize, boolean isFlaming, boolean isSmoking)
	{
		super(world, entity, explosionX, explosionY, explosionZ, explosionSize, isFlaming, isSmoking);
		this.explosionSize = explosionSize;


		worldObj = world;
		exploder = entity;
		this.explosionSize = explosionSize;
	}

	@SuppressWarnings("unchecked")
	public void setAffectedBlockPositions(List<BlockPos> list)
	{
		func_180342_d();
		//getAffectedBlockPositions().addAll(list);
		getAffectedBlockPositions().addAll(list);
		blocksCalculated = true;
	}
	
	public void doEntityExplosion()
	{
		doEntityExplosion(DamageSource.setExplosionSource(this));
	}
	
	public void doEntityExplosion(DamageSource damagesource)
	{
		float size = explosionSize * 2F;
		int i0 = MathHelper.floor_double(getExplosionX() - size - 1.0D);
		int i1 = MathHelper.floor_double(getExplosionX() + size + 1.0D);
		int j0 = MathHelper.floor_double(getExplosionY() - size - 1.0D);
		int j1 = MathHelper.floor_double(getExplosionY() + size + 1.0D);
		int k0 = MathHelper.floor_double(getExplosionZ() - size - 1.0D);
		int k1 = MathHelper.floor_double(getExplosionZ() + size + 1.0D);
		@SuppressWarnings("unchecked")
		List<Entity> list = worldObj.getEntitiesWithinAABBExcludingEntity(exploder, new AxisAlignedBB(i0, j0, k0, i1, j1, k1));
		Vec3 vec31 = new Vec3(getExplosionX(), getExplosionY(), getExplosionZ());
		
		double dx;
		double dy;
		double dz;
		
		for (int i = 0; i < list.size(); i++)
		{
			Entity entity = list.get(i);
			double dr = entity.getDistance(getExplosionX(), getExplosionY(), getExplosionZ()) / size;
			
			if (dr <= 1.0D)
			{
				dx = entity.posX - getExplosionX();
				dy = entity.posY - getExplosionY();
				dz = entity.posZ - getExplosionZ();
				double d = MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);
				
				if (d != 0D)
				{
					dx /= d;
					dy /= d;
					dz /= d;
					double dens = worldObj.getBlockDensity(vec31, entity.getEntityBoundingBox());
					double var36 = (1.0D - dr) * dens;
					int damage = (int) ((var36 * var36 + var36) / 2.0D * 8.0D * size + 1D);
					entity.attackEntityFrom(damagesource, damage);
					entity.motionX += dx * var36;
					entity.motionY += dy * var36;
					entity.motionZ += dz * var36;
				}
			}
		}
	}
	
	public void doBlockExplosion()
	{
		if (!blocksCalculated)
		{
			calculateBlockExplosion();
		}
		for (int i = getAffectedBlockPositions().size() - 1; i >= 0; i--)
		{
			BlockPos blockpos = (BlockPos) getAffectedBlockPositions().get(i);
			IBlockState blockstate = worldObj.getBlockState(blockpos);
			Block block = blockstate.getBlock();
			if (block != null)
			{
				if (block.canDropFromExplosion(this))
				{
					block.dropBlockAsItemWithChance(worldObj, blockpos, blockstate, 1F / explosionSize, 0);
				}
				
				worldObj.setBlockToAir(blockpos);
				block.onBlockDestroyedByExplosion(worldObj, blockpos, this);
			}
		}
	}
	
	public void doParticleExplosion(boolean smallparticles, boolean bigparticles)
	{
		worldObj.playSoundEffect(getExplosionX(), getExplosionY(), getExplosionZ(), "random.explode", 4F, (1.0F + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
		if (bigparticles)
		{
			worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, getExplosionX(), getExplosionY(), getExplosionZ(), 0.0D, 0.0D, 0.0D);
		}
		if (!smallparticles) return;
		
		if (!blocksCalculated)
		{
			calculateBlockExplosion();
		}
		
		for (int i = getAffectedBlockPositions().size() - 1; i >= 0; i--)
		{
			BlockPos blockpos = (BlockPos) getAffectedBlockPositions().get(i);
			int j = blockpos.getX();
			int k = blockpos.getY();
			int l = blockpos.getZ();
			//int i1 = worldObj.getBlockId(j, k, l);
			double px = j + worldObj.rand.nextFloat();
			double py = k + worldObj.rand.nextFloat();
			double pz = l + worldObj.rand.nextFloat();
			double dx = px - getExplosionX();
			double dy = py - getExplosionY();
			double dz = pz - getExplosionZ();
			double distance = MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);
			dx /= distance;
			dy /= distance;
			dz /= distance;
			double d7 = 0.5D / (distance / explosionSize + 0.1D);
			d7 *= worldObj.rand.nextFloat() * worldObj.rand.nextFloat() + 0.3F;
			dx *= d7;
			dy *= d7;
			dz *= d7;
			worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (px + getExplosionX() * 1.0D) / 2D, (py + getExplosionY() * 1.0D) / 2D, (pz + getExplosionZ() * 1.0D) / 2D, dx, dy, dz);
			worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, px, py, pz, dx, dy, dz);
		}
	}

	public double getExplosionX()
	{
		return getPosition().xCoord;
	}

	public double getExplosionY()
	{
		return getPosition().yCoord;
	}

	public double getExplosionZ()
	{
		return getPosition().zCoord;
	}
	
	@SuppressWarnings("unchecked")
	protected void calculateBlockExplosion()
	{
		byte maxsize = 16;
		Set<BlockPos> set = new HashSet<BlockPos>();
		int i;
		int j;
		int k;
		double dx;
		double dy;
		double dz;
		
		for (i = 0; i < maxsize; i++)
		{
			for (j = 0; j < maxsize; j++)
			{
				for (k = 0; k < maxsize; k++)
				{
					if (i == 0 || i == maxsize - 1 || j == 0 || j == maxsize - 1 || k == 0 || k == maxsize - 1)
					{
						double rx = (i / (maxsize - 1.0F) * 2.0F - 1.0F);
						double ry = (j / (maxsize - 1.0F) * 2.0F - 1.0F);
						double rz = (k / (maxsize - 1.0F) * 2.0F - 1.0F);
						double rd = Math.sqrt(rx * rx + ry * ry + rz * rz);
						rx /= rd;
						ry /= rd;
						rz /= rd;
						float strength = explosionSize * (0.7F + worldObj.rand.nextFloat() * 0.6F);
						dx = getExplosionX();
						dy = getExplosionY();
						dz = getExplosionZ();
						
						for (float f = 0.3F; strength > 0.0F; strength -= f * 0.75F)
						{
							BlockPos blockpos = new BlockPos(MathHelper.floor_double(dx), MathHelper.floor_double(dy), MathHelper.floor_double(dz));
							Block block = worldObj.getBlockState(blockpos).getBlock();
							
							if (block != null)
							{
								strength -= (block.getExplosionResistance(worldObj, blockpos, exploder, this) + 0.3F) * f;
							}
							
							if (strength > 0.0F)
							{
								set.add(blockpos);
							}
							
							dx += rx * f;
							dy += ry * f;
							dz += rz * f;
						}
					}
				}
			}
		}

		getAffectedBlockPositions().addAll(set);
		blocksCalculated = true;
	}
	
	protected static final Random	rand	= new Random();
}
