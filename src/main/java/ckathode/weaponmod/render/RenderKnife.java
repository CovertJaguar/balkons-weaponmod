package ckathode.weaponmod.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import ckathode.weaponmod.WeaponModResources;
import ckathode.weaponmod.entity.projectile.EntityKnife;

public class RenderKnife extends Render
{
	public RenderKnife(RenderManager rendermanager)
	{
		super(rendermanager);
	}

	public void renderKnife(EntityKnife entityarrow, double d, double d1, double d2, float f, float f1)
	{
		bindEntityTexture(entityarrow);
		GL11.glPushMatrix();
		{
			GL11.glTranslatef((float) d, (float) d1, (float) d2);
			GL11.glRotatef((entityarrow.prevRotationYaw + (entityarrow.rotationYaw - entityarrow.prevRotationYaw) * f1) - 90F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(entityarrow.prevRotationPitch + (entityarrow.rotationPitch - entityarrow.prevRotationPitch) * f1, 0.0F, 0.0F, 1.0F);
			Tessellator tess = Tessellator.getInstance();
			WorldRenderer renderer = tess.getWorldRenderer();
			float[] color = entityarrow.getMaterialColor();
			int i = 0;
			float f2 = 0.0F;
			float f3 = 0.5F;
			float f4 = (0 + i * 10) / 32F;
			float f5 = (5 + i * 10) / 32F;
			float f6 = 0.0F;
			float f7 = 0.15625F;
			float f8 = (5 + i * 10) / 32F;
			float f9 = (10 + i * 10) / 32F;
			float f13 = 0.3125F;
			float f14 = 0.46875F;
			float f10 = 0.05625F;
			GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
			float f11 = entityarrow.arrowShake - f1;
			if (f11 > 0.0F)
			{
				float f12 = -MathHelper.sin(f11 * 3F) * f11;
				GL11.glRotatef(f12, 0.0F, 0.0F, 1.0F);
			}
			GL11.glRotatef(45F, 1.0F, 0.0F, 0.0F);
			GL11.glScalef(f10, f10, f10);
			GL11.glTranslatef(-4F, 0.0F, 0.0F);
			GL11.glNormal3f(f10, 0.0F, 0.0F);
			renderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
			{
				renderer.pos(-7D, -2D, -2D).tex(f6, f8).endVertex();
				renderer.pos(-7D, -2D, 2D).tex(f7, f8).endVertex();
				renderer.pos(-7D, 2D, 2D).tex(f7, f9).endVertex();
				renderer.pos(-7D, 2D, -2D).tex(f6, f9).endVertex();
			}
			tess.draw();
			GL11.glNormal3f(-f10, 0.0F, 0.0F);
			renderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
			{
				renderer.pos(-7D, 2D, -2D).tex(f6, f8).endVertex();
				renderer.pos(-7D, 2D, 2D).tex(f7, f8).endVertex();
				renderer.pos(-7D, -2D, 2D).tex(f7, f9).endVertex();
				renderer.pos(-7D, -2D, -2D).tex(f6, f9).endVertex();
			}
			tess.draw();
			for (int j = 0; j < 4; j++)
			{
				GL11.glRotatef(90F, 1.0F, 0.0F, 0.0F);
				GL11.glNormal3f(0.0F, 0.0F, f10);
				renderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
				{
					renderer.pos(-8D, -2D, 0.0D).tex(f2, f4).color(1F, 1F, 1F, 1F).endVertex();
					renderer.pos(8D, -2D, 0.0D).tex(f3, f4).color(1F, 1F, 1F, 1F).endVertex();
					renderer.pos(8D, 2D, 0.0D).tex(f3, f5).color(1F, 1F, 1F, 1F).endVertex();
					renderer.pos(-8D, 2D, 0.0D).tex(f2, f5).color(1F, 1F, 1F, 1F).endVertex();

					renderer.pos(-8D, -2D, 0.0D).tex(f2, f13).color(color[0], color[1], color[2], 1F).endVertex();
					renderer.pos(8D, -2D, 0.0D).tex(f3, f13).color(color[0], color[1], color[2], 1F).endVertex();
					renderer.pos(8D, 2D, 0.0D).tex(f3, f14).color(color[0], color[1], color[2], 1F).endVertex();
					renderer.pos(-8D, 2D, 0.0D).tex(f2, f14).color(color[0], color[1], color[2], 1F).endVertex();
				}
				tess.draw();
			}
			
			GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
		}
		GL11.glPopMatrix();
	}
	
	@Override
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
	{
		renderKnife((EntityKnife) entity, d, d1, d2, f, f1);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return WeaponModResources.Textures.KNIFE;
	}
}
