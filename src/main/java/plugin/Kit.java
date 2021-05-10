package plugin;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.PermissionAttachment;
import java.util.*;


public class Kit{

    private String kit;
    private Player player;

    private static HashMap<Player, String> playerSelectedKit = new HashMap<>();

    private static final FileConfiguration config = YamlConfiguration.loadConfiguration(HungerGames.kitFile);

    public Kit(String kit, Player p){
        this.kit = kit;
        this.player = p;

        if(playerSelectedKit.containsKey(p)){
            setPermission(p, kit, true);
            playerSelectedKit.replace(p, kit);
        }else{
            setPermission(p, kit, false);
            playerSelectedKit.put(p, kit);
        }
    }


    public void fillInventory(){
        List<String> items = config.getStringList(kit + ".items");
        player.getInventory().clear();

        for (String s: items) {
            String[] div = s.split(" ");
            ItemStack item = new ItemStack(Material.getMaterial(div[0]), Integer.parseInt(div[1]));
            player.getInventory().addItem(item);
        }

    }

    public static Set<String> listKits(){
        return config.getKeys(false);
    }

    public static String getKit(Player p){
        return playerSelectedKit.getOrDefault(p, null);
    }

    public static List<String> getKitAbilities(String kit){
        return config.getStringList(kit + ".ability");
    }

    public static List<String> getKitPerms(String kit){
        return config.getStringList(kit + ".permission");
    }

    public static void removeSelectedKit(Player p){
        playerSelectedKit.remove(p);
    }

    public static void setPermission(Player p, String kit, boolean change){
        PermissionAttachment pperms = HungerGames.perms.get(p.getUniqueId());

        if(change){
            for (String perm:getKitPerms(playerSelectedKit.get(p))) {
                pperms.unsetPermission(perm);
            }
        }

        for (String perm:getKitPerms(kit)) {
            pperms.setPermission(perm, true);
        }
    }

}
