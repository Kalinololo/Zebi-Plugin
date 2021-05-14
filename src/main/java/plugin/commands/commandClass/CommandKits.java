package plugin.commands.commandClass;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugin.commands.ICommands;
import plugin.kits.Kit;

public class CommandKits implements ICommands {

    @Override
    public boolean exec(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        player.getInventory().addItem(Kit.getKitSelector());
        return true;
    }
}
