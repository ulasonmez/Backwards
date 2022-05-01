import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.scheduler.BukkitRunnable;

public class EndermiteMob {

	private Main plugin;
	public EndermiteMob(Main plugin) {
		this.plugin=plugin;
	}
	Items item = new Items();
	public void spawnEndermite(Player p) {
		Zombie z = endermiteMob(p.getLocation());
		followPlayer(z,p);
	}
	public Zombie endermiteMob(Location loc) {
		Zombie endermite = (Zombie)loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
		endermite.setCustomName("Endermite");
		endermite.setInvulnerable(true);
		endermite.setInvisible(true);
		endermite.getEquipment().setHelmet(item.endermiteModel());
		return endermite;
	}
	public void followPlayer(Zombie zomb, Player p) {
		new BukkitRunnable() {
			@Override
			public void run() {
				Location zombLoc = zomb.getLocation(),pLoc = p.getLocation();
				if(zombLoc.distance(pLoc) >= 30) {
					zomb.teleport(p);
				}
				else if(zombLoc.distance(pLoc) >= 5) {
					zomb.setTarget(p);
				}
				else {
					zomb.setTarget(null);
				}
			}
		}.runTaskTimer(plugin, 0, 5);
	}
}
