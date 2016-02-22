package ckathode.weaponmod;

import ckathode.weaponmod.item.DartType;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import ckathode.weaponmod.entity.EntityCannon;
import ckathode.weaponmod.entity.EntityDummy;
import ckathode.weaponmod.entity.projectile.EntityBlowgunDart;
import ckathode.weaponmod.entity.projectile.EntityBlunderShot;
import ckathode.weaponmod.entity.projectile.EntityBoomerang;
import ckathode.weaponmod.entity.projectile.EntityCannonBall;
import ckathode.weaponmod.entity.projectile.EntityCrossbowBolt;
import ckathode.weaponmod.entity.projectile.EntityDynamite;
import ckathode.weaponmod.entity.projectile.EntityFlail;
import ckathode.weaponmod.entity.projectile.EntityJavelin;
import ckathode.weaponmod.entity.projectile.EntityKnife;
import ckathode.weaponmod.entity.projectile.EntityMusketBullet;
import ckathode.weaponmod.entity.projectile.EntitySpear;
import ckathode.weaponmod.network.WMMessagePipeline;
import ckathode.weaponmod.render.GuiOverlayReloaded;
import ckathode.weaponmod.render.RenderBlowgunDart;
import ckathode.weaponmod.render.RenderBlunderShot;
import ckathode.weaponmod.render.RenderBoomerang;
import ckathode.weaponmod.render.RenderCannon;
import ckathode.weaponmod.render.RenderCannonBall;
import ckathode.weaponmod.render.RenderCrossbowBolt;
import ckathode.weaponmod.render.RenderDummy;
import ckathode.weaponmod.render.RenderDynamite;
import ckathode.weaponmod.render.RenderFlail;
import ckathode.weaponmod.render.RenderJavelin;
import ckathode.weaponmod.render.RenderKnife;
import ckathode.weaponmod.render.RenderMusketBullet;
import ckathode.weaponmod.render.RenderSpear;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.ArrayList;

public class WMClientProxy extends WMCommonProxy
{
	@Override
	public void registerEventHandlers()
	{
		super.registerEventHandlers();
		WMClientEventHandler eventhandler = new WMClientEventHandler();
		FMLCommonHandler.instance().bus().register(eventhandler);
		MinecraftForge.EVENT_BUS.register(eventhandler);
		MinecraftForge.EVENT_BUS.register(new GuiOverlayReloaded(FMLClientHandler.instance().getClient()));

	}

	@Override
	public void registerPackets(WMMessagePipeline pipeline)
	{
		super.registerPackets(pipeline);
	}

	@Override
	public void registerIcons(WeaponModConfig config)
	{
		if (config.isEnabled("halberd"))
		{
			addVariantNames(BalkonsWeaponMod.halberdWood, "halberd.wood", "halberd.wood-stabbing");
			addVariantNames(BalkonsWeaponMod.halberdStone, "halberd.stone", "halberd.stone-stabbing");
			addVariantNames(BalkonsWeaponMod.halberdSteel, "halberd.iron", "halberd.iron-stabbing");
			addVariantNames(BalkonsWeaponMod.halberdGold, "halberd.gold", "halberd.gold-stabbing");
			addVariantNames(BalkonsWeaponMod.halberdDiamond, "halberd.diamond", "halberd.diamond-stabbing");
		}

		if (config.isEnabled("flail"))
		{
			addVariantNames(BalkonsWeaponMod.flailWood, "flail.wood", "flail-thrown");
			addVariantNames(BalkonsWeaponMod.flailStone, "flail.stone", "flail-thrown");
			addVariantNames(BalkonsWeaponMod.flailSteel, "flail.iron", "flail-thrown");
			addVariantNames(BalkonsWeaponMod.flailGold, "flail.gold", "flail-thrown");
			addVariantNames(BalkonsWeaponMod.flailDiamond, "flail.diamond", "flail-thrown");
		}

		if (config.isEnabled("crossbow"))
		{
			addVariantNames(BalkonsWeaponMod.crossbow, "crossbow", "crossbow-loaded");
		}

		if (config.isEnabled("blowgun"))
		{
			ArrayList<String> typenames = new ArrayList<String>();
			for (int damage = 0; damage < DartType.dartTypes.length; damage++)
			{
				DartType type = DartType.dartTypes[damage];
				if (type != null)
				{
					typenames.add(type.typeName);
				}
			}
			addVariantNames(BalkonsWeaponMod.dart, typenames.toArray(new String[typenames.size()]));
		}
	}

