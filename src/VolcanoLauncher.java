import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class VolcanoLauncher implements Listener{

	private Main plugin;
	public VolcanoLauncher(Main plugin) {
		this.plugin=plugin;
	}
	Items item = new Items();
	@EventHandler
	public void onPlace(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))return;
		if(!event.getHand().equals(EquipmentSlot.HAND))return;
		if(plugin.hasItem(p, "main", item.volcanoLauncher())) {
			Location blockLocation = event.getClickedBlock().getRelative(event.getBlockFace()).getLocation();
			Location volcanoLocation = new Location(p.getLocation().getWorld(),
					blockLocation.getX(),blockLocation.getY(),blockLocation.getZ(),p.getLocation().getYaw(),blockLocation.getPitch());
			ArmorStand volcanoLauncher = spawnStand(volcanoLocation, item.volcanoLauncher());
			new BukkitRunnable() {
				@Override
				public void run() {
					shootMiniatureVolcano(volcanoLauncher.getLocation());

				}
			}.runTaskLater(plugin, 20);
		}
		if(plugin.hasItem(p, "main", item.basaltPortal())) {
			Location blockLocation = event.getClickedBlock().getRelative(event.getBlockFace()).getLocation();
			spawnStand(blockLocation, item.basaltPortal());
			p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);;
		}
	}

	public ArmorStand spawnStand(Location loc, ItemStack helmet) {
		ArmorStand stand = (ArmorStand)loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		stand.setInvulnerable(true);
		stand.getEquipment().setHelmet(helmet);
		stand.setInvisible(true);
		return stand;
	}


	public ArmorStand shootMiniatureVolcano(Location loc) {
		ArmorStand stand = (ArmorStand)loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		stand.setInvulnerable(true);
		stand.getEquipment().setHelmet(item.miniatureVolcano());
		stand.setInvisible(true);
		stand.setVelocity(loc.getDirection().multiply(2).setY(1));
		new BukkitRunnable() {
			int time = 0;
			@Override
			public void run() {
				time+=1;
				loc.getWorld().spawnParticle(Particle.FLAME, plugin.addToLoc(stand.getLocation(), 0, 0.5, 0) , 0, 0.0, 0.0, 0.0);
				if(time >= 20 * 6) {
					blowMiniatureVolcano(stand);
					this.cancel();
				}
			}
		}.runTaskTimer(plugin, 0, 1);
		return stand;
	}
	Random rand = new Random();
	public void blowMiniatureVolcano(ArmorStand stand) {
		stand.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, stand.getLocation() , 5);
		for(Entity ent : stand.getNearbyEntities(1, 1, 1)) {
			if(ent instanceof ArmorStand) {
				if(plugin.hasHelmet((ArmorStand)ent, item.basaltPortal())) {
					stand.getWorld().playSound(ent.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, Integer.MAX_VALUE, 20);
					((ArmorStand) ent).getEquipment().setHelmet(item.basaltPortalOpen());
					new BukkitRunnable() {
						@Override
						public void run() {
							for(Player p : Bukkit.getOnlinePlayers()) {
								if(!p.getWorld().equals(stand.getWorld())) {
									this.cancel();
								}
							}
							if(ent.isDead()) {
								this.cancel();
							}
							stand.getWorld().spawnParticle(Particle.FLAME, plugin.addToLoc(ent.getLocation(), rand.nextDouble()-0.4, rand.nextDouble()+0.4, rand.nextDouble()-0.4) , 0, 0.0, 0.0, 0.0);
							stand.getWorld().spawnParticle(Particle.FLAME, plugin.addToLoc(ent.getLocation(), rand.nextDouble()-0.4, rand.nextDouble()+0.4, rand.nextDouble()-0.4) , 0, 0.0, 0.0, 0.0);
							stand.getWorld().spawnParticle(Particle.ASH, plugin.addToLoc(ent.getLocation(), rand.nextDouble()-0.4, rand.nextDouble()+0.4, rand.nextDouble()-0.4) , 0, 0.0, 0.0, 0.0);
						}
					}.runTaskTimer(plugin, 0, 1);
				}
			}
		}
		for(Entity ent : stand.getNearbyEntities(2, 2, 2)) {
			if(!(ent instanceof ArmorStand)) {
				if(ent instanceof LivingEntity) {
					if(ent.getType()!=EntityType.PLAYER) {
						ent.getWorld().playSound(ent.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, Integer.MAX_VALUE, 20);
						((LivingEntity)ent).damage(20);
					}
				}
			}
		}
		stand.remove();
	}
	
	
	public void spawnOverWorldPortal() {
		ArmorStand stand = spawnStand(plugin.overWorldSpawnLocation, item.basaltPortalOpen());
		stand.getEquipment().setHelmet(item.basaltPortalOpen());
		new BukkitRunnable() {
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(!p.getWorld().equals(stand.getWorld())) {
						this.cancel();
					}
				}
				if(stand.isDead()) {
					this.cancel();
				}
				stand.getWorld().spawnParticle(Particle.FLAME, plugin.addToLoc(stand.getLocation(), rand.nextDouble()-0.4, rand.nextDouble()+0.4, rand.nextDouble()-0.4) , 0, 0.0, 0.0, 0.0);
				stand.getWorld().spawnParticle(Particle.FLAME, plugin.addToLoc(stand.getLocation(), rand.nextDouble()-0.4, rand.nextDouble()+0.4, rand.nextDouble()-0.4) , 0, 0.0, 0.0, 0.0);
				stand.getWorld().spawnParticle(Particle.ASH, plugin.addToLoc(stand.getLocation(), rand.nextDouble()-0.4, rand.nextDouble()+0.4, rand.nextDouble()-0.4) , 0, 0.0, 0.0, 0.0);
				
			}
		}.runTaskTimer(plugin, 0, 3);
	}
	
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player p = event.getPlayer();
		if(p.getWorld().equals(Bukkit.getWorlds().get(1))) {
			for(Entity ent : p.getNearbyEntities(0.2, 0.2, 0.2)) {
				if(ent instanceof ArmorStand) {
					ArmorStand stand = (ArmorStand)ent;
					if(plugin.hasHelmet(stand, item.basaltPortalOpen())) {
						p.teleport(plugin.overWorldSpawnLocation);
						spawnOverWorldPortal();
					}
				}
			}
		}
	}
}
