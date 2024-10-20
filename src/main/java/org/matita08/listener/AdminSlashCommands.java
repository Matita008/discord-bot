package org.matita08.listener;

import net.dv8tion.jda.api.events.interaction.command.*;

import static org.matita08.tools.Utilities.*;

public class AdminSlashCommands {
    public static long time;
    public static boolean confirm = false;
    public static int timeout = 60 * 1000;

    public static void save(SlashCommandInteractionEvent event) {//TODO please implement me ;(
        unimplemented(event);
    }

    public static void kill(SlashCommandInteractionEvent event) {//TODO please implement me ;(
        unimplemented(event);
    }

    public static void emergency(SlashCommandInteractionEvent event) {//TODO please implement me ;(
        //unimplemented(event);
        if (!confirm) {
            time = System.currentTimeMillis();
            confirm = true;
            event.reply("Reenter the command to confirm in " + (timeout >= 60000 ? timeout / (60 * 1000) + " minute" + (timeout / (60 * 1000) == 1 ? "" : "s") : timeout / 1000 + " second") + (timeout / 1000 == 1 ? "" : "s")).setEphemeral(true).queue();
            return;
        }
        if (System.currentTimeMillis() >= time + timeout) {
            time = System.currentTimeMillis();
            return;
        }
        logAll("ATTENTION!!!\nThe bot  is shutting down", event.getUser());
        event.reply("Shutting down...").setEphemeral(true).queue();
        System.exit(202);
    }

    private static void unimplemented(SlashCommandInteractionEvent e) {
        e.reply("This admin command isn't implemented :(\n try again tomorrow").setEphemeral(true).queue();
    }
}
