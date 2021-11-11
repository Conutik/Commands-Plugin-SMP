package me.conutik.somecommandssmp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class tpa implements CommandExecutor {

    private static Main main;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {

            final Player player = (Player) sender;

            if(args.length == 0) {
                player.sendMessage(ChatColor.RED + "Please specify who you want to tp to.");
                return false;
            }

            Player tper = Bukkit.getPlayer(args[0]);
            if(tper == null) {
                player.sendMessage(ChatColor.RED + "Please specify who you want to tp to.");
                return false;
            }

            if(tper == player) {
                player.sendMessage(ChatColor.RED + "You cannot tp to yourself!");
                return false;
            }

            PlayerConfig.create(player);

            PlayerConfig.load(player);

            PlayerConfig.config.set("tp", tper.getUniqueId());

            player.sendMessage(ChatColor.GREEN + "TPA Request has been sent.");

            tper.sendMessage(ChatColor.LIGHT_PURPLE + player.getDisplayName() + " has sent a TPA request to You \nType " + ChatColor.GREEN + "/tpaccept" + ChatColor.LIGHT_PURPLE + "to accept the request.\nType " + ChatColor.GREEN + "/tpadeny" + ChatColor.LIGHT_PURPLE + " to deny the request. \n\nALL PENDING REQUESTS BEFORE THIS HAVE BEEN REMOVED.");

            PlayerConfig.save();
        }

        return true;
    }
}
