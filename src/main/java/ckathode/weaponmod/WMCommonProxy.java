package ckathode.weaponmod;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import ckathode.weaponmod.network.MsgCannonFire;
import ckathode.weaponmod.network.MsgExplosion;
import ckathode.weaponmod.network.WMMessagePipeline;

public class WMCommonProxy
{
	public void registerEventHandlers()
	{
		MinecraftForge.EVENT_BUS.register(new WMCommonEventHandler());
	}
	
	public void registerPackets(WMMessagePipeline pipeline)
	{
		pipeline.registerPacket(MsgCannonFire.class);
		pipeline.registerPacket(MsgExplosion.class);
	}
	
	public void registerIcons(WeaponModConfig config)
	{
	}
	
	public void registerRenderers(WeaponModConfig config)
	{
	}

	public void setTextureName(Item item, int damage, String texturename)
	{
	}

	public void setTextureName(Item item, String texturename)
	{
		setTextureName(item, 0, texturename);
	}
}
