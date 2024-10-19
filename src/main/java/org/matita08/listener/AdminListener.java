package org.matita08.listener;

import net.dv8tion.jda.api.events.interaction.command.*;
import net.dv8tion.jda.api.events.session.*;
import net.dv8tion.jda.api.hooks.*;
import org.jetbrains.annotations.*;
import org.matita08.*;

import static org.matita08.listener.AdminSlashCommands.*;
import static org.matita08.tools.Utilities.*;

public class AdminListener extends ListenerAdapter {
    public AdminListener() {
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        switch (event.getName().toLowerCase()) {
            case "save" -> save(event);
            case "kill" -> kill(event);
            case "emergencyswitch" -> emergency(event);
        }
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        loadAdminCommands(discordBot.getBot().getShardManager().getGuildById(1297243956681113601L));
    }
}
