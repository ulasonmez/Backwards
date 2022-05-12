import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class SculkSeeker implements Listener{

	private Main plugin;
	public SculkSeeker(Main plugin) {
		this.plugin=plugin;
	}
	Items item = new Items();
	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(event.getHand().equals(EquipmentSlot.HAND)) {
				if(plugin.hasItem(p, "main", item.sculkSeeker())) {
					shoot(p,plugin.bigTreeLocation);
				}
			}
		}
	}
	public void shoot(Player p, Location treeLocation) {
		new BukkitRunnable() {
			double t = 0.5;
			Location destination = treeLocation;
			Vector dir = (destination.toVector()).clone().subtract((p.getLocation().toVector())).normalize();
			Location pLoc = p.getEyeLocation();
			int count = 0;
			@Override
			public void run() {
				count++;
				if(count>=30) {
					this.cancel();
				}
				t+=0.2;
				double a = dir.getX()*t;
				double c = dir.getZ()*t;
				pLoc.add(a,0,c);
				Particle.DustOptions dustOptions = new Particle.DustOptions(Color.GREEN,1.0F);
				p.getWorld().spawnParticle(Particle.REDSTONE, pLoc,40,0.0,0.0,0.0,dustOptions);

				pLoc.subtract(a,0,c);
			}
		}.runTaskTimer(plugin, 0, 1);
	}
}
