package plugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor{



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        switch (command.getName()) {
            case "surfacerandom":
                int n = 10;
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    Location loc = p.getLocation();
                    loc.setY(100);
                    while (n != 0) {
                        p.getPlayer().setFlySpeed(10);
                        n--;
                    }
                    p.teleport(loc);
                }
                break;
            case "ez":
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if(player.getInventory().getBoots() == null){
                        player.kickPlayer("Va graille your cadaver p'tit batard.");
                    }else if (!(player.getInventory().getBoots().getType() == Material.CHAINMAIL_BOOTS)) {
                        player.kickPlayer("Va graille your cadaver p'tit batard.");
                    }else{
                        for (Player p : sender.getServer().getOnlinePlayers()) {
                            if (player != p) {
                                p.damage(20, player);
                            }
                        }
                    }
                }
                break;
            case "fiou":
                if (sender instanceof Player) {
                    if (args.length == 0) {
                        return true;
                    }
                    Location pos = ((Player) sender).getLocation();

                    int max = Integer.parseInt(args[0]);
                    for (int i = 0; i < max; i++) {
                        ((Player) sender).getWorld().spawnEntity(pos, EntityType.PIG_ZOMBIE);
                    }
                }
                break;
            case "spawn":
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    Location loc = p.getLocation();

                }
                break;
        }
        return true;
    }



}