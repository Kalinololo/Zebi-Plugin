package plugin;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import plugin.kits.Kit;
import plugin.kits.lists.ListKitAbilities;

import java.util.*;

public class EventManager implements Listener{

    private static HashMap<Player, Date> cooldownManager = new HashMap<>();

    /*@EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Snowball && ((Snowball) e.getDamager()).getShooter() instanceof Player) {
            Player shooter = (Player) ((Snowball) e.getDamager()).getShooter();
            Player shooted = (Player) e.getEntity();
            Snowball bouboule = (Snowball) e.getDamager();
        }

        /*
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
    }*/

    @EventHandler
    public void onCelianTBourre(PlayerInteractEvent e) {
        if(e.getItem() != null && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)){
            if(e.getItem().getType() == Material.GOLD_SWORD){
                Player player = e.getPlayer();
                Location pos = player.getLocation();
                pos.setX(pos.getX()+pos.getDirection().getX()*1.2);
                pos.setZ(pos.getZ()+pos.getDirection().getZ()*1.2);
                pos.setY(pos.getY()+1);
                Entity bouboule = player.getWorld().spawnEntity(pos, EntityType.SNOWBALL);
                bouboule.setVelocity(new Vector(player.getEyeLocation().getDirection().getX()*1.1, player.getEyeLocation().getDirection().getY()*1.1, player.getEyeLocation().getDirection().getZ()*1.1));
            }else if(e.getItem().getType() == Material.RED_MUSHROOM && new ArrayList<>(Arrays.asList(Kit.getKit(e.getPlayer()).getAbilities())).contains(ListKitAbilities.valueOf("JUMPER"))){
                if(isCooldowned(e.getPlayer(), 800)){
                    Vector dir = e.getPlayer().getEyeLocation().getDirection();
                    e.getPlayer().setVelocity(new Vector(dir.getX()*0.4, 1, dir.getZ()*0.4));
                }
            }else if(e.getItem().getType() == getKitSelector().getType()) {
                e.getPlayer().openInventory(Kit.getKitMenu());
            }else if(e.getItem().getType() == Material.COMPASS){
                Player player = e.getPlayer();
                double distance = player.getLocation().distance(getNearestPlayer(player).getLocation());
                player.sendMessage("§6Le joueur le plus proche est à §l" + (int) distance + " blocs.");
                if((int) distance == 0){
                    player.sendMessage("§6Il faut ouvrir les yeux, tu fais pas d'effort la.");
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

        Bukkit.getScheduler().runTaskTimer(HungerGames.plugin, () -> compasTrack(e.getPlayer()), 1, 1);
    }

    @EventHandler
    public void onKitSelect(InventoryClickEvent e){
        if(e.getInventory().getName().equals(Kit.getKitMenu().getName())){
            new Kit(e.getCurrentItem().getItemMeta().getDisplayName(), (Player) e.getWhoClicked()).fillInventory();
            e.getWhoClicked().closeInventory();
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if(Kit.listKits().contains(e.getItemDrop().getItemStack().getItemMeta().getDisplayName()) || e.getItemDrop().getItemStack().getItemMeta() == getKitSelector().getItemMeta()){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            Player damaged = (Player) e.getEntity();

            if(damaged.getHealth() - e.getDamage() <= 0){
                damaged.getWorld().strikeLightningEffect(damaged.getLocation());
                damaged.setGameMode(GameMode.SPECTATOR);
                e.setCancelled(true);


                if(new ArrayList<>(Arrays.asList(Kit.getKit((Player) e.getEntity()).getAbilities())).contains(ListKitAbilities.valueOf("VAMPIRE"))){
                    Player killer = damaged.getKiller();
                    killer.setHealth(killer.getHealth()+9);
                }
                int nbPlayerAlive = 0;
                Player lastAlive = null;

                for (Player p: e.getEntity().getServer().getOnlinePlayers()) {
                    if(p.getGameMode() == GameMode.SURVIVAL) nbPlayerAlive++;
                    lastAlive = p;
                    if(nbPlayerAlive > 1) return;
                }

                if(nbPlayerAlive == 1){
                    lastAlive.setGameMode(GameMode.CREATIVE);
                    Player finalLastAlive = lastAlive;
                    HungerGames.plugin.getServer().getScheduler().runTaskTimer(HungerGames.plugin, () -> e.getEntity().getServer().broadcastMessage("§l" + finalLastAlive.getName() + " a gagné la partie, youhou, super, génial, trop fort le type ! (Vieux bot)"), 3, 3);

                    //TODO : ------------------ Fin de partie
                }
            }
        }
    }

    @EventHandler
    public void onBlockDestroy(BlockBreakEvent e){
        /*
        if(e.getBlock().getType() == Material.WOOD){
            Location actualBlock = e.getBlock().getLocation();
            Material actuelBlockMaterial = e.getBlock().getType();

            Location locDroite = new Location(e.getPlayer().getWorld(), actualBlock.getX() + 1,actualBlock.getY(), actualBlock.getZ());
            Location locGauche = new Location(e.getPlayer().getWorld(), actualBlock.getX() - 1,actualBlock.getY(), actualBlock.getZ());
            Location locHaut = new Location(e.getPlayer().getWorld(), actualBlock.getX(),actualBlock.getY(), actualBlock.getZ() + 1);
            Location locBas = new Location(e.getPlayer().getWorld(), actualBlock.getX(),actualBlock.getY(), actualBlock.getZ() - 1);

            boolean droite = e.getBlock().getWorld().getBlockAt(locDroite).getType() == e.getBlock().getType();
            boolean gauche = e.getBlock().getWorld().getBlockAt(locGauche).getType() == e.getBlock().getType();
            boolean haut = e.getBlock().getWorld().getBlockAt(locHaut).getType() == e.getBlock().getType();
            boolean bas = e.getBlock().getWorld().getBlockAt(locBas).getType() == e.getBlock().getType();

            while (actuelBlockMaterial == e.getBlock().getType()){
                e.getPlayer().getWorld().getBlockAt(actualBlock).breakNaturally();
                if(droite){
                    e.getPlayer().getWorld().getBlockAt(locDroite).breakNaturally();
                }
                if(gauche){
                    e.getPlayer().getWorld().getBlockAt(locGauche).breakNaturally();
                }
                if(haut){
                    e.getPlayer().getWorld().getBlockAt(locHaut).breakNaturally();
                }
                if(bas){
                    e.getPlayer().getWorld().getBlockAt(locBas).breakNaturally();
                }

                actualBlock.setY(actualBlock.getY() + 1);
                locDroite.setY(locDroite.getY() + 1);
                locGauche.setY(locGauche.getY() + 1);
                locHaut.setY(locHaut.getY() + 1);
                locBas.setY(locBas.getY() + 1);

                actuelBlockMaterial = e.getBlock().getWorld().getBlockAt(actualBlock).getType();
            }


        }*/
    }


    public static ItemStack getKitSelector(){
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

    private void compasTrack(Player p){
        p.setCompassTarget(getNearestPlayer(p).getLocation());
    }

    private Player getNearestPlayer(Player p){
        Location pos = p.getLocation();
        double nearestRange = 0;
        Player nearestPlayer = p;

        for (Player player: p.getWorld().getPlayers()) {
            if((player.getLocation().distance(pos) <= nearestRange || nearestRange==0) && player.getUniqueId() != p.getUniqueId()){
                nearestRange = player.getLocation().distance(pos);
                nearestPlayer = player;
            }
        }

        return nearestPlayer;
    }
}