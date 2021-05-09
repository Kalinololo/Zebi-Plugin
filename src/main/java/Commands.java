import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;
import java.lang.Math.*;

public class Commands implements CommandExecutor{



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equals("kit")){
            if(sender instanceof Player){
                Player player = (Player) sender;
                ItemStack Boule = new ItemStack(Material.SNOW_BALL);
                ItemStack Epee = new ItemStack(Material.GOLD_SWORD);
                Epee.setAmount(1);
                Boule.setAmount(16);
                player.getInventory().addItem(Epee,Boule);
            }
        }

        else if (command.getName().equals("surfacerandom")) {

            if (sender instanceof  Player){
                Player p = (Player) sender;
                Location loc = p.getLocation();
                loc.setY(100);


                p.teleport(loc);
            }
        }

        else if (command.getName().equals("ez")) {
            if(sender instanceof Player){
                Player player = (Player) sender;
                if(!(player.getInventory().getBoots().getType() == Material.CHAINMAIL_BOOTS)){
                    player.kickPlayer("Va graille your cadaver p'tit batard.");
                }else{
                    for (Player p:sender.getServer().getOnlinePlayers()) {
                        if(player != p){
                            p.setHealth(0);
                        }
                    }
                }
            }
        }

        return true;
    }



}