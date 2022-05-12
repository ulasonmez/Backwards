import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class GreatFertilizer implements Listener{

	private Main plugin;
	public GreatFertilizer(Main plugin) {
		this.plugin=plugin;
	}
	Items item = new Items();
	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(event.getHand().equals(EquipmentSlot.HAND)) {
				if(plugin.hasItem(p, "main", item.greatFertilizer())) {
					Location loc = event.getClickedBlock().getLocation();
					if(loc.equals(plugin.bigTreeLocation) && loc.getBlock().getType().equals(Material.OAK_SAPLING)) {
						(new SchematicPaster()).pasteSchematics(plugin.schematics, loc, plugin,"BigTree.schem");
						Bukkit.broadcastMessage(p.getName()+" has completed the challenge "+ChatColor.DARK_PURPLE+"[But the game just started!]");
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, Integer.MAX_VALUE, 40);
						new BukkitRunnable() {
							@Override
							public void run() {
								p.getWorld().spawnParticle(Particle.TOTEM, plugin.bigTreeLocation, 15, 4,7,4);
							}
						}.runTaskTimer(plugin, 0, 1);
					}
				}
			}
		}
	}
}
