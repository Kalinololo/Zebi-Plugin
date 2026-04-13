package plugin.kits.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import plugin.kits.KitListener;

public class Swindler extends KitListener {

    @EventHandler
    public void onPickpocket(EntityDamageEvent e){
        if(e.getEntity() instanceof Player)
        {
            if (e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK)
            {
                Player damaged = (Player) e.getEntity();
                Player damager = (Player) e.getDamageSource().getCausingEntity();
                EquipmentSlot item = damager.getActiveItemHand();
                if (item != null && damager.getEquipment().getItem(item).getType() == Material.STICK && hasAbility(damager) && isCooldowned(damager, 25000))
                {
                    int index = java.util.concurrent.ThreadLocalRandom.current().nextInt(damaged.getInventory().getSize());
                    ItemStack stolenItem = damaged.getInventory().getItem(index);
                    damaged.getInventory().setItem(index, damaged.getInventory().getItemInMainHand());
                    damaged.getInventory().setItemInMainHand(stolenItem);
                }
            }
            
        }
    }


}
