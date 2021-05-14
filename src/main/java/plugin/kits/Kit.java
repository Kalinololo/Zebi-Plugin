package plugin.kits;

import com.google.common.collect.SetMultimap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import plugin.HungerGames;
import plugin.kits.lists.ListKit;
import plugin.kits.lists.ListKitAbilities;
import plugin.kits.lists.ListKitContent;

import java.util.*;


public class Kit{

    public static void setKit(Player p, ListKit kit){
        if(playerSelectedKit.containsKey(p)){
            setKitAbilities(p);
            playerSelectedKit.replace(p, kit);
        }else{
            playerSelectedKit.put(p, kit);
        }
        setKitAbilitiesNew(p);
    }

    private static void setKitAbilitiesNew(Player p){
        for (ListKitAbilities ability:getKitAbilities(getKit(p))) {
            ability.getAbility().addPlayer(p);
        }
    }

    private static void setKitAbilities(Player p){
        for (ListKitAbilities ability:getKitAbilities(getKit(p))) {
            ability.getAbility().removePlayer(p);
        }
    }

    private static final Map<Player, ListKit> playerSelectedKit = new HashMap<>();

    public static void fillInventory(Player player){
        getKit(player).fillInventory(player);
    }

    public static ArrayList<ListKit> listKits(){
        return new ArrayList<>(Arrays.asList(ListKit.values()));
    }

    public static ListKit getKit(Player p){
        return playerSelectedKit.getOrDefault(p, null);
    }

    public static void removeSelectedKit(Player p){
        playerSelectedKit.remove(p);
    }

    public static void openKitMenu(Player p){
        p.openInventory(HungerGames.kitMenu);
    }

    public static ArrayList<ListKitAbilities> getKitAbilities(ListKit kit){
        return new ArrayList<>(Arrays.asList(kit.getAbilities()));
    }


    public static Inventory getKitMenu(){
        ArrayList<ListKit> kits = Kit.listKits();
        Inventory menu = Bukkit.createInventory(null, 54, "Sélectionne ton kit !");

        for (ListKit kit : kits) {
            ItemStack item = new ItemStack(kit.getImage(), 1);
            ItemMeta meta = item.getItemMeta();
            ArrayList<String> itemDesc = new ArrayList<>();

            itemDesc.add(kit.getDescription());

            for (ListKitContent kitItem : kit.getItems()) {
                itemDesc.add(" - " + kitItem.getName());
            }

            meta.setDisplayName(kit.getName());
            meta.setLore(itemDesc);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);

            item.setItemMeta(meta);

            menu.addItem(item);
        }

        return menu;
    }

    public static ItemStack getKitSelector(){
        ItemStack plume = new ItemStack(Material.FEATHER, 1);
        ItemMeta ks = plume.getItemMeta();

        ks.setDisplayName("§9 Sélecteur de kit");
        ks.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ks.addEnchant(Enchantment.ARROW_INFINITE, 1, true);

        plume.setItemMeta(ks);

        return plume;
    }

}
