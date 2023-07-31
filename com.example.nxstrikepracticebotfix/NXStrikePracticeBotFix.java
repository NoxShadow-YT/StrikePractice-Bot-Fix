import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import net.strikepractice.api.StrikePractice;
import net.strikepractice.api.bukkit.StrikePracticeAPI;

public class NXStrikePracticeBotFix extends JavaPlugin implements Listener {
    private StrikePracticeAPI spAPI;
    private FileConfiguration config;

    @Override
    public void onEnable() {
        // Initialize StrikePracticeAPI
        spAPI = StrikePractice.getInstance().getAPI();

        // Register listener
        Bukkit.getPluginManager().registerEvents(this, this);

        // Generate config.yml file
        generateConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("nxpracticebotfix")) {
            // Reload command
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("nxpracticebotfix.reload")) {
                    // Reload config.yml file
                    reloadConfig();
                    sender.sendMessage("Config reloaded.");
                } else {
                    sender.sendMessage("You do not have permission to use this command.");
                }
            } else {
                // Show plugin description
                sender.sendMessage("NXStrikePracticeBotFix plugin");
                sender.sendMessage("Fixes the issue where players lose to bots on StrikePractice servers.");
                sender.sendMessage("Use '/nxpracticebotfix reload' to reload the config.");
            }
        }

        return true;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = victim.getKiller();

        if (killer != null && spAPI.isBot(killer)) {
            // If killer is a bot, make the victim lose
            spAPI.getArenaPvPManager().getArenaPvPPlayer(victim).getLose();
        }
    }

    private void generateConfig() {
        File configFile = new File(getDataFolder(), "config.yml");

        // If config.yml file does not exist, create it and set default values
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        // Load config.yml file
        config = new YamlConfiguration();
        try {
            config.load(configFile);
        } catch (IOException e) {
            getLogger().log(Level.SEVERE, "Failed to load config.yml file.", e);
        }
    }

    private void reloadConfig() {
        // Reload config.yml file
        File configFile = new File(getDataFolder(), "config.yml");
        if (configFile.exists()) {
            config = YamlConfiguration.loadConfiguration(configFile);
        }
    }
}
