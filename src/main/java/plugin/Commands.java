package plugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (command.getName()) {
            case "surfacerandom":
                if(sender instanceof Player){
                    int n = 10;
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
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    if(Kit.getKitAbilities(Kit.getKit(player)).contains("SEECRET") && player.getInventory().getBoots() != null) {
                        if (player.getInventory().getBoots().getType() == Material.CHAINMAIL_BOOTS) {
                            for (Player p : sender.getServer().getOnlinePlayers()) {
                                if (player != p) {
                                    p.damage(20, player);
                                }
                            }
                            return true;
                        }
                        player.damage(1);
                    }
                }

                break;
            case "fiou":
                if (Kit.getKitAbilities(Kit.getKit((Player) sender)).contains("FIOU")) {
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
            case "cworld":
                if(sender.isOp()){
                    sender.getServer().createWorld(new WorldCreator(args[0]));
                }
                break;
            case "dworld":
                if(sender.isOp()){
                    sender.getServer().unloadWorld(args[0], false);
                    sender.getServer().getWorld(args[0]).getWorldFolder().delete();
                }
                break;
            case "gworld":
                if(sender.isOp()){
                    Player player = (Player) sender;
                    player.teleport(sender.getServer().getWorld(args[0]).getSpawnLocation());
                }
                break;
            case "lworld":
                if(sender.isOp()){
                    Player player = (Player) sender;
                    String list = "Worlds : ";

                    for (World w:player.getServer().getWorlds()) {
                        list += w.getName() + " - ";
                    }

                    player.sendMessage(list);
                }
        }

        return true;
    }



}