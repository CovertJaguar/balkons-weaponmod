package ckathode.weaponmod.render;

import ckathode.weaponmod.WeaponModResources;
import ckathode.weaponmod.entity.projectile.EntityBoomerang;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderBoomerang extends Render
{
	public RenderBoomerang(RenderManager rendermanager)
	{
		super(rendermanager);
	}

	private void renderBoomerang(EntityBoomerang entityarrow, double d, double d1, double d2, float f, float f1)
	{
		bindEntityTexture(entityarrow);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d, (float) d1, (float) d2);
		GL11.glRotatef(entityarrow.prevRotationPitch + (entityarrow.rotationPitch - entityarrow.prevRotationPitch) * f1, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef((entityarrow.prevRotationYaw + (entityarrow.rotationYaw - entityarrow.prevRotationYaw) * f1) - 90F, 0.0F, 1.0F, 0.0F);
		Tessellator tess = Tessellator.getInstance();
		VertexBuffer renderer = tess.getBuffer();

		int mat = entityarrow.getWeaponMaterialId();
		float[] color = entityarrow.getMaterialColor();
		float ft0 = 0.0F;
		float ft1 = 0.5F;
		float ft2 = 1.0F;
		
		float fh = 0.08F;
		float f2 = 0.2F;
		float f3 = 0.9F;
		float f4 = 1F - f2;
		
		float ft3 = 0.5F;
		float ft4 = 0.65625F;
		
		GL11.glTranslatef(-0.5F, 0F, -0.5F);
		GL11.glColor3f(1F, 1F, 1F);
		GL11.glNormal3f(0F, 1F, 0F);

		renderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);

		renderer.pos(0D, 0D, 1D).tex(ft1, ft0).color(1F, 1F, 1F, 1F).endVertex();
		renderer.pos(1D, 0D, 1D).tex(ft0, ft0).color(1F, 1F, 1F, 1F).endVertex();
		renderer.pos(1D, 0D, 0D).tex(ft0, ft1).color(1F, 1F, 1F, 1F).endVertex();
		renderer.pos(0D, 0D, 0D).tex(ft1, ft1).color(1F, 1F, 1F, 1F).endVertex();

		if (mat != 0)
		{
			renderer.pos(0D, 0D, 1D).tex(ft2, ft0).color(color[0], color[1], color[2], 1F).endVertex();
			renderer.pos(1D, 0D, 1D).tex(ft1, ft0).color(color[0], color[1], color[2], 1F).endVertex();
			renderer.pos(1D, 0D, 0D).tex(ft1, ft1).color(color[0], color[1], color[2], 1F).endVertex();
			renderer.pos(0D, 0D, 0D).tex(ft2, ft1).color(color[0], color[1], color[2], 1F).endVertex();
		}
		tess.draw();
		GL11.glNormal3f(0F, -1F, 0F);

		renderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
		renderer.pos(1D, 0D, 0D).tex(ft0, ft1).color(1F, 1F, 1F, 1F).endVertex();
		renderer.pos(1D, 0D, 1D).tex(ft1, ft1).color(1F, 1F, 1F, 1F).endVertex();
		renderer.pos(0D, 0D, 1D).tex(ft1, ft0).color(1F, 1F, 1F, 1F).endVertex();
		renderer.pos(0D, 0D, 0D).tex(ft0, ft0).color(1F, 1F, 1F, 1F).endVertex();
		
		if (mat != 0)
		{
			renderer.pos(1D, 0D, 0D).tex(ft1, ft1).color(color[0], color[1], color[2], 1F).endVertex();
			renderer.pos(1D, 0D, 1D).tex(ft2, ft1).color(color[0], color[1], color[2], 1F).endVertex();
			renderer.pos(0D, 0D, 1D).tex(ft2, ft0).color(color[0], color[1], color[2], 1F).endVertex();
			renderer.pos(0D, 0D, 0D).tex(ft1, ft0).color(color[0], color[1], color[2], 1F).endVertex();
		}
		tess.draw();
		
		float sqrt2 = (float) Math.sqrt(2);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glNormal3f(-sqrt2, 0F, sqrt2);

		renderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
		renderer.pos(f2, -fh, f4).tex(ft1, ft3).color(1F, 1F, 1F, 1F).endVertex();
		renderer.pos(f2, fh, f4).tex(ft1, ft4).color(1F, 1F, 1F, 1F).endVertex();
		renderer.pos(f3, fh, f4).tex(ft0, ft4).color(1F, 1F, 1F, 1F).endVertex();
		renderer.pos(f3, -fh, f4).tex(ft0, ft3).color(1F, 1F, 1F, 1F).endVertex();
		
		if (mat != 0)
		{
			renderer.pos(f2, -fh, f4).tex(ft2, ft3).color(color[0], color[1], color[2], 1F).endVertex();
			renderer.pos(f2, fh, f4).tex(ft2, ft4).color(color[0], color[1], color[2], 1F).endVertex();
			renderer.pos(f3, fh, f4).tex(ft1, ft4).color(color[0], color[1], color[2], 1F).endVertex();
			renderer.pos(f3, -fh, f4).tex(ft1, ft3).color(color[0], color[1], color[2], 1F).endVertex();
		}

		renderer.pos(f2, -fh, f4).tex(ft1, ft3).color(1F, 1F, 1F, 1F).endVertex();
		renderer.pos(f2, fh, f4).tex(ft1, ft4).color(1F, 1F, 1F, 1F).endVertex();
		renderer.pos(f2, fh, f2).tex(ft0, ft4).color(1F, 1F, 1F, 1F).endVertex();
		renderer.pos(f2, -fh, f2).tex(ft0, ft3).color(1F, 1F, 1F, 1F).endVertex();

		if (mat != 0)
		{
			renderer.pos(f2, -fh, f4).tex(ft2, ft3).color(color[0], color[1], color[2], 1F).endVertex();
			renderer.pos(f2, fh, f4).tex(ft2, ft4).color(color[0], color[1], color[2], 1F).endVertex();
			renderer.pos(f2, fh, f2).tex(ft1, ft4).color(color[0], color[1], color[2], 1F).endVertex();
			renderer.pos(f2, -fh, f2).tex(ft1, ft3).color(color[0], color[1], color[2], 1F).endVertex();
		}

		tess.draw();
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();
	}
	
	@Override
	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9)
	{
		renderBoomerang((EntityBoomerang) var1, var2, var4, var6, var8, var9);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return WeaponModResources.Textures.BOOMERANG;
	}
}
