package plugin;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

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
    public static boolean isEnded;

    @Override
    public void onEnable()
    {
        isEnded = true;
        plugin = this;
        kitMenu = Kit.getKitMenu();
        ListKitAbilities.loadAbilities();
        new Commands().start();
        init_lobby();

        World world = Bukkit.getWorld("world");

        if (world != null) {
            int radius = 5;

            Chunk center = world.getSpawnLocation().getChunk();
            int cx = center.getX();
            int cz = center.getZ();

            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    world.setChunkForceLoaded(cx + x, cz + z, true);
                }
            }

            Bukkit.getLogger().info("Spawn area force-loaded.");
        }
        isEnded = false;
    }

    @Override
    public void onDisable()
    {
        //Bukkit.getScheduler().cancelAllTasks();
    }

    private void loadListeners(){
        this.getServer().getPluginManager().registerEvents(new KitMenuListener(), this);
        this.getServer().getPluginManager().registerEvents(new CustomDeathListener(), this);
        this.getServer().getPluginManager().registerEvents(new LobbyListener(), this);
        this.getServer().getPluginManager().registerEvents(new TrackingListener(), this);
    }

    public void init_lobby()
    {
        Kit.playerSelectedKit = new HashMap<>();
        party = new Lobby(this);
        party.start();
        loadListeners();
    }

    public void kick_all()
    {
        for (Player p: this.getServer().getOnlinePlayers())
        {
            p.kickPlayer("Server is restarting...");
        }
    }

    public void stopServer()
    {
        kick_all();
        Bukkit.spigot().restart();
    }
}