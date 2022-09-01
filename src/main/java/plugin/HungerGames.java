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
    private static int lobby_task_id;

    @Override
    public void onEnable()
    {
        isEnded = true;
        plugin = this;
        if (!(new File("/home/celian/loan/useless").exists()))
            plugin.getServer().createWorld(new WorldCreator("useless"));
        kitMenu = Kit.getKitMenu();
        ListKitAbilities.loadAbilities();
        new Commands().start();
        init_lobby();
    }

    @Override
    public void onDisable()
    {
        Bukkit.getScheduler().cancelAllTasks();
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
        lobby_task_id = Bukkit.getScheduler().runTaskAsynchronously(this, party).getTaskId();
        loadListeners();
        isEnded = false;
    }

    public void kick_all()
    {
        for (Player p: this.getServer().getOnlinePlayers())
        {
            p.kickPlayer("Server restarting !");
        }
    }

    public void stopServer()
    {
        kick_all();
        getServer().getScheduler().cancelTask(lobby_task_id);
        getServer().getScheduler().cancelAllTasks();
        changeworld();
    }

    private void changeworld()
    {
        File world = new File("/home/celian/loan/useless");
        getServer().unloadWorld("useless", false);
        deleteFile(world);
        Bukkit.reload();
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