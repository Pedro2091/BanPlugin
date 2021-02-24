package me.pedro2091.banplugin.events;

import me.pedro2091.banplugin.sql.MuteTable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.time.LocalDateTime;

public class MutedInChat implements Listener {

    @EventHandler
    public void OnChatMessage(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String nick = player.getName();

        //Check if player is on MuteTable
        String isMuted = MuteTable.searchPlayers(nick);

        try {
            //get the unmute date in String
            String unmuteString = MuteTable.searchPlayerDate(nick);

            //if the unmute date is a valid date
            assert unmuteString != null;
            if (!unmuteString.equals("NEVER")) {
                //transfer the String date to LocalDateTime format
                LocalDateTime unmuteDate = LocalDateTime.parse(unmuteString);

                //if is time to unmute the player
                if (unmuteDate.isBefore(LocalDateTime.now())) {
                    MuteTable.removePlayer(nick);
                }

            }

            //if player is muted
            if (isMuted != null) {
                //cancel the message
                event.setCancelled(true);
                player.sendMessage("§cYou are muted in the chat");
                player.sendMessage("§cYou are be demoted in: " + unmuteString.replace("T", " "));
            }

        //ignore if don't find a unmuted Date
        }catch (NullPointerException ignore){}

    }

}
