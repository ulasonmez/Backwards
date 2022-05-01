import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ShulkerItems implements Listener{

	private Main plugin;
	public ShulkerItems(Main plugin) {
		this.plugin=plugin;
	}

	Items item = new Items();
	Map<Player, BlockFace> lastFace = new HashMap<>();

	@EventHandler
	public void onBlockClicked(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			lastFace.put(e.getPlayer(), e.getBlockFace());
		}
	}
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Location loc = e.getBlock().getLocation();
		Player p = e.getPlayer();
		if(plugin.hasItem(p, "main", item.shulkerPickaxe())) {
			BlockFace face = lastFace.get(e.getPlayer());
			int radius = 1,depth=1;
			if (face.equals(BlockFace.NORTH)) {
				for (int x = -radius; x <= radius; x++) {
					for (int y = -radius; y <= radius; y++) {
						for(int z = -0 ; z<=depth;z++) {
							Location loc2 = loc.clone();
							loc2.add(x, y, z);
							p.getWorld().spawnParticle(Particle.CLOUD, loc2,30,0.5,0.5,0.5);
							e.getPlayer().getWorld().getBlockAt(loc2).breakNaturally(new ItemStack(Material.NETHERITE_PICKAXE));
						}
					}
				}
			}
			if(face.equals(BlockFace.SOUTH)) {
				for (int x = -radius; x <= radius; x++) {
					for (int y = -radius; y <= radius; y++) {
						for(int z = 0 ; z>=-depth;z--) {
							Location loc2 = loc.clone();
							loc2.add(x, y, z);
							p.getWorld().spawnParticle(Particle.CLOUD, loc2,30,0.5,0.5,0.5);
							e.getPlayer().getWorld().getBlockAt(loc2).breakNaturally(new ItemStack(Material.NETHERITE_PICKAXE));
						}
					}
				}
			}
			if (face.equals(BlockFace.EAST)) {
				for (int z = -radius; z <= radius; z++) {
					for (int y = -radius; y <= radius; y++) {
						for(int x = 0 ; x>=-depth;x--) {
							Location loc2 = loc.clone();
							loc2.add(x, y, z);
							p.getWorld().spawnParticle(Particle.CLOUD, loc2,30,0.5,0.5,0.5);
							e.getPlayer().getWorld().getBlockAt(loc2).breakNaturally(new ItemStack(Material.NETHERITE_PICKAXE));
						}
					}
				}
			}
			if(face.equals(BlockFace.WEST)) {
				for (int z = -radius; z <= radius; z++) {
					for (int y = -radius; y <= radius; y++) {
						for(int x = -0 ; x<=depth;x++) {
							Location loc2 = loc.clone();
							loc2.add(x, y, z);
							p.getWorld().spawnParticle(Particle.CLOUD, loc2,30,0.5,0.5,0.5);
							e.getPlayer().getWorld().getBlockAt(loc2).breakNaturally(new ItemStack(Material.NETHERITE_PICKAXE));
						}
					}
				}
			}
			if (face.equals(BlockFace.UP)) {
				for (int x = -radius; x <= radius; x++) {
					for (int z = -radius; z <= radius; z++) {
						for(int y = 0; y>=-depth;y--) {
							Location loc2 = loc.clone();
							loc2.add(x, y, z);
							p.getWorld().spawnParticle(Particle.CLOUD, loc2,30,0.5,0.5,0.5);
							e.getPlayer().getWorld().getBlockAt(loc2).breakNaturally(new ItemStack(Material.NETHERITE_PICKAXE));
						}
					}
				}
			}
			if(face.equals(BlockFace.DOWN)) {
				for (int x = -radius; x <= radius; x++) {
					for (int z = -radius; z <= radius; z++) {
						for(int y = -0; y<=depth;y++) {
							Location loc2 = loc.clone();
							loc2.add(x, y, z);
							p.getWorld().spawnParticle(Particle.CLOUD, loc2,30,0.5,0.5,0.5);
							e.getPlayer().getWorld().getBlockAt(loc2).breakNaturally(new ItemStack(Material.NETHERITE_PICKAXE));
						}
					}
				}
			}
		}
	}
}
