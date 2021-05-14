package plugin;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.commands.Commands;
import plugin.kits.Kit;
import plugin.kits.lists.ListKitAbilities;

public class HungerGames extends JavaPlugin {

    public static JavaPlugin plugin;

    public static Inventory kitMenu;

    @Override
    public void onEnable() {
        plugin = this;
        kitMenu = Kit.getKitMenu();

        //getServer().getPluginManager().registerEvents(new EventManager(), this);

        ListKitAbilities.loadAbilities();
        new Commands().start();

    }

    @Override
    public void onDisable() {
        getLogger().info("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }






}