package plugin.kits;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import plugin.HungerGames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class Kit{

    private String kit;
    private Player player;

    private static HashMap<Player, String> playerSelectedKit = new HashMap<>();

    private static final FileConfiguration config = YamlConfiguration.loadConfiguration(HungerGames.kitFile);

    public Kit(String kit, Player p){
        this.kit = kit;
        this.player = p;

        if(playerSelectedKit.containsKey(p)){
            playerSelectedKit.replace(p, kit);
        }else{
            playerSelectedKit.put(p, kit);
        }
    }


    public void fillInventory(){
        List<String> items = config.getStringList(kit + ".items");
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

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

    public static List<String> getKitItem(String kit){
        return config.getStringList(kit + ".items");
    }

    public static String getKitImage(String kit){
        return config.getString(kit + ".showItem");
    }

    public static String getKitDesc(String kit){
        return config.getString(kit + ".description");
    }

    public static void removeSelectedKit(Player p){
        playerSelectedKit.remove(p);
    }

    public static Inventory getKitMenu(){
        Set<String> kits = Kit.listKits();
        Inventory menu = Bukkit.createInventory(null, 54, "Sélectionne ton kit !");

        for (String kit : kits) {
            ItemStack item = new ItemStack(Material.getMaterial(getKitImage(kit)), 1);
            ItemMeta meta = item.getItemMeta();
            List<String> itemDesc = new ArrayList<>();

            itemDesc.add(getKitDesc(kit));

            for (String itemName : getKitItem(kit)) {
                itemDesc.add(" - " + itemName.split(" ")[0]);
            }

            meta.setDisplayName(kit);
            meta.setLore(itemDesc);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);

            item.setItemMeta(meta);

            menu.addItem(item);
        }

        return menu;
    }

}