	@Override
	public void registerRenderers(WeaponModConfig config)
	{
		RenderManager rendermanager = FMLClientHandler.instance().getClient().getRenderManager();
		//LongItemRenderer longrender = new LongItemRenderer();
		//StabItemRenderer stabrender = new StabItemRenderer();

		if (config.isEnabled("halberd"))
		{
			/*MinecraftForgeClient.registerItemRenderer(BalkonsWeaponMod.halberdWood, longrender);
			MinecraftForgeClient.registerItemRenderer(BalkonsWeaponMod.halberdStone, longrender);
			MinecraftForgeClient.registerItemRenderer(BalkonsWeaponMod.halberdSteel, longrender);
			MinecraftForgeClient.registerItemRenderer(BalkonsWeaponMod.halberdDiamond, longrender);
			MinecraftForgeClient.registerItemRenderer(BalkonsWeaponMod.halberdGold, longrender);*/
		}

		if (config.isEnabled("knife"))
		{
			RenderingRegistry.registerEntityRenderingHandler(EntityKnife.class, new RenderKnife(rendermanager));
			/*
			MinecraftForgeClient.registerItemRenderer(BalkonsWeaponMod.knifeWood.itemID, stabrender);
			MinecraftForgeClient.registerItemRenderer(BalkonsWeaponMod.knifeStone.itemID, stabrender);
			MinecraftForgeClient.registerItemRenderer(BalkonsWeaponMod.knifeSteel.itemID, stabrender);
			MinecraftForgeClient.registerItemRenderer(BalkonsWeaponMod.knifeDiamond.itemID, stabrender);
			MinecraftForgeClient.registerItemRenderer(BalkonsWeaponMod.knifeGold.itemID, stabrender);*/
		}

		if (config.isEnabled("spear"))
		{
			RenderingRegistry.registerEntityRenderingHandler(EntitySpear.class, new RenderSpear(rendermanager));
			/*
			MinecraftForgeClient.registerItemRenderer(BalkonsWeaponMod.spearWood, longrender);
			MinecraftForgeClient.registerItemRenderer(BalkonsWeaponMod.spearStone, longrender);
			MinecraftForgeClient.registerItemRenderer(BalkonsWeaponMod.spearSteel, longrender);
			MinecraftForgeClient.registerItemRenderer(BalkonsWeaponMod.spearDiamond, longrender);
			MinecraftForgeClient.registerItemRenderer(BalkonsWeaponMod.spearGold, longrender);*/
		}

		if (config.isEnabled("javelin"))
		{
			RenderingRegistry.registerEntityRenderingHandler(EntityJavelin.class, new RenderJavelin(rendermanager));
			//MinecraftForgeClient.registerItemRenderer(BalkonsWeaponMod.javelin, longrender);
		}

		if (config.isEnabled("firerod"))
		{
			//MinecraftForgeClient.registerItemRenderer(BalkonsWeaponMod.fireRod.itemID, stabrender);
		}

		if (config.isEnabled("musket"))
		{
			RenderingRegistry.registerEntityRenderingHandler(EntityMusketBullet.class, new RenderMusketBullet(rendermanager));
		}
		if (config.isEnabled("crossbow"))
		{
			RenderingRegistry.registerEntityRenderingHandler(EntityCrossbowBolt.class, new RenderCrossbowBolt(rendermanager));
			//MinecraftForgeClient.registerItemRenderer(BalkonsWeaponMod.crossbow.itemID, new CrossbowItemRenderer());
		}
		if (config.isEnabled("blowgun"))
		{
			RenderingRegistry.registerEntityRenderingHandler(EntityBlowgunDart.class, new RenderBlowgunDart(rendermanager));
		}
		if (config.isEnabled("dynamite"))
		{
			RenderingRegistry.registerEntityRenderingHandler(EntityDynamite.class, new RenderDynamite(rendermanager));
		}
		if (config.isEnabled("flail"))
		{
			RenderingRegistry.registerEntityRenderingHandler(EntityFlail.class, new RenderFlail(rendermanager));
		}
		if (config.isEnabled("cannon"))
		{
			RenderingRegistry.registerEntityRenderingHandler(EntityCannon.class, new RenderCannon(rendermanager));
			RenderingRegistry.registerEntityRenderingHandler(EntityCannonBall.class, new RenderCannonBall(rendermanager));
		}
		if (config.isEnabled("blunderbuss"))
		{
			RenderingRegistry.registerEntityRenderingHandler(EntityBlunderShot.class, new RenderBlunderShot(rendermanager));
		}
		if (config.isEnabled("dummy"))
		{
			RenderingRegistry.registerEntityRenderingHandler(EntityDummy.class, new RenderDummy(rendermanager));
		}
		if (config.isEnabled("boomerang"))
		{
			RenderingRegistry.registerEntityRenderingHandler(EntityBoomerang.class, new RenderBoomerang(rendermanager));
		}
	}

	@Override
	public void setTextureName(Item item, int damage, String texturename)
	{
		ModelLoader.setCustomModelResourceLocation(item, damage, new ModelResourceLocation("weaponmod:" + texturename, "inventory"));
	}

	private void addVariantNames(Item item, String... names)
	{
		for (int i = 0; i < names.length; i++)
		{
			names[i] = "weaponmod:" + names[i];
		}

		ModelLoader.addVariantName(item, names);
	}
}
