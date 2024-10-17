package org.matita08;

import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.channel.concrete.*;
import net.dv8tion.jda.api.events.guild.member.*;

import static org.matita08.Utilities.*;

public class Events {
    public static void join(GuildMemberJoinEvent event) {
        GuildSettings s = getGuildSettings(event.getGuild());
        if (s == null) return;
        TextChannel t = (TextChannel) s.join;
        EmbedBuilder e = new EmbedBuilder();
        e.setTitle("Welcome to "+s.guild.getName());
        e.setDescription("Benvenuto "+event.getUser().getAsMention()+" nel mio server discord\n ti auguro una buona permanenza!\n per goderti al meglio leggi le regole #regole");
        t.sendMessageEmbeds(e.build()).queue();
    }

}
