package me.pedro2091.banplugin.sql;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import java.sql.*;

public class SqlConnection {

    static Connection connection;

    public static void connection() {
        try {
            String host = "localhost";
            String port = "3306";
            String database = "plugin";
            String user = "root";
            String password = "";
            String url = "jdbc:mysql://" + host + ":" + port + "/" + database;

            Bukkit.getServer().getLogger().info(ChatColor.GREEN + "FUNCIONOU!");
            connection = DriverManager.getConnection(url, user, password);

        }catch (Exception exception ){
            Bukkit.getServer().getLogger().warning("§c[DATA BASE] CONNECTION ERROR:" + exception);
        }
    }

    public static void SqlClose(){
        try {
            connection.close();
        } catch (SQLException exception) {
            Bukkit.getServer().getLogger().warning("§c[DATA BASE] CLOSE CONNECTION ERROR:" + exception);
        }
    }

    public static PreparedStatement startAQuery(String sql){
        try {
            assert connection != null;
            return connection.prepareStatement(sql);

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

}
