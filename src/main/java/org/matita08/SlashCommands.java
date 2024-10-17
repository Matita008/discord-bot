package org.matita08;

import net.dv8tion.jda.api.events.interaction.command.*;

public class SlashCommands {
    public static void hello(SlashCommandInteractionEvent event) {
        event.reply("Hello " + event.getUser().getAsMention() + "!") // What will we reply with?
                .setEphemeral(true) // Do we want the message hidden so only the user who ran the command can see it?
                .queue(); // Queue the reply.

    }

    public static void ping(SlashCommandInteractionEvent event) {
        if (event.getOption("type") == null || event.getOption("type").getAsBoolean())
            event.reply("pong!\nmy ping is: " + event.getJDA().getGatewayPing()+"ms").queue();
        else {
            long time = System.currentTimeMillis();
            event.reply("Pong!").flatMap(v ->
                    event.getHook().editOriginalFormat("Pong: %d ms", System.currentTimeMillis() - time) // then edit original
            ).queue(); // Queue both reply and edit
        }
    }
}
