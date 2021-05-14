package plugin.commands.commandClass;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugin.commands.ICommands;
import plugin.kits.Kit;
import plugin.kits.lists.ListKit;

import java.util.ArrayList;

public class CommandKit implements ICommands {

    @Override
    public boolean exec(CommandSender sender, Command command, String s, String[] args) {
        if(args.length == 0){
            Kit.openKitMenu((Player) sender);
        }else{
            try{
                ListKit listKit = ListKit.valueOf(args[0].toUpperCase());
                Kit.setKit((Player) sender, listKit);
            }catch (IllegalArgumentException e){
                sender.sendMessage("§c Le kit " + args[0] + " n'existe pas encore.");
            }
        }
        return true;
    }
}
