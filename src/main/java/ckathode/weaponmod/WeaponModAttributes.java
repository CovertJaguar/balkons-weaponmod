package ckathode.weaponmod;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.BaseAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;

public class WeaponModAttributes extends SharedMonsterAttributes
{
	public static final BaseAttribute	IGNORE_ARMOUR_DAMAGE	= new RangedAttribute(null, "weaponmod.ignoreArmour", 0D, 0D, Double.MAX_VALUE);
	public static final BaseAttribute	WEAPON_KNOCKBACK		= new RangedAttribute(null, "weaponmod.knockback", 0.4D, 0D, Double.MAX_VALUE);
	public static final BaseAttribute	ATTACK_SPEED			= new RangedAttribute(null, "weaponmod.attackSpeed", 0D, -Double.MAX_VALUE, Double.MAX_VALUE);
	public static final BaseAttribute	RELOAD_TIME				= new RangedAttribute(null, "weaponmod.reloadTime", 0D, 0D, Double.MAX_VALUE);
	public static final BaseAttribute	WEAPON_REACH			= new RangedAttribute(null, "weaponmod.reach", 0D, 0D, Double.MAX_VALUE);
}
