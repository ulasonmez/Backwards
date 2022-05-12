import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EnderDragon.Phase;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class PhantomCrystal implements Listener{

	private Main plugin;
	public PhantomCrystal(Main plugin) {
		this.plugin=plugin;
	}

	Items item = new Items();
	int crystalCount = 0;
	ArrayList<EnderCrystal> crystals = new ArrayList<>();
	@EventHandler
	public void onPlayerInteract(final PlayerInteractEvent event) {
		Player p = event.getPlayer();
		//if(!p.getWorld().equals(Bukkit.getWorlds().get(2)))return;
		if (Action.RIGHT_CLICK_BLOCK == event.getAction()) {
			if (Material.BEDROCK == event.getClickedBlock().getType()) {
				if (Material.END_CRYSTAL == event.getMaterial()) {
					new BukkitRunnable() {
						@Override
						public void run() {
							List<Entity> entities = event.getPlayer().getNearbyEntities(4, 4, 4);
							for (Entity entity : entities) {
								if (EntityType.ENDER_CRYSTAL == entity.getType()) {
									EnderCrystal crystal = (EnderCrystal) entity;
									crystals.add(crystal);
									Block belowCrystal = crystal.getLocation().getBlock().getRelative(BlockFace.DOWN);
									if (event.getClickedBlock().equals(belowCrystal)) { 
										if(plugin.hasItem(p, "main", item.phantomCrystal())) {
											crystalCount+=1;
											if(crystalCount == 10) {
												Location dragonSpawnLoc = new Location(Bukkit.getWorlds().get(2),0,70,0);
												EnderDragon dragon = (EnderDragon)p.getWorld().spawnEntity(dragonSpawnLoc,EntityType.ENDER_DRAGON);
												dragon.setPhase(Phase.CIRCLING);
												p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, Integer.MAX_VALUE, 10);
												for(EnderCrystal ec : crystals) {
													new BukkitRunnable() {
														@Override
														public void run() {
															if(!p.getWorld().equals(Bukkit.getWorlds().get(2))) {
																this.cancel();
															}
															ec.setBeamTarget(dragon.getLocation());
														}
													}.runTaskTimer(plugin, 0, 1);
												}
												fillPortal();
											}
										}
										break;
									}
								}
						}
						}
					}.runTask(plugin);
				}
			}
		}
	}
	@EventHandler
	public void onTeleport(PlayerTeleportEvent event) {
		Player p = event.getPlayer();
		if(p.getWorld().equals(Bukkit.getWorlds().get(2))) {
			if(event.getTo().getWorld().equals(Bukkit.getWorlds().get(0))) {
				event.setCancelled(true);
				p.teleport(plugin.netherStrongholdLocation);
				new BukkitRunnable() {
					@Override
					public void run() {
						Bukkit.broadcastMessage(p.getName() + " has made the advancement "+ChatColor.GREEN+"[Back Home... Almost]");
					}
				}.runTaskLater(plugin, 0);
			}
		}
	}
	public void fillPortal() {
		Location loc = new Location(Bukkit.getWorlds().get(2),0,60,0);
		for(int x = -10;x<=10;x++) {
			for(int z = -10;z<=10;z++) {
				if(plugin.addToLoc(loc, x, -1, z).getBlock().getType().equals(Material.BEDROCK)) {
					plugin.addToLoc(loc, x, 0, z).getBlock().setType(Material.END_PORTAL);
				}
			}
		}
	}
}
