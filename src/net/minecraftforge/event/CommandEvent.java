package net.minecraftforge.event;

import net.minecraft.server.ICommand;
import net.minecraft.server.ICommandListener;

@Cancelable
public class CommandEvent extends Event
{
    public final ICommand command;
    public final ICommandListener sender;
    public String[] parameters;
    public Throwable exception;

    public CommandEvent(ICommand var1, ICommandListener var2, String[] var3)
    {
        this.command = var1;
        this.sender = var2;
        this.parameters = var3;
    }
}
