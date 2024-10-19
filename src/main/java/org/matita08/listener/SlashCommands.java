package org.matita08.listener;

import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.channel.*;
import net.dv8tion.jda.api.entities.channel.concrete.*;
import net.dv8tion.jda.api.entities.emoji.*;
import net.dv8tion.jda.api.events.interaction.command.*;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.selections.*;

import java.awt.*;

import static org.matita08.tools.Utilities.*;

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
            event.reply("Pong!").flatMap(v -> event.getHook().editOriginalFormat("Pong: %d ms", System.currentTimeMillis() - time) // then edit original
            ).queue(); // Queue both reply and edit
        }
    }

    public static void setChannel(SlashCommandInteractionEvent event) {
        if (onlyGuild(event)) return;
        event.reply("elaborating....").setEphemeral(true).queue();
        Channel c = event.getOption("channel").getAsChannel();
        switch (event.getOption("type").getAsString()) {
            case "join" -> getGuildSettings(event.getGuild()).join = (TextChannel) c;
            case "leave" -> getGuildSettings(event.getGuild()).leave = (TextChannel) c;
            case "log" -> getGuildSettings(event.getGuild()).logChannel = (TextChannel) c;
        }
        log((TextChannel) event.getChannel(), event.getGuild(), event.getOption("type").getAsString() + " channel was just set to " + c.getAsMention() + " by " + event.getUser().getAsMention());
    }

    public static void ticket(SlashCommandInteractionEvent event) {
        TextChannel c = event.getOption("textchannel") == null ? (TextChannel) event.getChannel() : (TextChannel) event.getOption("textchannel").getAsChannel();
        if (!c.canTalk()) {
            event.reply("i can't send messages/embed").setEphemeral(true).queue();
            return;
        }
        EmbedBuilder e = new EmbedBuilder();
        e.setColor(Color.CYAN);
        e.setTitle("Open a new ticket");
        e.setDescription("Seleziona la categoria");
        event.reply("sending embed in " + c.getAsMention()).queue();
        c.sendMessageEmbeds(e.build()).addActionRow(event.getOption("type").getAsBoolean() ? StringSelectMenu.create("new-ticket-type").addOption("Discord", "Discord", "Report a issue of the discord", Emoji.fromFormatted(":discord:1296934945956171857")).build() : Button.primary("Discord", "Discord"));
    }

    public static void setrule(SlashCommandInteractionEvent event) {//TODO implement rules
        if (onlyGuild(event)) return;
        event.reply("elaborating....").setEphemeral(true).queue();

    }

    /**
     * Reply to an event specifying it's only for guilds
     *
     * @param event the event to reply if it isn't from a guild
     * @return false if it is from a guild, true overwise
     */
    private static boolean onlyGuild(GenericCommandInteractionEvent event) {
        if (event.isFromGuild() || event.getGuild() != null) return false;
        event.reply("the interaction has failed since this command is for guilds ony (you are in DM)").queue();
        return true;
    }
}