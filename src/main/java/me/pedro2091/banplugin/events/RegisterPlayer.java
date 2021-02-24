package me.pedro2091.banplugin.events;

import me.pedro2091.banplugin.BanPlugin;
import me.pedro2091.banplugin.sql.PlayersTable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import java.util.UUID;

public class RegisterPlayer implements Listener {
    BanPlugin plugin;

    public RegisterPlayer(BanPlugin _plugin) {
        plugin = _plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        String nick = player.getName();

        //if player in unregistered
        if(PlayersTable.searchPlayers(uuid) == null) {
            //register Player
            PlayersTable.insertOnPlayerTable(uuid, nick);
            Bukkit.getServer().getLogger().info("§a[DATA BASE] PLAYER REGISTERED");
        }

    }


}
