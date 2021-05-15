package plugin;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.io.BukkitObjectInputStream;
import plugin.commands.Commands;
import plugin.kits.Kit;
import plugin.kits.listeners.KitMenuListener;
import plugin.kits.lists.ListKitAbilities;
import plugin.party.Lobby;
import plugin.party.listeners.CustomDeathListener;
import plugin.party.listeners.LobbyListener;
import plugin.party.listeners.TrackingListener;

public class HungerGames extends JavaPlugin {

    public static JavaPlugin plugin;

    public static Inventory kitMenu;

    public static Lobby party;

    @Override
    public void onEnable() {
        plugin = this;
        kitMenu = Kit.getKitMenu();
        party = new Lobby();

        ListKitAbilities.loadAbilities();
        loadListeners();

        Bukkit.getScheduler().cancelAllTasks();


        new Commands().start();

    }

    @Override
    public void onDisable() {
        party.end();
    }

    private void loadListeners(){
        plugin.getServer().getPluginManager().registerEvents(new KitMenuListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new CustomDeathListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new LobbyListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new TrackingListener(), plugin);
    }






}