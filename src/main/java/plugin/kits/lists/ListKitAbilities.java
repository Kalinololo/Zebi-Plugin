package plugin.kits;

import org.bukkit.plugin.java.JavaPlugin;
import plugin.HungerGames;
import plugin.commands.commandClass.CommandEz;
import plugin.kits.abilities.*;
import plugin.kits.KitListener;

public enum ListKitAbilities {

    SWAP("SWAP", new Swapper()),
    FIREMAN("FIREMAN", new Fireman()),
    JUMPER("JUMPER", new Jumper()),
    FREEFALL("FREEFALL", new Stomper()),
    VAMPIRE("VAMPIRE", new Vampire()),
    SECRET("SECRET", new CommandEz());


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
