package plugin.commands.commandClass;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import plugin.commands.ICommands;

import org.bukkit.entity.Player;

public class CommandFly implements ICommands {
    @Override
    public boolean exec(CommandSender sender, Command command, String label, String[] args){
        if(sender.isOp()){
            Player player = (Player) sender;
            player.setAllowFlight(!player.getAllowFlight());
        }
        return true;
    }
}
