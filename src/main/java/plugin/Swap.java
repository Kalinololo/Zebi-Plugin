package plugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.util.Vector;

import java.util.Date;
import java.util.HashMap;

public class Swap implements Listener{

    private static HashMap<Player, Date> cooldownManager = new HashMap<>();

    @EventHandler
    public void onDuel(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Snowball) {
            Snowball bouboule = (Snowball) e.getDamager();
            if (bouboule.getShooter() instanceof Player) {
                Player shooter = (Player) bouboule.getShooter();
                if(Kit.getKitAbilities(Kit.getKit(shooter)).contains("BOUBOULE")){
                    Entity shooted = e.getEntity();

                    Location posShooted = shooted.getLocation();

                    shooted.teleport(shooter.getLocation());
                    shooter.teleport(posShooted);
                } else {
                    Entity k = e.getEntity();
                    k.setVelocity(e.getDamager().getVelocity());
                }
            } else {
                Entity k = e.getEntity();
                k.setVelocity(e.getDamager().getVelocity());
            }
        }
    }

    @EventHandler
    public void onCelianTBourre(PlayerInteractEvent e) throws InterruptedException {
        if(e.getItem() != null && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)){
            if(e.getItem().getType() == Material.GOLD_SWORD){
                Player player = e.getPlayer();
                Location pos = player.getLocation();
                pos.setX(pos.getX()+pos.getDirection().getX()*1.2);
                pos.setZ(pos.getZ()+pos.getDirection().getZ()*1.2);
                pos.setY(pos.getY()+1);
                Entity bouboule = player.getWorld().spawnEntity(pos, EntityType.SNOWBALL);
                bouboule.setVelocity(new Vector(player.getEyeLocation().getDirection().getX()*1.1, player.getEyeLocation().getDirection().getY()*1.1, player.getEyeLocation().getDirection().getZ()*1.1));
            }else if(e.getItem().getType() == Material.RED_MUSHROOM && Kit.getKitAbilities(Kit.getKit(e.getPlayer())).contains("JUMPER")){
                if(isCooldowned(e.getPlayer(), 500)){
                    Vector dir = e.getPlayer().getEyeLocation().getDirection();
                    e.getPlayer().setVelocity(new Vector(dir.getX()*0.4, 1, dir.getZ()*0.4));
                }
            }else if(e.getItem().getType() == getKitSelector().getType()){
                e.getPlayer().sendMessage("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
                e.getPlayer().openInventory(Kit.kitMenu);
            }
        }
    }

    @EventHandler
    public void onFall(EntityDamageEvent e){
        if(e.getCause() == EntityDamageEvent.DamageCause.FALL  && e.getEntity() instanceof Player){
            if(Kit.getKitAbilities(Kit.getKit((Player) e.getEntity())).contains("FREEFALL")){
                double damage = e.getDamage();
                e.setDamage(0);
                for (Entity enemy : e.getEntity().getNearbyEntities(4, 4, 4)) {
                    if(enemy instanceof LivingEntity){
                        ((LivingEntity) enemy).damage(damage);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent e){
        if(Kit.getKit(e.getPlayer()) != null){
            Kit.removeSelectedKit(e.getPlayer());
        }
        cooldownManager.remove(e.getPlayer());
    }

    @EventHandler
    public void onConnect(PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        p.getInventory().addItem(getKitSelector());
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        e.getEntity().getWorld().strikeLightningEffect(e.getEntity().getLocation());
    }


    private ItemStack getKitSelector(){
        ItemStack plume = new ItemStack(Material.FEATHER, 1);
        ItemMeta ks = plume.getItemMeta();

        ks.setDisplayName("§9 Sélecteur de kit");
        ks.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ks.addEnchant(Enchantment.ARROW_INFINITE, 1, true);

        plume.setItemMeta(ks);

        return plume;
    }

    private boolean isCooldowned(Player p, int cooldown) {
        Date now = new Date(System.currentTimeMillis());
        if (cooldownManager.containsKey(p)) {
            if (now.getTime() - cooldownManager.get(p).getTime() >= cooldown) {
                cooldownManager.replace(p, now);
                return true;
            } else {
                double timeRemain = (cooldown * 0.001) - ((now.getTime() - cooldownManager.get(p).getTime()) * 0.001);
                p.sendMessage("§6 Vous devez encore attendre " + (double) Math.round(timeRemain * 100) / 100 + " secondes.");
                return false;
            }
        } else {
            cooldownManager.put(p, now);
            return true;
        }
    }
}