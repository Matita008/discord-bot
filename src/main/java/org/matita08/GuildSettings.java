package org.matita08;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.*;
import net.dv8tion.jda.api.entities.channel.concrete.*;

public class GuildSettings {
    public Channel join = null;
    public Channel leave = null;
    public Channel rules = null;
    public TextChannel logChannel = null;
    public Guild guild;

    public GuildSettings(Guild guild) {
        this.guild = guild;
        guild.getChannels().forEach(g -> {
            if (g.getType() == ChannelType.TEXT || g.getType() == ChannelType.NEWS) {
                if (g.toString().contains("welcome") || g.toString().contains("join") || g.toString().contains("benvenuto"))
                    this.join = g.getType() == ChannelType.TEXT ? (TextChannel) g : (NewsChannel) g;
                else if (g.toString().contains("leave") || g.toString().contains("goodbye") || g.toString().contains("arrivederci"))
                    this.leave = g;
                else if (g.toString().contains("rules") || g.toString().contains("regole"))
                    this.rules = g;
            }
        });
    }
}
