package org.matita08;

import net.dv8tion.jda.api.entities.channel.*;
import net.dv8tion.jda.api.events.interaction.command.*;

import static org.matita08.Utilities.*;

public class SlashCommands {
    public static void hello(SlashCommandInteractionEvent event) {
        event.reply("Hello " + event.getUser().getAsMention() + "!") // What will we reply with?
                .setEphemeral(true) // Do we want the message hidden so only the user who ran the command can see it?
                .queue(); // Queue the reply.

    }

    public static void ping(SlashCommandInteractionEvent event) {
        if (event.getOption("type") == null || event.getOption("type").getAsBoolean())
            event.reply("pong!\nmy ping is: " + event.getJDA().getGatewayPing() + "ms").queue();
        else {
            long time = System.currentTimeMillis();
            event.reply("Pong!").flatMap(v ->
                    event.getHook().editOriginalFormat("Pong: %d ms", System.currentTimeMillis() - time) // then edit original
            ).queue(); // Queue both reply and edit
        }
    }

    public static void setChannel(SlashCommandInteractionEvent event) {
        if(!event.isFromGuild()) {
            onlyGuidAccepted(event);
            return;
        }
        Channel c = event.getOption("channel").getAsChannel();
        switch (event.getOption("option").getAsInt()) {
            case 1 -> getGuildSettings(event.getGuild()).join = c;
            case 2 -> getGuildSettings(event.getGuild()).leave = c;
        }
    }

    private static void onlyGuidAccepted(SlashCommandInteractionEvent event) {
        event.reply("the interaction has failed since this command is for guilds ony (you are in DM)").queue();
    }
}
