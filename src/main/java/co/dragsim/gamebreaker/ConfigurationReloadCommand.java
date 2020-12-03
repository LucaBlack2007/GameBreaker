package co.dragsim.gamebreaker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConfigurationReloadCommand implements CommandExecutor {

    private Main main;

    public ConfigurationReloadCommand(Main main) {
        this.main = main;
    }

    protected String prefix;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        prefix = ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "GameBreaker " + ChatColor.DARK_GRAY + "| " + ChatColor.GRAY.toString() + ChatColor.ITALIC;

        if (sender.hasPermission(main.getConfig().getString("gamebreaker.configuration-reload-permission"))) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("refresh")) {
                    sender.sendMessage(prefix + "Reloading Configuration...");
                    Bukkit.getScheduler().runTaskLater(main, new Runnable() {
                        @Override
                        public void run() {
                            main.reloadConfig();
                            sender.sendMessage(prefix + "Reloaded!");
                        }
                    }, 15);
                } else {
                    sender.sendMessage(ChatColor.RED + "Please use /gb [reload|refresh]");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid argument length.");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "No permission.");
        }

        return false;
    }
}
