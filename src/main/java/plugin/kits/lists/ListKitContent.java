package plugin.kits.lists;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public enum ListKitContent {

    //Swapper

    SWAPPER_SNOWBALL(ListKit.SWAPPER, "Boule de neige x24", new ItemStack(Material.SNOWBALL, 24)),
    SWAPPER_SHOVEL(ListKit.SWAPPER, "Pelle en fer", new ItemStack(Material.IRON_SHOVEL, 1)),

    //FIREMAN

    FIREMAN_MAGMA(ListKit.FIREMAN, "Lance flamme", new ItemStack(Material.MAGMA_CREAM, 1)),

    //GAMBLER
    GAMBLER_DICE(ListKit.GAMBLER, "Dé du hasard", getNamedItem(Material.PAPER, "§fDé du hasard")),

    //KNIGHT
    KNIGHT_CHESTPLATE(ListKit.KNIGHT, "Plastron en fer", new ItemStack(Material.IRON_CHESTPLATE, 1)),
    KNIGHT_BOOTS(ListKit.KNIGHT, "Bottes en cuir", new ItemStack(Material.LEATHER_BOOTS, 1)),

    //NINJA
    NINJA_SHADOWSTEP(ListKit.NINJA, "Pas de l'ombre", getNamedItem(Material.BLACK_DYE, "§8Pas de l'ombre")),

    //BERSERKER
    BERSERKER_AXE(ListKit.BERSERKER, "Hache en fer", new ItemStack(Material.IRON_AXE, 1)),

    //BEASTMASTER
    BEASTMASTER_EGG(ListKit.BEASTMASTER, "Oeuf de loup", new ItemStack(Material.WOLF_SPAWN_EGG, 1)),

    //ELEMENTALISTS
    WATER_MAGE_CORE(ListKit.WATER_MAGE, "Catalyseur aqueux", getNamedItem(Material.HEART_OF_THE_SEA, "§bCatalyseur aqueux")),
    EARTH_MAGE_CORE(ListKit.EARTH_MAGE, "Noyau tellurique", getNamedItem(Material.DIRT, "§6Noyau tellurique")),
    WIND_MAGE_CORE(ListKit.WIND_MAGE, "Noyau aérien", getNamedItem(Material.FEATHER, "§fNoyau aérien")),

    //PYROMANIAC
    PYROMANIAC_LAVA(ListKit.PYROMANIAC, "Seau de lave", new ItemStack(Material.LAVA_BUCKET, 1)),
    PYROMANIAC_LIGHTER(ListKit.PYROMANIAC, "Briquet", new ItemStack(Material.FLINT_AND_STEEL, 1)),

    //JUMPER
    JUMPER_MUSHROOM(ListKit.JUMPER, "SAUTE !!!", new ItemStack(Material.FIREWORK_ROCKET)),

    //MEILLEUR
    MEILLEUR_BOOT(ListKit.MEILLEUR, "Bottes en maille", new ItemStack(Material.CHAINMAIL_BOOTS)),

    //VAMPIRE
    VAMPIRE_FORCE(ListKit.VAMPIRE, "Potion de force", getPotion(new PotionEffect(PotionEffectType.STRENGTH, 3000, 0))),

    //GRANDMERE
    POPOTE(ListKit.GRANDMERE, "le Potage", new ItemStack(Material.MUSHROOM_STEW, 1)),

    //CAVALIER
    CAVALIER_SADDLE(ListKit.CAVALIER, "Selle", new ItemStack(Material.SADDLE, 1)),
    CAVALIER_LEAD(ListKit.CAVALIER, "Laisse", new ItemStack(Material.LEAD, 1)),
    CAVALIER_BOW(ListKit.CAVALIER, "Arc", new ItemStack(Material.BOW, 1)),
    CAVALIER_ARROW(ListKit.CAVALIER, "Flèche x16", new ItemStack(Material.ARROW, 16)),

    //ABORT
    ABORT_EYE(ListKit.ABORT, "Oeil de l'Ender", new ItemStack(Material.ENDER_PEARL, 1)),

    //POSEIDON
    POSEIDON_BUCKET(ListKit.POSEIDON, "Seau d'eau", new ItemStack(Material.WATER_BUCKET, 1)),

    //SWINDLER
    SWINDLER_STICK(ListKit.SWINDLER, "Baton de pickpocket", new ItemStack(Material.STICK, 1)),

    //THOR
    THOR_HAMMER(ListKit.THOR, "Marteau de Thor", new ItemStack(Material.MACE, 1)),

    //MADMAN
    MADMAN_BLAZE_ROD(ListKit.MADMAN, "Bâton du chaos", new ItemStack(Material.BLAZE_ROD, 1)),

    //ASSASSIN
    ASSASSIN_SWORD(ListKit.ASSASSIN, "Épée en pierre", new ItemStack(Material.STONE_SWORD, 1)),

    //ENDERMAGE
    ENDERMAGE_PEARL(ListKit.ENDERMAGE, "Téléporteur", new ItemStack(Material.ENDER_EYE, 1)),

    FOOD("Pain", new ItemStack(Material.BREAD, 32)),
    COMPASS("Boussole", new ItemStack(Material.COMPASS, 1));

    private final String name;
    private final ListKit kit;
    private final ItemStack item;

    ListKitContent(String name, ItemStack item){
        this.name = name;
        this.kit = null;
        this.item = item;
    }

    ListKitContent(ListKit kit, String name, ItemStack item){
        this.kit = kit;
        this.name = name;
        this.item = item;
    }


    public ItemStack getItem() {
        return item;
    }

    public ListKit getKit() {
        return kit;
    }

    public String getName() {
        return name;
    }

    public ArrayList<ListKitContent> getKitContent(ListKit kit){
        ArrayList<ListKitContent> itemsArray = new ArrayList<>();
        for (ListKitContent kitContent: ListKitContent.values()) {
            if(kitContent == COMPASS){
                itemsArray.add(kitContent);
            }else if(kitContent.getKit() == kit){
                itemsArray.add(kitContent);
            }
        }
        return itemsArray;
    }

    public static ItemStack getPotion(PotionEffect p){
        ItemStack potion = new ItemStack(Material.POTION, 1);

        PotionMeta effect = (PotionMeta) potion.getItemMeta();
        effect.addCustomEffect(p, true);

        potion.setItemMeta(effect);

        return potion;
    }

    public static ItemStack getNamedItem(Material material, String name){
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }

}
