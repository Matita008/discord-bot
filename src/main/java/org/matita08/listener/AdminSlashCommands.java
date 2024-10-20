package org.matita08.listener;

import net.dv8tion.jda.api.events.interaction.command.*;

public class AdminSlashCommands {
    public static long time;
    public static bool confirm=false;
    public static int timeout=1*60*1000;
    public static void save(SlashCommandInteractionEvent event) {//TODO please implement me ;(
        unimplemented(event);
    }

    public static void kill(SlashCommandInteractionEvent event) {//TODO please implement me ;(
        unimplemented(event);
    }

    public static void emergency(SlashCommandInteractionEvent event) {//TODO please implement me ;(
        //unimplemented(event);
        if(!confirm){
            time=System.millis();
            confirm=true;
            return;
        }
        if(System.millis()>=time+timeout){
            confirm=false;
            return;
        }
        System.exit(1);
    }

    private static void unimplemented(SlashCommandInteractionEvent e) {
        e.reply("This admin command isn't implemented :(\n try again tomorrow").setEphemeral(true).queue();
    }
}
