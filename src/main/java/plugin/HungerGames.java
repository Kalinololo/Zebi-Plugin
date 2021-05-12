package plugin;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class HungerGames extends JavaPlugin {

    public static JavaPlugin plugin;

    public static File kitFile;

    @Override
    public void onEnable() {
        kitFile = new File(getDataFolder(), "kits.yml");
        if(!kitFile.exists()){
            saveResource("kits.yml", true);
        }
        plugin = this;

        Kit.setKitMenu();

        getServer().getPluginManager().registerEvents(new Swap(), this);

        this.getCommand("kit").setExecutor(new CommandKit());
        this.getCommand("surfacerandom").setExecutor(new Commands());
        this.getCommand("ez").setExecutor(new Commands());
        this.getCommand("fiou").setExecutor(new Commands());
        this.getCommand("cworld").setExecutor(new Commands());
        this.getCommand("dworld").setExecutor(new Commands());
        this.getCommand("gworld").setExecutor(new Commands());
        this.getCommand("lworld").setExecutor(new Commands());
    }

    @Override
    public void onDisable() {
        getLogger().info("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }

}