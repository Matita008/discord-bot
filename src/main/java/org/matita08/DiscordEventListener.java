package org.matita08;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.guild.*;
import net.dv8tion.jda.api.events.guild.member.*;
import net.dv8tion.jda.api.events.interaction.command.*;
import net.dv8tion.jda.api.events.session.*;
import net.dv8tion.jda.api.hooks.*;
import org.jetbrains.annotations.*;

import static org.matita08.Events.*;
import static org.matita08.SlashCommands.*;
import static org.matita08.Utilities.*;

public class DiscordEventListener extends ListenerAdapter {
    discordBot bot;

    public DiscordEventListener(discordBot bot) {
        this.bot = bot;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        switch (event.getName().toLowerCase()) {
            case "hello" -> hello(event);
            case "ping" -> ping(event);
            case "setchannel" -> setChannel(event);
        }
    }

    // There is a reason why we don't add the commands IMMEDIENTLY after the bot starts up. The bot has to load in all the guilds it is in before it can add commands.
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        for (Guild g : bot.getShardManager().getGuilds()) {
            loadSlashCommands(g);
            loadBaseSettings(g);
        }
    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        loadSlashCommands(event.getGuild());
        loadBaseSettings(event.getGuild());
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        join(event);
    }
}
