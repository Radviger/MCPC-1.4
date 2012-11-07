package net.minecraftforge.event;

import net.minecraft.server.EntityPlayer;

@Cancelable
public class ServerChatEvent extends Event {
        public final String message, username;
        public final EntityPlayer player;
        public String line;
        public ServerChatEvent(EntityPlayer player, String message, String line)
        {
            super();
            this.message = message;
            this.player = player;
            this.username = player.displayName;
            this.line = "<" + username + "> " + message;
        }
}