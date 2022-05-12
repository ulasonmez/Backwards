import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Chest;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootTables;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class MineshaftDrill implements Listener{

	private Main plugin;
	public MineshaftDrill(Main plugin) {
		this.plugin=plugin;
	}
	Items item = new Items();
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))return;
		if(!event.getHand().equals(EquipmentSlot.HAND))return;
		if(!plugin.hasItem(p, "main", item.mineshaftDrill()))return;
		Location clickedLoc = event.getClickedBlock().getRelative(event.getBlockFace()).getLocation();
		Location drillLocation = new Location(p.getWorld(),clickedLoc.getX()+0.5,clickedLoc.getY()
				,clickedLoc.getZ()+0.5,getYawForDrill(p.getLocation().getYaw()) ,0);
		ArmorStand stand = spawnDrill(drillLocation);
		moveDrill(stand);
	}
	public ArmorStand spawnDrill(Location loc) {
		ArmorStand stand = (ArmorStand)loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		stand.getEquipment().setHelmet(item.mineshaftDrill());
		stand.setInvulnerable(true);
		stand.setGravity(false);
		stand.setInvisible(true);
		return stand;
	}
	public void moveDrill(ArmorStand stand) {
		new BukkitRunnable() {
			Vector dir = stand.getLocation().getDirection().normalize();
			Location standLoc = stand.getLocation();
			double t = 1;
			int timer = 0;
			@Override
			public void run() {
				t+=0.35;timer+=1;
				if(timer >= 20 * 7) {
					setChest(stand.getLocation());
					stand.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, stand.getLocation(),5);
					stand.remove();
					this.cancel();
				}
				double a = dir.getX()*t;
				double b = dir.getY()*t;
				double c = dir.getZ()*t;
				standLoc.add(a,b,c);

				stand.teleport(standLoc);
				for(int x = -1; x <= 1; x++) {
					for(int z = -1; z <= 1; z++) {
						for(int y = -1; y <= 2; y++) {
							if(y>=0) {
								if(!plugin.addToLoc(stand.getLocation(), x, y, z).getBlock().getType().equals(Material.OAK_PLANKS) &&
										!plugin.addToLoc(stand.getLocation(), x, y, z).getBlock().getType().equals(Material.OAK_FENCE)&&
										!plugin.addToLoc(stand.getLocation(), x, y, z).getBlock().getType().equals(Material.CHEST)) {
									plugin.addToLoc(stand.getLocation(), x, y, z).getBlock().breakNaturally(new ItemStack(Material.NETHERITE_PICKAXE));
								}
							}
							else {
								if(plugin.addToLoc(standLoc, x, y, z).getBlock().getType().equals(Material.AIR) ||
										plugin.addToLoc(standLoc, x, y, z).getBlock().getType().equals(Material.CAVE_AIR)) {
									plugin.addToLoc(standLoc, x, y, z).getBlock().setType(Material.OAK_PLANKS);
								}
							}
						}
					}
				}
				if(timer % (20*2) == 0) {
					placeMineShaft(stand.getLocation());
				}
				stand.getWorld().playSound(stand.getLocation(), Sound.BLOCK_NETHERITE_BLOCK_BREAK, Integer.MAX_VALUE, 1);
				standLoc.subtract(a,b,c);
			}
		}.runTaskTimer(plugin, 0, 1);
	}
	Random rand = new Random();
	public void setChest(Location loc) {
		loc.getBlock().setType(Material.CHEST);
		List<NamespacedKey> loots = List.of(LootTables.SHIPWRECK_SUPPLY.getKey(),LootTables.SHIPWRECK_TREASURE.getKey(),
				LootTables.BURIED_TREASURE.getKey(),LootTables.BASTION_TREASURE.getKey(),
				LootTables.END_CITY_TREASURE.getKey());
		List<ItemStack> specialItems = List.of(item.wardenEar(),item.villagerNose(),item.diamondIngot());
		new BukkitRunnable() {
			@Override
			public void run() {
				Chest chest = (Chest)loc.getBlock().getState();
				chest.setLootTable(Bukkit.getLootTable(loots.get(rand.nextInt(loots.size()))));
				chest.update();
				ItemStack randItem = specialItems.get(rand.nextInt(specialItems.size()));
				for(int i= 0; i<=rand.nextInt(3)+1;i++) {
					chest.getBlockInventory().setItem(rand.nextInt(27), randItem);
				}
			}
		}.runTaskLater(plugin, 2);
	}


	public void placeMineShaft(Location loc) {
		if(loc.getBlock().getLightLevel()<=10) {
			plugin.addToLoc(loc, 0, -1, 0).getBlock().setType(Material.GLOWSTONE);
		}
		final float newZ = (float)(loc.getZ() + ( 2 * Math.sin(Math.toRadians(loc.getYaw() + 90 * 0 ))));
		final float newX = (float)(loc.getX() + ( 2 * Math.cos(Math.toRadians(loc.getYaw() + 90 * 0))));
		//left
		Location loc1 = new Location(loc.getWorld(),newX,loc.getY(),newZ);

		final float newZ1 = (float)(loc.getZ() + ( -2 * Math.sin(Math.toRadians(loc.getYaw() + 90 * 0 ))));
		final float newX1 = (float)(loc.getX() + ( -2 * Math.cos(Math.toRadians(loc.getYaw() + 90 * 0))));
		//right
		Location loc2 = new Location(loc.getWorld(),newX1,loc.getY(),newZ1);

		loc1.getBlock().setType(Material.OAK_FENCE);
		plugin.addToLoc(loc1, 0, 1, 0).getBlock().setType(Material.OAK_FENCE);
		plugin.addToLoc(loc1, 0, -1, 0).getBlock().setType(Material.OAK_PLANKS);
		loc2.getBlock().setType(Material.OAK_FENCE);
		plugin.addToLoc(loc2, 0, 1, 0).getBlock().setType(Material.OAK_FENCE);
		plugin.addToLoc(loc2, 0, -1, 0).getBlock().setType(Material.OAK_PLANKS);

		plugin.addToLoc(loc2, 0, 2, 0).getBlock().setType(Material.OAK_PLANKS);
		plugin.addToLoc(loc1, 0, 2, 0).getBlock().setType(Material.OAK_PLANKS);


		final float newZ2 = (float)(loc.getZ() + ( 1 * Math.sin(Math.toRadians(loc.getYaw() + 90 * 0 ))));
		final float newX2 = (float)(loc.getX() + ( 1 * Math.cos(Math.toRadians(loc.getYaw() + 90 * 0))));
		//left
		Location loc3 = new Location(loc.getWorld(),newX2,loc.getY(),newZ2);

		final float newZ3 = (float)(loc.getZ() + ( -1 * Math.sin(Math.toRadians(loc.getYaw() + 90 * 0 ))));
		final float newX3 = (float)(loc.getX() + ( -1 * Math.cos(Math.toRadians(loc.getYaw() + 90 * 0))));
		//left
		Location loc4 = new Location(loc.getWorld(),newX3,loc.getY(),newZ3);
		plugin.addToLoc(loc3, 0, 3, 0).getBlock().setType(Material.OAK_PLANKS);
		plugin.addToLoc(loc, 0, 3, 0).getBlock().setType(Material.OAK_PLANKS);
		plugin.addToLoc(loc4, 0, 3, 0).getBlock().setType(Material.OAK_PLANKS);

	}
	public float getYawForDrill(float yaw) {
		if(yaw <= -90) {
			if(yaw <= -135) {
				return -180;
			}else {
				return -90;
			}
		}
		else if(yaw <= 0) {
			if(yaw <= -45) {
				return -90;
			}else {
				return 0;
			}
		}
		else if(yaw <= 90) {
			if(yaw <= 44) {
				return 0;
			}else {
				return 90;
			}
		}
		else if(yaw <= 180) {
			if(yaw <= 134) {
				return 90;
			}else {
				return 180;
			}
		}
		return 0;
	}
}
