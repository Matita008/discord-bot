package org.matita08.tools;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.*;
import net.dv8tion.jda.api.entities.channel.concrete.*;
import net.dv8tion.jda.api.interactions.commands.*;
import net.dv8tion.jda.api.interactions.commands.build.*;
import net.dv8tion.jda.api.requests.restaction.*;
import org.jetbrains.annotations.*;
import org.matita08.*;

public class Utilities {
    public static GuildSettings[] guildsSettings;

    /**
     * load the configuration of all saved guilds
     */
    public static void load() {//TODO for now it's simply creating a blank instance, when it *should* create an instance based on some saved files
        if (guildsSettings == null) {
            GuildSettings[] tmp = new GuildSettings[discordBot.getBot().getShardManager().getGuilds().isEmpty() ? 1 : discordBot.getBot().getShardManager().getGuilds().size()];
            int i = 0;
            for (Guild gu : discordBot.getBot().getShardManager().getGuilds()) {
                if (i < tmp.length) tmp[i++] = new GuildSettings(gu);
            }
            guildsSettings = tmp;
        }
    }

    /**
     * get a {@code org.matita08.tools.GuildSettings} object given a {@code net.dv8tion.jda.api.entities.Guild}
     *
     * @param g the guild you want the settings
     * @return the GuildSettings object of the guild, or null if it isn't saved
     */
    @Nullable
    public static GuildSettings getGuildSettings(Guild g) {
        for (GuildSettings s : guildsSettings) {
            if (s.guild == g) return s;
        }
        return null;
    }

    /**
     * crate and save a new {@code org.matita08.tools.GuildSettings} given a Guild if it isn't saved
     *
     * @param g the guild to add tho the saved configuration
     */
    public static void loadBaseSettings(Guild g) {
        if (getGuildSettings(g) != null) return;
        GuildSettings[] tmp = new GuildSettings[guildsSettings.length + 1];
        tmp = guildsSettings;
        tmp[tmp.length - 1] = new GuildSettings(g);
        guildsSettings = tmp;
    }

    /**
     * send a text message in the {@code org.matita08.tools.GuildSettings.logChannel} if is defined, else in the channel provided
     *
     * @param c       the fallback channel
     * @param g       the guild where you want to take the settings from
     * @param message the log message to send
     */
    public static void log(@NotNull TextChannel c, Guild g, String message) {
        if (g != null && getGuildSettings(g) != null && getGuildSettings(g).logChannel != null)
            c = getGuildSettings(g).logChannel;
        c.sendMessage(message).queue();
    }

    /**
     * load all the configured slash commands in the specified guild
     *
     * @param g the guild you want to add the slash interactions
     */
    public static void loadSlashCommands(Guild g) {
        if (g != null) loadSlashCommands(g.updateCommands());
    }

    /**
     * add all the base slash commands in the specified CommandListUpdateAction
     *
     * @param commands the CommandListUpdateAction which you want them added
     */
    public static void loadSlashCommands(@NotNull CommandListUpdateAction commands) {
        commands.addCommands(Commands.slash("hello", "Have the bot say hello to you in an ephemeral message!"),
                Commands.slash("ping", "ping the bot to see if is online")
                        .addOptions(new OptionData(OptionType.BOOLEAN, "type", "true or unset to see the internet ping, false to see the lag of the bot", false)),
                Commands.slash("error", "Send an invalid command to the bot to error it").setDefaultPermissions(DefaultMemberPermissions.DISABLED),
                Commands.slash("setchannel", "set a channel as a log").setDefaultPermissions(DefaultMemberPermissions.DISABLED)
                        .addOptions(new OptionData(OptionType.CHANNEL, "channel", "Channel of where to send log messages", true)
                                        .setChannelTypes(ChannelType.TEXT),
                                new OptionData(OptionType.STRING, "type", "The type of log", true)
                                        .addChoices(new Command.Choice("Welcome", "join"), new Command.Choice("Leave", "leave"), new Command.Choice("Log", "log"))),
                Commands.slash("ticket", "send the ticket embed").setDefaultPermissions(DefaultMemberPermissions.DISABLED)
                        .addOptions(new OptionData(OptionType.CHANNEL, "textchannel", "The channel for sending the ticket embed,null to select the current", true)
                                        .setChannelTypes(ChannelType.TEXT),
                                new OptionData(OptionType.BOOLEAN, "type", "true to use dropdown mode, false to use buttons", true)),
                Commands.slash("setrule", "Set a specified rule").setDefaultPermissions(DefaultMemberPermissions.DISABLED)
                        .addOptions(new OptionData(OptionType.STRING, "rule", "The rule to configure", true),
                                new OptionData(OptionType.BOOLEAN, "config", "What to set", true))).queue();
    }

    /**
     * load special bot commands in the specified guild
     *
     * @param g the guild
     *          IMPORTANT! Use only on private,trusted guilds, since anyone can launch the commands
     */
    public static void loadAdminCommands(Guild g) {
        if (g == null) return;
        loadSlashCommands(g.updateCommands());
        loadAdminCommands(g.updateCommands());
    }

    public static void loadAdminCommands(@NotNull CommandListUpdateAction commands) {
        commands.addCommands(Commands.slash("kill", "Save current configuration and terminate the execution of the bot"),
                Commands.slash("emergencyswitch", "USE ONY IN CASE OS AN EMERGENCY,IT WILL KILL THE BOT IMMEDIATLY").setDefaultPermissions(DefaultMemberPermissions.DISABLED),
                Commands.slash("save", "Save current configuration of the bot")).queue();
    }

    /**
     * send a message to all log channel and in the console
     *
     * @param message the message to send
     */
    public static void logAll(String message) {
        logAll(message, null);
    }

    /**
     * send a message to all log channel and in the console
     *
     * @param message the message to send
     * @param u       The user who triggered the action
     */
    public static void logAll(String message, @Nullable User u) {
        for (GuildSettings s : guildsSettings) {
            if (s.logChannel != null)
                s.logChannel.sendMessage(message + "\n Triggered by " + (u == null ? "the console" : (u.getAsMention() + "(" + u.getGlobalName() + ")"))).queue();
        }
        System.out.println(message + "\n Triggered by " + (u == null ? "the console" : (u.getAsMention() + "(" + u.getGlobalName() + ")")));
    }
}
