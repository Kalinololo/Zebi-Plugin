package plugin;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class HungerGames extends JavaPlugin {

    public static JavaPlugin plugin;

    public static File kitFile;

    public static HashMap<UUID, PermissionAttachment> perms = new HashMap<>();

    @Override
    public void onEnable() {
        kitFile = new File(getDataFolder(), "kits.yml");
        if(!kitFile.exists()){
            saveResource("kits.yml", true);
        }
        plugin = this;

        getServer().getPluginManager().registerEvents(new Swap(), this);

        this.getCommand("kit").setExecutor(new CommandKit());
        this.getCommand("surfacerandom").setExecutor(new Commands());
        this.getCommand("ez").setExecutor(new Commands());
        this.getCommand("fiou").setExecutor(new Commands());
    }

    @Override
    public void onDisable() {
        getLogger().info("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }

}