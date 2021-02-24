package me.pedro2091.banplugin.sql;

import org.bukkit.Bukkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MuteTable {

    public static void createTable(){
        try(PreparedStatement statement = SqlConnection.startAQuery("CREATE TABLE IF NOT EXISTS muted_players(UUID varchar(36), nick varchar(16),muted_by varchar(16), unmute_date varchar(25));")) {
            assert statement != null;
            statement.executeUpdate();
            Bukkit.getServer().getLogger().info("§a[DATA BASE] MUTED PLAYERS TABLE STARTED");

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void insertPlayer(String uuid, String nick, String mutedBy, String unmuteDate){
        try (PreparedStatement statement = SqlConnection.startAQuery("INSERT INTO muted_players(UUID, nick, muted_by, unmute_date) VALUES (?, ?, ?, ?);")){
            assert statement != null;
            statement.setString(1, String.valueOf(uuid));
            statement.setString(2, nick);
            statement.setString(3, mutedBy);
            statement.setString(4, unmuteDate);
            Bukkit.getServer().getLogger().info("§cTESTE");


            statement.executeUpdate();

            Bukkit.getLogger().info("§a[DATA BASE] PLAYER MUTED");

        } catch (SQLException exception) {
            Bukkit.getServer().getLogger().warning("§c[DATA BASE] MUTE ERROR:" + exception);
        }
    }

    public static void removePlayer(String nick){
        try(PreparedStatement statement = SqlConnection.startAQuery("DELETE FROM muted_players WHERE nick = ?;")) {
            assert statement != null;
            statement.setString(1,nick);
            statement.executeUpdate();

            Bukkit.getServer().getLogger().info("[DATA BASE] PLAYER UNMUTED");

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static String searchPlayers(String nick){
        try (PreparedStatement statement = SqlConnection.startAQuery("SELECT * FROM muted_players WHERE nick = ?;")){
            assert statement != null;
            statement.setString(1,nick);
            ResultSet resultQuery = statement.executeQuery();

            if(resultQuery.next()) {
                return resultQuery.getString("nick");
            }

        } catch (SQLException exception) {
            Bukkit.getServer().getLogger().warning("§c[DATA BASE] SEARCH ERROR:" + exception);
        }
        return null;
    }

    public static String searchPlayerDate(String nick){
        try (PreparedStatement statement = SqlConnection.startAQuery("SELECT * FROM muted_players WHERE nick = ?;")){
            assert statement != null;
            statement.setString(1,nick);
            ResultSet resultQuery = statement.executeQuery();

            if(resultQuery.next()) {
                return resultQuery.getString("unmute_date");
            }

        } catch (SQLException exception) {
            Bukkit.getServer().getLogger().warning("§c[DATA BASE] SEARCH ERROR:" + exception);
        }
        return null;
    }

}
