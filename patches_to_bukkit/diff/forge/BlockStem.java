package net.minecraft.server;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraftforge.common.ForgeDirection;

public class BlockStem extends BlockFlower
{
    /** Defines if it is a Melon or a Pumpkin that the stem is producing. */
    private Block blockFruit;

    protected BlockStem(int var1, Block var2)
    {
        super(var1, 111);
        this.blockFruit = var2;
        this.b(true);
        float var3 = 0.125F;
        this.a(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.25F, 0.5F + var3);
        this.a((CreativeModeTab)null);
    }

    /**
     * Gets passed in the blockID of the block below and supposed to return true if its allowed to grow on the type of
     * blockID passed in. Args: blockID
     */
    protected boolean d_(int var1)
    {
        return var1 == Block.SOIL.id;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void b(World var1, int var2, int var3, int var4, Random var5)
    {
        super.b(var1, var2, var3, var4, var5);

        if (var1.getLightLevel(var2, var3 + 1, var4) >= 9)
        {
            float var6 = this.n(var1, var2, var3, var4);

            if (var5.nextInt((int)(25.0F / var6) + 1) == 0)
            {
                int var7 = var1.getData(var2, var3, var4);

                if (var7 < 7)
                {
                    ++var7;
                    var1.setData(var2, var3, var4, var7);
                }
                else
                {
                    if (var1.getTypeId(var2 - 1, var3, var4) == this.blockFruit.id)
                    {
                        return;
                    }

                    if (var1.getTypeId(var2 + 1, var3, var4) == this.blockFruit.id)
                    {
                        return;
                    }

                    if (var1.getTypeId(var2, var3, var4 - 1) == this.blockFruit.id)
                    {
                        return;
                    }

                    if (var1.getTypeId(var2, var3, var4 + 1) == this.blockFruit.id)
                    {
                        return;
                    }

                    int var8 = var5.nextInt(4);
                    int var9 = var2;
                    int var10 = var4;

                    if (var8 == 0)
                    {
                        var9 = var2 - 1;
                    }

                    if (var8 == 1)
                    {
                        ++var9;
                    }

                    if (var8 == 2)
                    {
                        var10 = var4 - 1;
                    }

                    if (var8 == 3)
                    {
                        ++var10;
                    }

                    int var11 = var1.getTypeId(var9, var3 - 1, var10);
                    boolean var12 = byId[var11] != null && byId[var11].canSustainPlant(var1, var9, var3 - 1, var10, ForgeDirection.UP, this);

                    if (var1.getTypeId(var9, var3, var10) == 0 && (var12 || var11 == Block.DIRT.id || var11 == Block.GRASS.id))
                    {
                        var1.setTypeId(var9, var3, var10, this.blockFruit.id);
                    }
                }
            }
        }
    }

    public void l(World var1, int var2, int var3, int var4)
    {
        var1.setData(var2, var3, var4, 7);
    }

    private float n(World var1, int var2, int var3, int var4)
    {
        float var5 = 1.0F;
        int var6 = var1.getTypeId(var2, var3, var4 - 1);
        int var7 = var1.getTypeId(var2, var3, var4 + 1);
        int var8 = var1.getTypeId(var2 - 1, var3, var4);
        int var9 = var1.getTypeId(var2 + 1, var3, var4);
        int var10 = var1.getTypeId(var2 - 1, var3, var4 - 1);
        int var11 = var1.getTypeId(var2 + 1, var3, var4 - 1);
        int var12 = var1.getTypeId(var2 + 1, var3, var4 + 1);
        int var13 = var1.getTypeId(var2 - 1, var3, var4 + 1);
        boolean var14 = var8 == this.id || var9 == this.id;
        boolean var15 = var6 == this.id || var7 == this.id;
        boolean var16 = var10 == this.id || var11 == this.id || var12 == this.id || var13 == this.id;

        for (int var17 = var2 - 1; var17 <= var2 + 1; ++var17)
        {
            for (int var18 = var4 - 1; var18 <= var4 + 1; ++var18)
            {
                int var19 = var1.getTypeId(var17, var3 - 1, var18);
                float var20 = 0.0F;

                if (byId[var19] != null && byId[var19].canSustainPlant(var1, var17, var3 - 1, var18, ForgeDirection.UP, this))
                {
                    var20 = 1.0F;

                    if (byId[var19].isFertile(var1, var17, var3 - 1, var18))
                    {
                        var20 = 3.0F;
                    }
                }

                if (var17 != var2 || var18 != var4)
                {
                    var20 /= 4.0F;
                }

                var5 += var20;
            }
        }

        if (var16 || var14 && var15)
        {
            var5 /= 2.0F;
        }

        return var5;
    }

    @SideOnly(Side.CLIENT)
    public int g_(int var1)
    {
        int var2 = var1 * 32;
        int var3 = 255 - var1 * 8;
        int var4 = var1 * 4;
        return var2 << 16 | var3 << 8 | var4;
    }

    @SideOnly(Side.CLIENT)
    public int b(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.g_(var1.getData(var2, var3, var4));
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int a(int var1, int var2)
    {
        return this.textureId;
    }

    /**
     * Sets the block's bounds for rendering it as an item
     */
    public void f()
    {
        float var1 = 0.125F;
        this.a(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, 0.25F, 0.5F + var1);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void updateShape(IBlockAccess var1, int var2, int var3, int var4)
    {
        this.maxY = (double)((float)(var1.getData(var2, var3, var4) * 2 + 2) / 16.0F);
        float var5 = 0.125F;
        this.a(0.5F - var5, 0.0F, 0.5F - var5, 0.5F + var5, (float)this.maxY, 0.5F + var5);
    }

    /**
     * The type of render function that is called for this block
     */
    public int d()
    {
        return 19;
    }

    @SideOnly(Side.CLIENT)
    public int d(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getData(var2, var3, var4);
        return var5 < 7 ? -1 : (var1.getTypeId(var2 - 1, var3, var4) == this.blockFruit.id ? 0 : (var1.getTypeId(var2 + 1, var3, var4) == this.blockFruit.id ? 1 : (var1.getTypeId(var2, var3, var4 - 1) == this.blockFruit.id ? 2 : (var1.getTypeId(var2, var3, var4 + 1) == this.blockFruit.id ? 3 : -1))));
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropNaturally(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        super.dropNaturally(var1, var2, var3, var4, var5, var6, var7);
    }

    public ArrayList getBlockDropped(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        ArrayList var7 = new ArrayList();

        for (int var8 = 0; var8 < 3; ++var8)
        {
            if (var1.random.nextInt(15) <= var5)
            {
                var7.add(new ItemStack(this.blockFruit == PUMPKIN ? Item.PUMPKIN_SEEDS : Item.MELON_SEEDS));
            }
        }

        return var7;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int getDropType(int var1, Random var2, int var3)
    {
        return -1;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int a(Random var1)
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public int a(World var1, int var2, int var3, int var4)
    {
        return this.blockFruit == Block.PUMPKIN ? Item.PUMPKIN_SEEDS.id : (this.blockFruit == Block.MELON ? Item.MELON_SEEDS.id : 0);
    }
}
