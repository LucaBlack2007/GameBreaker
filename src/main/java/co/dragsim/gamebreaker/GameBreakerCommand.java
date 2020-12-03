package co.dragsim.gamebreaker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameBreakerCommand implements CommandExecutor {

    private Main main;

    public GameBreakerCommand(Main main) { this.main = main; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission(main.getConfig().getString("gamebreaker.gamebreaker-command-permission"))) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 1) {
                    if (Bukkit.getPlayerExact(args[0]).isOnline()) {
                        Player argPlayer = Bukkit.getPlayerExact(args[0]);
                        argPlayer.getInventory().addItem(main.registerNewGameBreaker(player, argPlayer));
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (p.getUniqueId() == player.getUniqueId() || p.getUniqueId() == argPlayer.getUniqueId())
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("gamebreaker.gamebreaker-message").replaceAll("<SENDER>", player.getDisplayName()).replaceAll("<RECIEVER>", argPlayer.getDisplayName())));
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Player not found.");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid argument length!");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Hey! This command can only be run by a player.");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "No permission!");
        }

        return false;
    }
}
