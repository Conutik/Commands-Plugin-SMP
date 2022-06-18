package me.conutik.somecommandssmp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Config {
    private SomeCommandsSmp main;
    private File folder;
    private File dataFolder;
    private FileConfiguration config;
    private File configFile;

    public void setMain(SomeCommandsSmp main) {
        this.main = main;
        this.folder = new File(this.main.getDataFolder(), "player-data" + File.separator);
        this.folder.mkdirs();
        this.dataFolder = this.main.getDataFolder();
    }

    public void createPlayerData(Player p) {
        this.configFile = new File(folder, p.getUniqueId() + ".yml");
        if (!this.dataFolder.exists()) this.dataFolder.mkdir();
        if (!this.configFile.exists()) {
            try {
                this.configFile.createNewFile();
            } catch(Exception e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error creating " + this.configFile.getName() + "!");
            }
        }
        this.config = YamlConfiguration.loadConfiguration(this.configFile);
    }

    public void load(Player p) {
        this.configFile = new File(this.dataFolder, "player-data" + File.separator + p.getUniqueId() + ".yml");
        this.config = YamlConfiguration.loadConfiguration(this.configFile);
    }

    public void save() {
        try {
            this.config.save(this.configFile);
        } catch(Exception e) {
            Bukkit.broadcast(ChatColor.RED + "Error saving " + this.configFile.getName() + "!", "ChatColor.ErrorMsgs");
        }
    }

    public File getFolder() { return this.folder; }

    public File getFile() { return this.configFile; }

    public FileConfiguration getConfig() { return this.config; }
}
