import java.util.Random;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class ChorusItems implements Listener{

	private Main plugin;
	public ChorusItems(Main plugin) {
		this.plugin=plugin;
	}
	
	Items item = new Items();
	//breaking the crafting table dropping the chorus table
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Player p = event.getPlayer();
		if(event.getBlock().getType().equals(Material.CRAFTING_TABLE)) {
			event.getBlock().setType(Material.AIR);
			p.getWorld().dropItemNaturally(event.getBlock().getLocation(), item.chorusCraftingTable());
		}
		if(plugin.hasItem(p, "main", item.chorusPickaxe())) {
			p.sendMessage("oldu");
			Location teleportLoc = chorusTeleport(p.getLocation());
			if(teleportLoc != null) {
				p.teleport(teleportLoc);
				Particle.DustOptions dustOptions = new Particle.DustOptions(Color.PURPLE,1.0F);
				p.getWorld().spawnParticle(Particle.REDSTONE, p.getLocation(),70,0.5,1,0.5,dustOptions);
			}
		}
	}
	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player) {
			Player p = (Player)event.getDamager();
			if(plugin.hasItem(p, "main", item.chorusPickaxe())) {
				Location teleportLoc = chorusTeleport(p.getLocation());
				if(teleportLoc != null) {
					p.teleport(teleportLoc);
					Particle.DustOptions dustOptions = new Particle.DustOptions(Color.PURPLE,1.0F);
					p.getWorld().spawnParticle(Particle.REDSTONE, p.getLocation(),70,0.5,1,0.5,dustOptions);
				}
			}
		}
	}
	Random rand = new Random();
	@EventHandler
	public void onDeath(EntityDeathEvent event) {
		if(event.getEntityType().equals(EntityType.ENDERMAN)) {
			for(ItemStack item : event.getDrops()) {
				item.setType(Material.AIR);
			}
			if(rand.nextBoolean()) {
				event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), item.enderCarrot());
			}
		}
	}
	public Location chorusTeleport(Location loc) {
		int x = rand.nextInt(64)-32;
		int z = rand.nextInt(64)-32;
		Location loc1 = null;
		while(true) {
			for(int y = 32; y >= -32; y--) {
				if(plugin.addToLoc(loc, x, y, z).getBlock().getType().isSolid() &&
						!plugin.addToLoc(loc, x, y+1, z).getBlock().getType().isSolid()) {
					loc1 = plugin.addToLoc(loc, x, y+1, z);
					break;
				}
			}
			if(loc1!=null) {
				break;
			}
		}
		return loc1;
	}
}
