package plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Iterator;
import java.util.Set;

public class CommandKit implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player && command.getName().equals("kit")){
            if(args.length == 0){
                String message = "§2 Kits : ";
                Set<String> kits = Kit.listKits();
                Iterator<String> itKit = kits.iterator();
                while (itKit.hasNext()){
                    message += itKit.next()  + ", ";
                }

                message = message.substring(0, message.length()-2) + ".";

                sender.sendMessage(message);

                return true;
            }else if(Kit.listKits().contains(args[0])){
                Kit kit = new Kit(args[0], (Player) sender);
                kit.fillInventory();
            }else{
                sender.sendMessage("§c Le kit " + args[0] + " n'existe pas encore.");
            }
        }
        return true;
    }
}
