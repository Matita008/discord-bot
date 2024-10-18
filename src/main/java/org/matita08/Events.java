package org.matita08;

import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.channel.concrete.*;
import net.dv8tion.jda.api.events.guild.member.*;
import org.jetbrains.annotations.*;

import java.awt.*;

import static org.matita08.Utilities.*;

public class Events {
    public static void join(GuildMemberJoinEvent event) {
        GuildSettings s = getGuildSettings(event.getGuild());
        if (s == null) return;
        TextChannel t = (TextChannel) s.join;
        EmbedBuilder e = new EmbedBuilder();
        e.setColor(Color.CYAN);
        e.setTitle("Welcome to " + s.guild.getName());
        e.setDescription("Benvenuto " + event.getUser().getAsMention() + " nel mio server discord\n ti auguro una buona permanenza!\n per goderti al meglio quest'esperienza leggi le regole in" + s.rules.getAsMention());
        t.sendMessageEmbeds(e.build()).queue();
        //System.out.println(event.getUser().getAsMention() + " has joined " + event.getGuild().getName());
    }

    public static void leave(@NotNull GuildMemberRemoveEvent event) {
        GuildSettings s = getGuildSettings(event.getGuild());
        if (s == null) return;
        TextChannel t = (TextChannel) s.leave;
        EmbedBuilder e = new EmbedBuilder();
        e.setColor(Color.RED);
        e.setTitle("Welcome to " + s.guild.getName());
        e.setDescription(event.getUser().getAsMention() + " ci ha lasciato.\n ora siamo solo " + event.getGuild().getMemberCount());
        t.sendMessageEmbeds(e.build()).queue();
        //System.out.println(event.getUser().getAsMention() + " has left " + event.getGuild().getName());

    }
}
