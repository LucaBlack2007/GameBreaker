package co.dragsim.gamebreaker;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getCommand("givegamebreaker").setExecutor(new GameBreakerCommand(this));
        getCommand("gamebreaker").setExecutor(new ConfigurationReloadCommand(this));
    }

    public ItemStack registerNewGameBreaker(Player sender, Player reciever) {
        List<String> lore = new ArrayList<>();

        ItemStack stack = new ItemStack(Material.TNT);
        ItemMeta meta = stack.getItemMeta();

        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Game Breaker");

        lore.add(ChatColor.GRAY + "This item was givin to a player");
        lore.add(ChatColor.GRAY + "who reported a game breaking");
        lore.add(ChatColor.GRAY + "exploit. What a guy!");
        lore.add(ChatColor.GREEN + " ");
        lore.add(ChatColor.GRAY + "From: " + sender.getDisplayName());
        lore.add(ChatColor.GRAY + "To: " + reciever.getDisplayName());
        lore.add(ChatColor.RED + " ");
        lore.add(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "MYTHIC ITEM");

        meta.setLore(lore);

        stack.setItemMeta(meta);

        return stack;
    }
}
