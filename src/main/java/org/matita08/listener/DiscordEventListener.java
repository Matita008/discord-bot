package org.matita08.listener;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.guild.*;
import net.dv8tion.jda.api.events.guild.member.*;
import net.dv8tion.jda.api.events.interaction.command.*;
import net.dv8tion.jda.api.events.session.*;
import net.dv8tion.jda.api.hooks.*;
import org.jetbrains.annotations.*;
import org.matita08.*;
import org.matita08.tools.*;

import static org.matita08.listener.Events.*;
import static org.matita08.listener.SlashCommands.*;
import static org.matita08.tools.Utilities.*;

public class DiscordEventListener extends ListenerAdapter {
    discordBot bot;

    public DiscordEventListener(discordBot bot) {
        this.bot = bot;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        //System.out.println("new interaction received: " + event.toString());
        switch (event.getName().toLowerCase()) {
            case "hello" -> hello(event);
            case "ping" -> ping(event);
            case "ticket" -> ticket(event);
            case "setchannel" -> setChannel(event);
            case "setrule" -> setrule(event);
            //default -> unrecognized(event);
        }
    }

    // There is a reason why we don't add the commands IMMEDIATELY after the bot starts up. The bot has to load in all the guilds it is in before it can add commands.
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.print("loading");
        Utilities.load();
        while (guildsSettings == null || guildsSettings.length == 0) System.out.print(".");
        for (Guild g : bot.getShardManager().getGuilds()) {
            loadSlashCommands(g);
            if (getGuildSettings(g) == null)
                loadBaseSettings(g);
        }
        System.out.println("\nloaded");
    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        //System.out.println("new tools received!");
        loadSlashCommands(event.getGuild());
        loadBaseSettings(event.getGuild());
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        //System.out.println("new join received!");
        join(event);
    }

    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event) {
        //System.out.println("new leave detected!");
        leave(event);
    }

    /**
     * Replay to a slash event with a message sawing it wasn't recognised
     *
     * @param event the event to reply
     */
    private void unrecognized(@NotNull SlashCommandInteractionEvent event) {
        //event.reply("the command " + event.getCommandString() + " wasn't recognized").queue();
        event.reply("the command " + event.getCommandString() + " wasn't recognized").setEphemeral(true).queue();
    }
}
