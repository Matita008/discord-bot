package org.matita08;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.requests.*;
import net.dv8tion.jda.api.sharding.*;
import org.apache.commons.cli.*;
import org.matita08.listener.*;

import javax.security.auth.login.*;

public class discordBot {
    protected static discordBot bot;
    private ShardManager shardManager = null;

    public discordBot(String token) {
        try {
            shardManager = buildShardManager(token);
        } catch (LoginException e) {
            System.out.println("Failed to start bot! Please check the console for any errors.");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
       /* String token = null;
        if (args[0].startsWith("-")) {
            int i = 0;
            while (i < args.length) {
                switch (args[i].substring(1)) {
                    case "t":
                    case "token":
                        token = args[++i];
                        break;
                    default:
                        throw new RuntimeException("Parameter not recognized\nparameter: " + args[i]);
                }
                i++;
            }
        } else if (args.length == 1) {
            token = args[0];
        }
        if (token == null) {
            System.exit(0);
        }*/
        Options options = new Options();

        options.addOption(new Option("h", "help", false, "Displays this help menu."));
        options.addOption(new Option("t", "token", true, "Provide the token during startup."));

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);

            // Check if the help argument was provided.
            if (cmd.hasOption("help")) {
                formatter.printHelp("Help Menu", options);
                System.exit(0);
            }

            // Check if the token argument was provided and has a value. If it doesn't, return null.
            String token = cmd.hasOption("token") ? cmd.getOptionValue("token") : null;
            if (token == null) {
                System.out.println("ERROR: No token provided, please provide a token using the -t or --token flag.");
                formatter.printHelp("", options);
                System.exit(0);
            }
            bot = new discordBot(token);
            //System.exit(3);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("", options);
            System.exit(0);
        }
        /*DiscordEventListener c= new DiscordEventListener(this);
        c.();*/
    }

    /**
     * @return the bot instance
     */
    public static discordBot getBot() {
        return bot;
    }

    /**
     * @param token The token of the bot
     * @throws LoginException if it fails to login
     */
    private ShardManager buildShardManager(String token) throws LoginException {
        // It is often better to load your token in from an external file or environment variable, especially if you plan on publishing the source code.
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_INVITES, GatewayIntent.GUILD_MESSAGES);
        builder.addEventListeners(new DiscordEventListener(this), new AdminListener());
        builder.setActivity(Activity.customStatus("Trying to decifer what @matita08 is saying to me"));
        return builder.build();
    }

    /**
     * @return the shard manager of the JDA
     */
    public ShardManager getShardManager() {
        return shardManager;
    }
}