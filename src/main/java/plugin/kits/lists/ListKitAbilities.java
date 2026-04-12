package plugin.kits.lists;

import org.bukkit.plugin.java.JavaPlugin;

import plugin.HungerGames;
import plugin.commands.commandClass.CommandEz;
import plugin.kits.KitListener;
import plugin.kits.abilities.Abort;
import plugin.kits.abilities.Cavalier;
import plugin.kits.abilities.Fireman;
import plugin.kits.abilities.GrandMere;
import plugin.kits.abilities.Jumper;
import plugin.kits.abilities.Poseidon;
import plugin.kits.abilities.Stomper;
import plugin.kits.abilities.Swapper;
import plugin.kits.abilities.Swindler;
import plugin.kits.abilities.Vampire;

public enum ListKitAbilities {

    SWAP("SWAP", new Swapper()),
    FIREMAN("FIREMAN", new Fireman()),
    JUMPER("JUMPER", new Jumper()),
    FREEFALL("FREEFALL", new Stomper()),
    VAMPIRE("VAMPIRE", new Vampire()),
    SECRET("SECRET", new CommandEz()),
    GRANDMERE("GRANDMERE", new GrandMere()),
    CAVALIER("CAVALIER", new Cavalier()),
    ABORT("ABORT", new Abort()),
    POSEIDON("POSEIDON", new Poseidon()),
    SWINDLER("SWINDLER", new Swindler());


    private String name;
    private KitListener ability;

    ListKitAbilities(String name, KitListener ability){
        this.name = name;
        this.ability = ability;
    }

    public KitListener getAbility() {
        return ability;
    }

    public static void loadAbilities(){
        JavaPlugin plugin = HungerGames.plugin;
        for (ListKitAbilities l:values()) {
            plugin.getServer().getPluginManager().registerEvents(l.getAbility(), plugin);
        }
    }
}
