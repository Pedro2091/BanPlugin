package me.pedro2091.banplugin.commands;

import me.pedro2091.banplugin.TimeFormat;
import me.pedro2091.banplugin.sql.MuteTable;
import me.pedro2091.banplugin.sql.PlayersTable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.time.LocalDateTime;


public class MuteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length > 0){

            String unmuteDate;
            String playerMutedNick = args[0];
            String mutedBy = sender.getName();

            String playerMutedUUID = PlayersTable.searchPlayersUUID(playerMutedNick);
            String playerAlreadyMuted = MuteTable.searchPlayers(playerMutedNick);


            //if player exists
            if(playerMutedUUID != null) {

                //if player are not muted
                if(playerAlreadyMuted == null) {

                    //if time not insert
                    if(args.length == 1) {
                        unmuteDate = "NEVER";
                        //perm mute
                        MuteTable.insertPlayer(playerMutedUUID, playerMutedNick, mutedBy, unmuteDate);
                    }

                    //if time are insert
                    if(args.length > 1) {
                        try {
                            //insert the date the player will be unmuted using TimeFormat
                            unmuteDate = TimeFormat.format(args);

                            //tempmute player
                            MuteTable.insertPlayer(playerMutedUUID, playerMutedNick, mutedBy, unmuteDate);

                        }catch(NumberFormatException exception){
                            sender.sendMessage("§cInvalid time format\n§cPlease use h,m or s.");
                        }

                    }

                }else if(args.length == 1) {
                        //if player are muted and time don't insert
                        //unmute
                        MuteTable.removePlayer(playerMutedNick);
                        sender.sendMessage("§aPlayer is not muted anymore");

                }else {
                    //if player is already muted
                    sender.sendMessage("§cThis player is already muted");
                }

            }else {
                //if player don't found
                sender.sendMessage("§cPlayer not found");
            }

        }else{
            //if player not specified
            sender.sendMessage("§cPlease insert the player");
            sender.sendMessage("§cUse /tempmute <nick> <reason>");
        }

        return true;
    }
}

/*
//          /tempMute Pedro2091 2h

            hoje
            CURRENT_TIMESTAMP()

            quando o cara o cara vai ser desbanido
            LocalDateTime.now() + 2 HOUR;

            remove da lista quando

            [CURRENT_TIMESTAMP();] >= [SELECT CURRENT_TIMESTAMP() + 2 HOUR;]

            toda vez que o cara for falar no chat verifica se ele ta banido antes da data

*/
