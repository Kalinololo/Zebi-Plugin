package plugin.commands.commandClass;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugin.commands.ICommands;
import plugin.kits.Kit;

import java.util.Set;

public class CommandKit implements ICommands {



    @Override
    public boolean exec(CommandSender sender, Command command, String s, String[] args) {
        if(args.length == 0){
            StringBuilder message = new StringBuilder("§2 Kits : ");
            Set<String> kits = Kit.listKits();
            for (String kit : kits) {
                message.append(kit).append(", ");
            }
            message = new StringBuilder(message.substring(0, message.length() - 2) + ".");

            sender.sendMessage(message.toString());
        }else if(Kit.listKits().contains(args[0])){
            Kit kit = new Kit(args[0], (Player) sender);
            kit.fillInventory();
        }else{
            sender.sendMessage("§c Le kit " + args[0] + " n'existe pas encore.");
        }
        return true;
    }
}
