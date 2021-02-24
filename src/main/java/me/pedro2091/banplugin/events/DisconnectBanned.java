package me.pedro2091.banplugin.events;

import me.pedro2091.banplugin.sql.BanTable;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;


public class DisconnectBanned implements Listener {
    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent event){
        String nick = event.getName();

        String playerBanned = BanTable.searchPlayers(nick);
        String reason = BanTable.searchPlayersReason(nick);

        //if player is on BanTable disallow him to connect
        if(playerBanned != null){
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, "You are banned in this server because: "+reason);
            Bukkit.getServer().getLogger().info("[DATA BASE] PLAYER BANNED TRY TO JOIN");
        }else{
            //if player is not on BanTable allow him to connect
            event.allow();
        }

    }

}
