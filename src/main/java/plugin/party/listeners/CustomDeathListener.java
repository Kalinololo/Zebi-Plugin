package plugin.party.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import plugin.HungerGames;
import plugin.party.events.PlayerCustomDeathEvent;

public class CustomDeathListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if(!HungerGames.party.isStarted() || (!HungerGames.party.isPvpActive() && e.getEntity().getType() == EntityType.PLAYER) || HungerGames.isEnded){
            e.setCancelled(true);
        }else if (e.getEntity().getType() == EntityType.PLAYER){
            Player victim = (Player) e.getEntity();

            if (victim.getHealth() - e.getDamage() <= 0) {
                e.setCancelled(true);
                try {
                    EntityDamageByEntityEvent damageEvent = (EntityDamageByEntityEvent) e;
                    HungerGames.plugin.getServer().getPluginManager().callEvent(new PlayerCustomDeathEvent(victim, (Player) damageEvent.getDamager(), e.getDamage()));
                } catch (ClassCastException exception) {
                    HungerGames.plugin.getServer().getPluginManager().callEvent(new PlayerCustomDeathEvent(victim, victim, e.getDamage()));
                }
            }
        }
    }


    @EventHandler
    public void onDeath(PlayerCustomDeathEvent e){
        Player victim = e.getVictim();
        victim.getWorld().strikeLightningEffect(e.getVictim().getLocation());
        victim.setGameMode(GameMode.SPECTATOR);
        victim.getInventory().forEach(item -> {
            if (item != null) victim.getWorld().dropItemNaturally(victim.getLocation(), item);
        });
        e.getVictim().getInventory().clear();
        if(e.getKiller() == e.getVictim()){
            e.getVictim().getServer().broadcastMessage(e.getVictim().getName() + " s'est lui même bouillave sa grande tante ce bot.");
        }else{
            e.getVictim().getServer().broadcastMessage(e.getVictim().getName() + " s'est fait bouillave sa grande tante par " + e.getKiller().getName());
        }

        HungerGames.party.removePlayer(e.getVictim());
        if(HungerGames.party.getPlayers().size() == 1) {
            HungerGames.party.end();
        }
    }
}

