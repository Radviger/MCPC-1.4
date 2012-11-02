package net.minecraftforge.common;

public enum ForgeDirection
{
    DOWN(0, -1, 0),
    UP(0, 1, 0),
    NORTH(0, 0, -1),
    SOUTH(0, 0, 1),
    WEST(-1, 0, 0),
    EAST(1, 0, 0),
    UNKNOWN(0, 0, 0);
    public final int offsetX;
    public final int offsetY;
    public final int offsetZ;
    public final int flag;
    public static final int[] opposite = new int[]{1, 0, 3, 2, 5, 4, 6};

    private ForgeDirection(int var3, int var4, int var5)
    {
        this.offsetX = var3;
        this.offsetY = var4;
        this.offsetZ = var5;
        this.flag = 1 << this.ordinal();
    }

    public static ForgeDirection getOrientation(int var0)
    {
        return var0 >= 0 && var0 < values().length ? values()[var0] : UNKNOWN;
    }

    public ForgeDirection getOpposite()
    {
        return getOrientation(opposite[this.ordinal()]);
    }
}
