package me.conutik.somecommandssmp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    PluginDescriptionFile info = this.getDescription();

    @Override
    public void onEnable() {
        PlayerConfig.setMain(this);
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "SomeCommandsSMP v" + info + " is now Online");
        getCommand("tpa").setExecutor(new tpa());
    }
}
