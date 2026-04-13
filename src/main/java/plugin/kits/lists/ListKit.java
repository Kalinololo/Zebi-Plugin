package plugin.kits.lists;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public enum ListKit {


    SWAPPER("Swapper", "Tu pratiques la bataille de boule de neige à haut niveau ? Ce kit est fait pour toi ! Touche ta cible afin d'échanger de place avec celle-ci.", new ListKitAbilities[]{ListKitAbilities.SWAP}, Material.SNOWBALL),
    FIREMAN("Fireman", "Tu lances des grosses boules de feu, un peu comme si tu étais un mage !", new ListKitAbilities[]{ListKitAbilities.FIREMAN}, Material.MAGMA_CREAM),
    GAMBLER("Gambler", "Tu lances ton dé et le destin décide de ton sort.", new ListKitAbilities[]{ListKitAbilities.GAMBLER}, Material.PAPER),
    KNIGHT("Knight", "Tu commences avec une armure simple mais solide.", new ListKitAbilities[]{}, Material.IRON_CHESTPLATE),
    NINJA("Ninja", "Tu disparais dans l'ombre pour te glisser derrière ta cible.", new ListKitAbilities[]{ListKitAbilities.NINJA}, Material.BLACK_DYE),
    BERSERKER("Berserker", "Plus tu es blessé, plus tu frappes fort.", new ListKitAbilities[]{ListKitAbilities.BERSERKER}, Material.IRON_AXE),
    BEASTMASTER("Beastmaster", "Invoque un loup fidèle pour traquer tes ennemis.", new ListKitAbilities[]{ListKitAbilities.BEASTMASTER}, Material.WOLF_SPAWN_EGG),
    WATER_MAGE("Water mage", "Crée une source d'eau et nage avec la grâce d'un dauphin.", new ListKitAbilities[]{ListKitAbilities.WATER_MAGE}, Material.HEART_OF_THE_SEA),
    EARTH_MAGE("Earth mage", "Canalise la terre pour devenir plus résistant.", new ListKitAbilities[]{ListKitAbilities.EARTH_MAGE}, Material.DIRT),
    WIND_MAGE("Wind mage", "Domine le vent pour bondir et planer.", new ListKitAbilities[]{ListKitAbilities.WIND_MAGE}, Material.FEATHER),
    PYROMANIAC("Pyromaniac", "Le feu ne te fait rien, et tu te bats avec la lave.", new ListKitAbilities[]{ListKitAbilities.PYROMANIAC}, Material.LAVA_BUCKET),
    VAMPIRE("Vampire", "Tu es doté d'une force surhumaine et tu te découvres une certain passion pour la dégustation du sang de tes ennemis.", new ListKitAbilities[]{ListKitAbilities.VAMPIRE}, Material.REDSTONE),
    MEILLEUR("Meilleur", "Selon la prophétie, tu serais l'élu... Mais faut-il encore que tu sois prêt...",  new ListKitAbilities[]{ListKitAbilities.SECRET}, Material.BOOK),
    JUMPER("Jumper", "Tu peux sauter haut et faire mal au gens en tombant... Pas très gentil mais bon...", new ListKitAbilities[]{ListKitAbilities.JUMPER,ListKitAbilities.FREEFALL}, Material.FIREWORK_ROCKET),
    GRANDMERE("Grand-Mère", "Tu bois ta soupe comme une bonne grand-mère", new ListKitAbilities[]{ListKitAbilities.GRANDMERE}, Material.MUSHROOM_STEW),
    CAVALIER("Cavalier", "Tu es un cavalier, tu peux galoper à travers les champs de bataille et écraser tes ennemis !", new ListKitAbilities[]{ListKitAbilities.CAVALIER}, Material.SADDLE),
    ABORT("Abort", "Tu peux dash dans tous les sens et etre un veritable moustique pour tes adversaires !", new ListKitAbilities[]{ListKitAbilities.ABORT}, Material.ENDER_PEARL),
    POSEIDON("Poseidon", "Tu es le dieu de la mer, tant que tu es dans l'eau personne ne peut te resister !", new ListKitAbilities[]{ListKitAbilities.POSEIDON}, Material.WATER_BUCKET),
    SWINDLER("Swindler", "Tu es un vrai p'tit filou !", new ListKitAbilities[]{ListKitAbilities.SWINDLER}, Material.STICK),
    THOR("Thor", "Tu es le dieu du tonnerre, tu peux faire tomber la foudre sur tes ennemis !", new ListKitAbilities[]{ListKitAbilities.THOR}, Material.MACE),
    MADMAN("Madman", "Tu plonges les ennemis dans le chaos avec une attaque de zone !", new ListKitAbilities[]{ListKitAbilities.MADMAN}, Material.BLAZE_ROD),
    ASSASSIN("Assassin", "Tu surgis dans l'ombre, prêt à frapper sans être vu.", new ListKitAbilities[]{ListKitAbilities.ASSASSIN}, Material.STONE_SWORD),
    ENDERMAGE("Endermage", "Tu manipules l'espace pour rassembler tout le monde au même endroit.", new ListKitAbilities[]{ListKitAbilities.ENDERMAGE}, Material.ENDER_EYE),
    BEGGAR("Beggar", "Tu n'as pas de kit, tu es un pauvre hère qui doit se débrouiller avec les moyens du bord !", new ListKitAbilities[]{}, Material.STICK);

    private final String name, description;
    private final ListKitAbilities[] abilities;
    private final Material image;

    ListKit(String name, String description, ListKitAbilities[] abilities, Material image){
        this.name = name;
        this.description = description;
        this.abilities = abilities;
        this.image = image;
    }

    public ListKitAbilities[] getAbilities() {
        return abilities;
    }

    public ArrayList<ListKitContent> getItems() {
        return ListKitContent.COMPASS.getKitContent(this);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Material getImage() {
        return image;
    }

    public void fillInventory(Player p){
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);

        if (this == KNIGHT) {
            p.getInventory().setChestplate(new org.bukkit.inventory.ItemStack(Material.IRON_CHESTPLATE));
            p.getInventory().setBoots(new org.bukkit.inventory.ItemStack(Material.LEATHER_BOOTS));
            return;
        }

        for (ListKitContent item:getItems()) {
            p.getInventory().addItem(item.getItem());
        }
    }
}
