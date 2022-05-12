import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class EndermiteMob implements Listener{

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
		endermite.setSilent(true);
		endermite.getEquipment().setHelmet(item.endermiteModel());
		return endermite;
	}
	public void followPlayer(Zombie zomb, Player p) {
		new BukkitRunnable() {
			@Override
			public void run() {
				Location zombLoc = zomb.getLocation(),pLoc = p.getLocation();
				if(!zombLoc.getWorld().equals(pLoc.getWorld())) {
					zomb.teleport(pLoc);
				}
				if(zombLoc.distance(pLoc) >= 30) {
					zomb.teleport(p);
				}
				else if(zombLoc.distance(pLoc) >= 5) {
					zomb.setTarget(p);
				}
				else {
					for(Entity ent : p.getNearbyEntities(5, 3, 5)) {
						if(ent instanceof Monster) {
							if(!ent.equals(zomb)) {
								shoot(zomb,(LivingEntity)ent);
								break;
							}
						}
					}
					zomb.setTarget(null);
				}
			}
		}.runTaskTimer(plugin, 0, 1);
	}
	public void shoot(LivingEntity endermite, LivingEntity target) {
		new BukkitRunnable() {
			double t = 1;
			@Override
			public void run() {
				int count = 0;
				Location destination = target.getEyeLocation().clone(),source = plugin.addToLoc(endermite.getEyeLocation(), 0, -1, 0).clone();
				Vector dir = (destination.toVector()).clone().subtract((source.toVector())).normalize();
				Location pLoc = plugin.addToLoc(endermite.getEyeLocation(), 0, -1, 0);
				count++;
				if(count>=20*20) {
					this.cancel();
				}
				t+=0.1;
				double a = dir.getX()*t;
				double b = dir.getY()*t;
				double c = dir.getZ()*t;
				pLoc.add(a,b,c);
				if(pLoc.distance(target.getEyeLocation())<=0.2) {
					target.damage(1);
					this.cancel();
				}
				if(pLoc.distance(target.getEyeLocation()) >= 7) {
					this.cancel();
				}
				Particle.DustOptions dustOptions = new Particle.DustOptions(Color.PURPLE,1.0F);
				endermite.getWorld().spawnParticle(Particle.REDSTONE, pLoc,10,0.0,0.0,0.0,dustOptions);

				pLoc.subtract(pLoc);
			}
		}.runTaskTimer(plugin, 0, 1);
	}
}
