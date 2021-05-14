package plugin.commands.commandClass;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import plugin.commands.ICommands;
import plugin.kits.Kit;
import plugin.kits.ListKitAbilities;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandFiou implements ICommands {

    @Override
    public boolean exec(CommandSender sender, Command command, String label, String[] args) {
        if (new ArrayList<>(Arrays.asList(Kit.getKit((Player) sender).getAbilities())).contains(ListKitAbilities.valueOf("FIOU"))) {
            if (args.length != 0) {
                Location pos = ((Player) sender).getLocation();

                int max = Integer.parseInt(args[0]);
                for (int i = 0; i < max; i++) {
                    ((Player) sender).getWorld().spawnEntity(pos, EntityType.PIG_ZOMBIE);
                }
            }
        }
        return true;
    }

}
