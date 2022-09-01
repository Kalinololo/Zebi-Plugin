package plugin;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import plugin.commands.Commands;
import plugin.kits.Kit;
import plugin.kits.listeners.KitMenuListener;
import plugin.kits.lists.ListKitAbilities;
import plugin.party.Lobby;
import plugin.party.listeners.CustomDeathListener;
import plugin.party.listeners.LobbyListener;
import plugin.party.listeners.RestartListener;
import plugin.party.listeners.TrackingListener;

import java.io.File;
import java.util.HashMap;
import java.util.Random;

public class HungerGames extends JavaPlugin {

    public static JavaPlugin plugin;
    public static Inventory kitMenu;
    public static Lobby party;
    public static boolean isEnded;
    private static int lobby_task_id;
    public static String map;

    @Override
    public void onEnable()
    {
        isEnded = true;
        this.getServer().getPluginManager().registerEvents(new RestartListener(), this);
        plugin = this;
        kitMenu = Kit.getKitMenu();
        ListKitAbilities.loadAbilities();
        new Commands().start();
        init_lobby();
    }

    @Override
    public void onDisable()
    {}

    private void loadListeners(){
        this.getServer().getPluginManager().registerEvents(new KitMenuListener(), this);
        this.getServer().getPluginManager().registerEvents(new CustomDeathListener(), this);
        this.getServer().getPluginManager().registerEvents(new LobbyListener(), this);
        this.getServer().getPluginManager().registerEvents(new TrackingListener(), this);
    }

    public void init_lobby()
    {
        map = new Random().ints('a', 'z' + 1).limit(10).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        plugin.getServer().createWorld(new WorldCreator(map));
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
        isEnded = true;
        kick_all();
        getServer().getScheduler().cancelTask(lobby_task_id);
        getServer().getScheduler().cancelAllTasks();
        changeworld();
        getServer().reload();
    }

    private void changeworld()
    {
        File world = new File("/home/celian/loan/" + map);
        getServer().unloadWorld(map, false);
        deleteFile(world);
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