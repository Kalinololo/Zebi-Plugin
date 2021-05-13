package plugin.commands.commandClass;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugin.commands.ICommands;
import plugin.kits.event.Swap;

public class CommandKits implements ICommands {

    @Override
    public boolean exec(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        player.getInventory().addItem(Swap.getKitSelector());
        return true;
    }
}
