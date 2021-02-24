package me.pedro2091.banplugin.sql;


import org.bukkit.Bukkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


public class PlayersTable {


    public static void createTable(){
        try(PreparedStatement statement = SqlConnection.startAQuery("CREATE TABLE IF NOT EXISTS players(UUID varchar(36), nick varchar(16));")){
            assert statement != null;
            statement.executeUpdate();

            Bukkit.getServer().getLogger().info("§a[DATA BASE] PLAYER TABLE STARTED");
        }catch (SQLException exception){
            Bukkit.getServer().getLogger().warning("§c[DATA BASE] PLAYER TABLE ERROR:" + exception);
        }
    }

    public static void insertOnPlayerTable(UUID uuid, String nick){
        try ( PreparedStatement statement = SqlConnection.startAQuery("INSERT INTO players(UUID, nick) VALUES ('" + uuid + "', '" + nick + "');")){
            assert statement != null;
            statement.executeUpdate();

            Bukkit.getServer().getLogger().info("§a[DATA BASE] PLAYER REGISTERED");
        } catch (SQLException exception) {
            Bukkit.getServer().getLogger().warning("§c[DATA BASE] INSERT ERROR:" + exception);
        }
    }

    public static String searchPlayers(String nick){
        try (PreparedStatement statement = SqlConnection.startAQuery("SELECT * FROM players WHERE nick = ?;")){
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

    public static String searchPlayers(UUID uuid){
        try (PreparedStatement statement = SqlConnection.startAQuery("SELECT * FROM players WHERE UUID = ?;")){
            assert statement != null;
            statement.setString(1, String.valueOf(uuid));
            ResultSet resultQuery = statement.executeQuery();

            if(resultQuery.next()) {
                return resultQuery.getString("UUID");
            }

        } catch (SQLException exception) {
            Bukkit.getServer().getLogger().warning("§c[DATA BASE] SEARCH ERROR:" + exception);
        }
        return null;
    }

    public static String searchPlayersUUID(String nick){
        try (PreparedStatement statement = SqlConnection.startAQuery("SELECT * FROM players WHERE nick = ?;")){
            assert statement != null;
            statement.setString(1,nick);
            ResultSet resultQuery = statement.executeQuery();

            if(resultQuery.next()) {
                return resultQuery.getString("UUID");
            }

        } catch (SQLException exception) {
            Bukkit.getServer().getLogger().warning("§c[DATA BASE] SEARCH ERROR:" + exception);
        }
        return null;
    }


}
