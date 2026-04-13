package plugin.commands.commandClass;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import plugin.HungerGames;
import plugin.commands.ICommands;

public class CommandHGParty implements ICommands {

    @Override
    public boolean exec(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            if(args[0].equals("stop")){
                HungerGames.party.end();
            }else if(args[0].equals("start")){
                if(!HungerGames.party.setStarted(true)){
                    sender.sendMessage("La partie a déjà commencé.");
                }
            }
            else if (args[0].equals("reset")){
                if (HungerGames.party.isStarted()) {
                    sender.sendMessage("La partie est déjà en cours, impossible de la réinitialiser.");
                } else {
                    HungerGames.party.restart();
                }
            }
        }
        return true;
    }
}

