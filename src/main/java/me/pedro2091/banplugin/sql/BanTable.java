package me.pedro2091.banplugin.sql;


import org.bukkit.Bukkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BanTable {

    public static void createTable(){
        try(PreparedStatement statement = SqlConnection.startAQuery("CREATE TABLE IF NOT EXISTS banned_players(UUID varchar(36), nick varchar(16), banned_by varchar(16), ban_reason varchar(150));")){
            assert statement != null;
            statement.executeUpdate();

            Bukkit.getServer().getLogger().info("§a[DATA BASE] BANNED PLAYER TABLE STARTED");
        }catch (SQLException exception){
            Bukkit.getServer().getLogger().warning("§c[DATA BASE] BANNED PLAYER TABLE ERROR:" + exception);
        }
    }

    public static void insertPlayer(String uuid, String nick, String bannedBy, String banReason){
        try (PreparedStatement statement = SqlConnection.startAQuery("INSERT INTO banned_players(UUID, nick, banned_by, ban_reason) VALUES (?, ?, ?, ?);")){
            assert statement != null;
            statement.setString(1, String.valueOf(uuid));
            statement.setString(2, nick);
            statement.setString(3, bannedBy);
            statement.setString(4, banReason);

            statement.executeUpdate();

            Bukkit.getLogger().info("§a[DATA BASE] PLAYER BANNED");

        } catch (SQLException exception) {
            Bukkit.getServer().getLogger().warning("§c[DATA BASE] BAN ERROR:" + exception);
        }
    }

    public static void removePlayer(String nick){
        try(PreparedStatement statement = SqlConnection.startAQuery("DELETE FROM banned_players WHERE nick = ?;")) {
            assert statement != null;
            statement.setString(1,nick);
            statement.executeUpdate();

            Bukkit.getServer().getLogger().info("[DATA BASE] PLAYER UNBANNED");

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static String searchPlayers(String nick){
        try (PreparedStatement statement = SqlConnection.startAQuery("SELECT * FROM banned_players WHERE nick = ?;")){
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

    public static String searchPlayersReason(String nick){
        try (PreparedStatement statement = SqlConnection.startAQuery("SELECT * FROM banned_players WHERE nick = ?;")){
            assert statement != null;
            statement.setString(1,nick);
            ResultSet resultQuery = statement.executeQuery();

            if(resultQuery.next()) {
                return resultQuery.getString("ban_reason");
            }

        } catch (SQLException exception) {
            Bukkit.getServer().getLogger().warning("§c[DATA BASE] SEARCH ERROR:" + exception);
        }
        return null;
    }
}
