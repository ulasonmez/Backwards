import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class WitherPearl implements Listener{

	private Main plugin;
	public WitherPearl(Main plugin) {
		this.plugin=plugin;
	}
	Items item = new Items();
	HashMap<Entity,Player> witherPearl = new HashMap<>();
	@EventHandler
	public void onClick(ProjectileLaunchEvent event) {
		if(event.getEntity().getType().equals(EntityType.ENDER_PEARL)) {
			Player thrower = getNearbyPlayers(event.getEntity().getLocation());
			if(plugin.hasItem(thrower, "main", item.witherPearl())) {
				witherPearl.put(event.getEntity(),thrower);
			}
		}
	}
	@EventHandler
	public void onHit(ProjectileHitEvent event) {
		if(witherPearl.containsKey(event.getEntity())) {
			Player p = witherPearl.get(event.getEntity());
			new BukkitRunnable() {
				@Override
				public void run() {
					p.teleport(plugin.netherPortalLocation);					
				}
			}.runTaskLater(plugin, 1);
		}
	}
	public Player getNearbyPlayers(Location loc) {
		for(Entity ent : loc.getWorld().getNearbyEntities(loc, 1, 1, 1)) {
			if(ent instanceof Player) {
				return (Player)ent;
			}
		}
		return null;
	}
}
