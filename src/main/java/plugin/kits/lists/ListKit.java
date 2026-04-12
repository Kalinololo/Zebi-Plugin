package plugin.kits.lists;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public enum ListKit {


    SWAPPER("Swapper", "Tu pratiques la bataille de boule de neige à haut niveau ? Ce kit est fait pour toi ! Touche ta cible afin d'échanger de place avec celle-ci.", new ListKitAbilities[]{ListKitAbilities.SWAP}, Material.SNOWBALL),
    FIREMAN("Fireman", "Tu lances des grosses boules de feu, un peu comme si tu étais un mage !", new ListKitAbilities[]{ListKitAbilities.FIREMAN}, Material.MAGMA_CREAM),
    VAMPIRE("Vampire", "Tu es doté d'une force surhumaine et tu te découvres une certain passion pour la dégustation du sang de tes ennemis.", new ListKitAbilities[]{ListKitAbilities.VAMPIRE}, Material.REDSTONE),
    MEILLEUR("Meilleur", "Selon la prophétie, tu serais l'élu... Mais faut-il encore que tu sois prêt...",  new ListKitAbilities[]{ListKitAbilities.SECRET}, Material.BOOK),
    JUMPER("Jumper", "Tu peux sauter haut et faire mal au gens en tombant... Pas très gentil mais bon...", new ListKitAbilities[]{ListKitAbilities.JUMPER,ListKitAbilities.FREEFALL}, Material.FIREWORK_ROCKET),
    GRANDMERE("Grand-Mère", "Tu bois ta soupe comme une bonne grand-mère", new ListKitAbilities[]{ListKitAbilities.GRANDMERE}, Material.MUSHROOM_STEW),
    CAVALIER("Cavalier", "Tu es un cavalier, tu peux galoper à travers les champs de bataille et écraser tes ennemis !", new ListKitAbilities[]{ListKitAbilities.CAVALIER}, Material.SADDLE),
    ABORT("Abort", "Tu peux dash dans tous les sens et etre un veritable moustique pour tes adversaires !", new ListKitAbilities[]{ListKitAbilities.ABORT}, Material.ENDER_EYE),
    POSEIDON("Poseidon", "Tu es le dieu de la mer, tant que tu es dans l'eau personne ne peut te resister !", new ListKitAbilities[]{ListKitAbilities.POSEIDON}, Material.WATER_BUCKET),
    THOR("Thor", "Tu es le dieu du tonnerre, tu peux faire tomber la foudre sur tes ennemis !", new ListKitAbilities[]{ListKitAbilities.THOR}, Material.MACE),
    BEGGAR("Beggar", "Tu n'as pas de kit, tu es un pauvre hère qui doit se débrouiller avec les moyens du bord !", new ListKitAbilities[]{}, Material.STICK);

    private String name, description;
    private ListKitAbilities[] abilities;
    private Material image;

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

        for (ListKitContent item:getItems()) {
            p.getInventory().addItem(item.getItem());
        }
    }
}
