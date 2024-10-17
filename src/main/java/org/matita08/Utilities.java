package org.matita08;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.interactions.commands.*;
import net.dv8tion.jda.api.interactions.commands.build.*;
import net.dv8tion.jda.api.requests.restaction.*;

public class Utilities {
    public static GuildSettings[] guildsSettings;

    public static GuildSettings getGuildSettings(Guild g) {
        for (GuildSettings s : guildsSettings) {
            if (s.guild == g) return s;
        }
        return null;
    }

    public static void loadBaseSettings(Guild g) {
        GuildSettings[] tmp = new GuildSettings[guildsSettings.length + 1];
        tmp = guildsSettings;
        tmp[tmp.length - 1] = new GuildSettings(g);
        guildsSettings = tmp;
    }

    // This method is called when the bot is ready to add commands. This is where we add the commands to the server.
    public static void loadSlashCommands(Guild g) {
        if (g != null) {
            CommandListUpdateAction commands = g.updateCommands();
            commands.addCommands(Commands.slash("hello", "Have the bot say hello to you in an ephemeral message!")).queue();
            commands.addCommands(Commands.slash("ping", "ping the bot to see if is online").addOptions(new OptionData(OptionType.BOOLEAN, "type", "true or unset to see the internet ping, false to see the lag of the bot", false))).queue();
            // All slash commands must be added here. They follow a strict set of rules and are not as flexible as text commands.
            // Since we only need a simple command, we will only use a slash command without any arguments.
        }
    }
}
