package me.pedro2091.banplugin;

import me.pedro2091.banplugin.commands.MuteCommand;
import me.pedro2091.banplugin.commands.banCommand.Ban;
import me.pedro2091.banplugin.commands.banCommand.Unban;
import me.pedro2091.banplugin.events.DisconnectBanned;
import me.pedro2091.banplugin.events.MutedInChat;
import me.pedro2091.banplugin.events.RegisterPlayer;
import me.pedro2091.banplugin.sql.BanTable;
import me.pedro2091.banplugin.sql.MuteTable;
import me.pedro2091.banplugin.sql.PlayersTable;
import me.pedro2091.banplugin.sql.SqlConnection;
import org.bukkit.plugin.java.JavaPlugin;


public final class BanPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        sqlOpenConnection();
        registerCommand();
        registerEvents();
    }

    @Override
    public void onDisable() {
        SqlConnection.SqlClose();
    }



    public void sqlOpenConnection(){
        SqlConnection.connection();
        PlayersTable.createTable();
        BanTable.createTable();
        MuteTable.createTable();
    }

    public void registerCommand(){
        getCommand("Pban").setExecutor(new Ban());
        getCommand("Punban").setExecutor(new Unban());
        getCommand("Ptempmute").setExecutor(new MuteCommand());
    }

    public void registerEvents(){
        getServer().getPluginManager().registerEvents(new RegisterPlayer(this),this);
        getServer().getPluginManager().registerEvents(new DisconnectBanned(),this);
        getServer().getPluginManager().registerEvents(new MutedInChat(),this);
    }

}
