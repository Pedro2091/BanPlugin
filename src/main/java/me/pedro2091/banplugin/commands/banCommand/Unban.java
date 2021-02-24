package me.pedro2091.banplugin.commands.banCommand;

import me.pedro2091.banplugin.sql.BanTable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Unban implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //if nick was insert
        if(args.length > 0){
            String nick = args[0];
            String playerBanned = BanTable.searchPlayers(nick);

            //player not banned
            if(playerBanned == null) {
                sender.sendMessage("§cThis player is not banned");

            }else{
                //if banned, remove
                BanTable.removePlayer(nick);
            }

        }else{
            //if args don't found
            sender.sendMessage("§cPlease insert the player");
            sender.sendMessage("§cUse /ban <nick>");
        }

        return true;
    }
}
