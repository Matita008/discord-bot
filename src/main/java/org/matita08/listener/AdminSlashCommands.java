package org.matita08.listener;

import net.dv8tion.jda.api.events.interaction.command.*;

public class AdminSlashCommands {
    public static void save(SlashCommandInteractionEvent event) {//TODO please implement me ;(
        unimplemented(event);
    }

    public static void kill(SlashCommandInteractionEvent event) {//TODO please implement me ;(
        unimplemented(event);
    }

    public static void emergency(SlashCommandInteractionEvent event) {//TODO please implement me ;(
        unimplemented(event);
    }

    private static void unimplemented(SlashCommandInteractionEvent e) {
        e.reply("This admin command isn't implemented :(\n try again tomorrow").setEphemeral(true).queue();
    }
}
