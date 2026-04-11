package plugin;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
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

import java.io.File;
import java.util.HashMap;

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
        isEnded = true;
        changeworld();
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
        isEnded = false;
    }

    public void kick_all()
    {
        for (Player p: this.getServer().getOnlinePlayers())
        {
            p.teleport(getServer().getWorld("world").getSpawnLocation());
        }
    }

    public void stopServer()
    {
        kick_all();
        changeworld();
        init_lobby();
    }

    private void changeworld()
    {
        File world = new File(plugin.getServer().getWorldContainer().getPath() + "/" + "useless");

        if (world.exists())
        {
            getServer().unloadWorld("useless", false);
            deleteFile(world);
        }
        plugin.getServer().createWorld(new WorldCreator("useless"));
    }

    private void deleteFile(File file){
        if(file.isFile()) {
            file.delete();
        }else{
            for (File f : file.listFiles()){
                deleteFile(f);
            }
            file.delete();
        }
    }
}