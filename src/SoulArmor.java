import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;

public class SoulArmor implements Listener{

	private Main plugin;
	public SoulArmor(Main plugin) {
		this.plugin=plugin;
	}

	Items item = new Items();
	@EventHandler
	public void onBreak(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			Player p = (Player)event.getEntity();
			if(plugin.hasArmor(p, item.soulHelmet(), item.soulChestplate(), item.soulLeggings(), item.soulBoots())) {
				if(event.getCause().equals(DamageCause.LAVA) || event.getCause().equals(DamageCause.FIRE) || 
						event.getCause().equals(DamageCause.FIRE_TICK)) {
					event.setCancelled(true);
				}
			}
		}
	}
	@EventHandler
	public void onWalk(PlayerMoveEvent event) {
		Player p = event.getPlayer();
		if(plugin.hasArmor(p, item.soulHelmet(), item.soulChestplate(), item.soulLeggings(), item.soulBoots())) {
			p.getWorld().spawnParticle(Particle.SOUL,p.getLocation(),5,1,1,1);	
		}
	}
}
