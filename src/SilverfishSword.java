import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class SilverfishSword implements Listener{

	private Main plugin;
	public SilverfishSword(Main plugin) {
		this.plugin=plugin;
	}

	Items item = new Items();
	ArrayList<FallingBlock> silverfishFallingBlocks = new ArrayList<>();
	ArrayList<Location> silverfishBlocks = new ArrayList<>();
	ArrayList<Entity> silverfishEntities = new ArrayList<>();
	@EventHandler
	public void onBreak(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player) {
			Player p = (Player)event.getDamager();
			if(event.getEntity() instanceof LivingEntity) {
				if(plugin.hasItem(p, "main", item.silverfishSword())) {
					event.getEntity().remove();
					FallingBlock fb = (FallingBlock)p.getWorld().spawnFallingBlock(plugin.addToLoc(event.getEntity().getLocation(), 0, 1, 0), Material.STONE_BRICKS.createBlockData());
					silverfishFallingBlocks.add(fb);
				}
			}
		}
	}
	@EventHandler
	public void onHit(EntityChangeBlockEvent event) {
		if(silverfishFallingBlocks.contains(event.getEntity())) {
			silverfishBlocks.add(event.getBlock().getLocation());
		}
	}
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if(silverfishBlocks.contains(event.getBlock().getLocation())) {
			silverfishBlocks.remove(event.getBlock().getLocation());
			event.getBlock().getWorld().spawnEntity(event.getBlock().getLocation(), EntityType.SILVERFISH);
		}
	}
	@EventHandler
	public void onDeath(EntityDeathEvent event) {
		if(silverfishEntities.contains(event.getEntity())) {
			for(ItemStack item : event.getDrops()) {
				item.setType(Material.AIR);
			}
		}
	}
}
