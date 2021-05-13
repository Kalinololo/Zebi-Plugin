package plugin;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.commands.CommandKit;
import plugin.commands.Commands;

import java.io.File;

public class HungerGames extends JavaPlugin {

    public static JavaPlugin plugin;

    public static File kitFile;

    @Override
    public void onEnable() {
        kitFile = new File(getDataFolder(), "kits.yml");
        //if(!kitFile.exists()){
            saveResource("kits.yml", true);
        //}
        plugin = this;

        getServer().getPluginManager().registerEvents(new Swap(), this);

        new Commands().start();

    }

    @Override
    public void onDisable() {
        getLogger().info("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }

}