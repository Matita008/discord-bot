package org.matita08.listener;

import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.channel.concrete.*;
import net.dv8tion.jda.api.events.guild.member.*;
import org.jetbrains.annotations.*;
import org.matita08.tools.*;

import java.awt.*;

import static org.matita08.tools.Utilities.*;

public class Events {
    public static void join(GuildMemberJoinEvent event) {
        GuildSettings s = getGuildSettings(event.getGuild());
        assert s != null;
        TextChannel t = s.join;
        if (t == null) return;
        EmbedBuilder e = new EmbedBuilder();
        e.setColor(Color.CYAN);
        e.setTitle("Welcome to " + s.guild.getName());
        e.setDescription("Benvenuto " + event.getUser().getAsMention() + " nel mio server discord\n ti auguro una buona permanenza!\n per goderti al meglio quest'esperienza leggi le regole in" + s.rules.getAsMention());
        t.sendMessageEmbeds(e.build()).queue();
        //System.out.println(event.getUser().getAsMention() + " has joined " + event.getGuild().getName());
    }

    public static void leave(@NotNull GuildMemberRemoveEvent event) {
        GuildSettings s = getGuildSettings(event.getGuild());
        assert s != null;
        TextChannel t = s.leave;
        if (t == null) return;
        EmbedBuilder e = new EmbedBuilder();
        e.setColor(Color.RED);
        e.setTitle("Welcome to " + s.guild.getName());
        e.setDescription(event.getUser().getAsMention() + " ci ha lasciato.\n ora siamo solo " + event.getGuild().getMemberCount());
        t.sendMessageEmbeds(e.build()).queue();
        //System.out.println(event.getUser().getAsMention() + " has left " + event.getGuild().getName());

    }
}
