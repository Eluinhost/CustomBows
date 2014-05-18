package com.publicuhc.custombows;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class CustomBows extends JavaPlugin implements Listener{

    private static final String SHOTGUN_NAME = ChatColor.GOLD+"Shotgun";

    public static Plugin getInstance() {
        return Bukkit.getPluginManager().getPlugin("custombows");
    }

    @Override
	public void onEnable(){
        getServer().getPluginManager().registerEvents(this, this);
	}

    public boolean isShotgunBow(ItemStack stack){
        return stack.getType() == Material.BOW && stack.getItemMeta().getDisplayName().equals(SHOTGUN_NAME);
    }

	@EventHandler
	public void onProjectileLaunchEvent(ProjectileLaunchEvent ple){
		Projectile e = ple.getEntity();
		if(e instanceof Arrow && e.getShooter() instanceof Player){
            Player p = (Player) e.getShooter();

            if(p.getItemInHand().getType() != Material.BOW||!isShotgunBow(p.getItemInHand())){
                return;
            }

            ShotgunShootEvent event = new ShotgunShootEvent(p);
            Bukkit.getPluginManager().callEvent(event);

            if(event.isCancelled()){
                ple.setCancelled(true);
                return;
            }

            //only allow full pull back
            if(e.getVelocity().length() < 2.8){
                p.sendMessage(ChatColor.RED+"Pull the bow all the way back to use the shotgun!");
                //cancel the actual launch
                ple.setCancelled(true);
                p.playSound(p.getLocation(), Sound.ITEM_BREAK,1,0);
                return;
            }

            //get a copy of the original arrow
            Vector regular = e.getVelocity().clone();

            p.getWorld().playSound(p.getLocation(), Sound.EXPLODE, 4, 0);

            //create 8 more arrows
            for(int j = 0; j<8; j++){
                //delay each arrow by 1 tick
                Bukkit.getScheduler().scheduleSyncDelayedTask(
                        this,
                        new RandomOffsetArrow(regular.clone(), e.getLocation(), e.getShooter()),
                        j);
            }

            //remove the original arrow
            e.remove();
		}
	}

    @EventHandler
    public void onProjectileHitEvent(ProjectileHitEvent phe){
        Entity e = phe.getEntity();
        if(e instanceof Arrow){
            Bukkit.getScheduler().scheduleSyncDelayedTask(this, new EntityRemoveJob(e), 40);
        }
    }

    @EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent edbee){
		Entity e = edbee.getDamager();
		if(e instanceof Arrow){
            Arrow arrow = (Arrow) e;
            if(e.hasMetadata("ArrowType")){
                String type = e.getMetadata("ArrowType").get(0).asString();
                if(type.equals("SHOTGUN")){
                    arrow.setFireTicks(0);
                    if(edbee.getEntity() instanceof LivingEntity){
                        LivingEntity p = (LivingEntity) edbee.getEntity();
                        p.setNoDamageTicks(0);
                    }
                }
            }
		}
	}

    public void giveBowAndArrow(InventoryHolder holder){
        ItemStack bowStack = new ItemStack(Material.BOW,1);
        bowStack.addEnchantment(Enchantment.ARROW_INFINITE,1);
        bowStack.addUnsafeEnchantment(Enchantment.DURABILITY,100);

        ItemMeta itemMeta = bowStack.getItemMeta();
        itemMeta.setDisplayName(SHOTGUN_NAME);
        bowStack.setItemMeta(itemMeta);

        ItemStack anArrow = new ItemStack(Material.ARROW,1);

        holder.getInventory().addItem(bowStack,anArrow);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String Label, String[] args){
        if(command.getName().equalsIgnoreCase("givebow")){
            if(args.length != 1){
                sender.sendMessage(ChatColor.RED+"/givebow <player>");
                return true;
            }
            Player p = Bukkit.getPlayerExact(args[0]);
            if(p == null){
                sender.sendMessage(ChatColor.RED+"Invalid player");
                return true;
            }
            giveBowAndArrow(p);

            sender.sendMessage(ChatColor.GOLD+"Bow given");
            return true;
        }
        return false;
    }
}
