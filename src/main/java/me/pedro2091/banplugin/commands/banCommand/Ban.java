package me.pedro2091.banplugin.commands.banCommand;

import me.pedro2091.banplugin.sql.BanTable;
import me.pedro2091.banplugin.sql.PlayersTable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import java.util.Arrays;


public class Ban implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length > 0) {

            String playerBannedNick = args[0];
            String playerBannedUUID;

            String bannedBy = sender.getName();
            String banReason = "No specified reason";

            //player search
            playerBannedUUID = PlayersTable.searchPlayersUUID(playerBannedNick);

            //search player in banTable
            String playerAlreadyBanned = BanTable.searchPlayers(playerBannedNick);

            //if player aren't banned
            if (playerAlreadyBanned == null) {

                //if player found
                if (playerBannedUUID != null) {

                    //if player aren't banned


                    //if args exists, if don't the default is "No specified reason"
                    if (args.length > 1) {

                        banReason = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                    }

                    //insert player on ban table
                    BanTable.insertPlayer(playerBannedUUID, playerBannedNick, bannedBy, banReason);

                } else {
                    //if player don't found
                    sender.sendMessage("§cPlayer not found");
                }

            }else{
                //if player is already banned
                sender.sendMessage("§cThis player is already banned");
            }


        }else{
            //if player not specified
            sender.sendMessage("§cPlease insert the player");
            sender.sendMessage("§cUse /ban <nick> <reason>");
        }

        return true;
    }
}


