package plugin.kits.lists;

import org.bukkit.plugin.java.JavaPlugin;

import plugin.HungerGames;
import plugin.commands.commandClass.CommandEz;
import plugin.kits.KitListener;
import plugin.kits.abilities.Abort;
import plugin.kits.abilities.Assassin;
import plugin.kits.abilities.Beastmaster;
import plugin.kits.abilities.Berserker;
import plugin.kits.abilities.Cavalier;
import plugin.kits.abilities.EarthMage;
import plugin.kits.abilities.Endermage;
import plugin.kits.abilities.Fireman;
import plugin.kits.abilities.Gambler;
import plugin.kits.abilities.GrandMere;
import plugin.kits.abilities.Jumper;
import plugin.kits.abilities.Madman;
import plugin.kits.abilities.Ninja;
import plugin.kits.abilities.Poseidon;
import plugin.kits.abilities.Pyromaniac;
import plugin.kits.abilities.Stomper;
import plugin.kits.abilities.Swapper;
import plugin.kits.abilities.Swindler;
import plugin.kits.abilities.Thor;
import plugin.kits.abilities.Vampire;
import plugin.kits.abilities.WaterMage;
import plugin.kits.abilities.WindMage;

public enum ListKitAbilities {

    SWAP(new Swapper()),
    FIREMAN(new Fireman()),
    GAMBLER(new Gambler()),
    NINJA(new Ninja()),
    BERSERKER(new Berserker()),
    BEASTMASTER(new Beastmaster()),
    WATER_MAGE(new WaterMage()),
    EARTH_MAGE(new EarthMage()),
    WIND_MAGE(new WindMage()),
    PYROMANIAC(new Pyromaniac()),
    JUMPER(new Jumper()),
    FREEFALL(new Stomper()),
    VAMPIRE(new Vampire()),
    SECRET(new CommandEz()),
    GRANDMERE(new GrandMere()),
    CAVALIER(new Cavalier()),
    ABORT(new Abort()),
    POSEIDON(new Poseidon()),
    SWINDLER(new Swindler()),
    THOR(new Thor()),
    MADMAN(new Madman()),
    ASSASSIN(new Assassin()),
    ENDERMAGE(new Endermage());


    private final KitListener ability;

    ListKitAbilities(KitListener ability){
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
