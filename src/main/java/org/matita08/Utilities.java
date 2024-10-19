package org.matita08;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.*;
import net.dv8tion.jda.api.interactions.commands.*;
import net.dv8tion.jda.api.interactions.commands.build.*;
import net.dv8tion.jda.api.requests.restaction.*;

public class Utilities {
    public static GuildSettings[] guildsSettings;

    public static void load() {
        if (guildsSettings == null) {
            GuildSettings[] tmp = new GuildSettings[discordBot.bot.getShardManager().getGuilds().size()];
            int i = 0;
            for (Guild gu : discordBot.bot.getShardManager().getGuilds()) {
                if (i < tmp.length) tmp[i++] = new GuildSettings(gu);
            }
            guildsSettings = tmp;
        }
    }

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

    public static void log(TextChannel c,@NotNull Guild g,String message) {
        if(getGuildSetting(g).logChannel!=null) c=(TextChannel)getGuildSetting(g).logChannel;
        c.sendMessage(message);
    }
    // This method is called when the bot is ready to add commands. This is where we add the commands to the server.
    public static void loadSlashCommands(Guild g) {
        if (g != null) {
            CommandListUpdateAction commands = g.updateCommands();
            commands.addCommands(Commands.slash("hello", "Have the bot say hello to you in an ephemeral message!"),
                            Commands.slash("ping", "ping the bot to see if is online")
                                    .addOptions(new OptionData(OptionType.BOOLEAN, "type", "true or unset to see the internet ping, false to see the lag of the bot", false)),
                            Commands.slash("error", "Send an invalid command to the bot to error it").setDefaultPermissions(DefaultMemberPermissions.DISABLED),
                            Commands.slash("setchannel", "set a channel for a specified use").setDefaultPermissions(DefaultMemberPermissions.DISABLED)
                                    .addOptions(new OptionData(OptionType.CHANNEL, "channel", "Channel of where to send log messages", true)
                                                    .setChannelTypes(ChannelType.TEXT, ChannelType.NEWS),
                                            new OptionData(OptionType.INTEGER, "type", "the type of log", true)
                                                    .addChoices(new Command.Choice("Welcome", "join"), new Command.Choice("Leave", "leave"), new Command.Choice("Log","log"))),
                            Commands.slash("ticket", "send the ticket embed").setDefaultPermissions(DefaultMemberPermissions.DISABLED)
                                    .addOptions(new OptionData(OptionType.CHANNEL, "textchannel", "The channel for sending the ticket embed,null to select the current", true)
                                                    .setChannelTypes(ChannelType.TEXT, ChannelType.NEWS),
                                            new OptionData(OptionType.BOOLEAN, "type", "true to use dropdown mode, false to use buttons", true)))
                    .queue();
        }
    }
}
