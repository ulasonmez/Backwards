import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class ObsidianCrossbow implements Listener{

	private Main plugin;
	public ObsidianCrossbow(Main plugin) {
		this.plugin=plugin;
	}
	Items item = new Items();
	@EventHandler
	public void onHit(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(plugin.hasItem(p, "main", item.obsidianCrossbow())) {
			if(!hasCarrot(p)) {
				event.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void onShoot(EntityShootBowEvent event) {
		if(event.getEntity() instanceof Player) {
			Player p = (Player)event.getEntity();
			if(plugin.hasItem(p, "main", item.obsidianCrossbow())) {
				decreaseCarrot(p);
				event.setCancelled(true);
				Location pLoc = p.getEyeLocation();
				Item thrownEggItem = p.getWorld().dropItem(pLoc, item.enderCarrot());
				thrownEggItem.setVelocity(pLoc.getDirection().multiply(2));
				new BukkitRunnable() {
					int count = 0;
					@Override
					public void run() {
						LivingEntity ent = getNearbyEntites(thrownEggItem.getLocation());
						if(ent!=null) {
							ent.damage(8);
							this.cancel();
						}
						if(thrownEggItem.isOnGround() || (count >= 20 * 10)) {
							thrownEggItem.remove();
							this.cancel();
						}
						count++;
					}
				}.runTaskTimer(plugin, 0, 1);
			}
		}
	}
	public LivingEntity getNearbyEntites(Location loc) {
		for(Entity ent : loc.getWorld().getNearbyEntities(loc,1,1,1)) {
			if(ent instanceof LivingEntity) {
				if(!(ent instanceof Player)) {
					return (LivingEntity)ent;
				}
			}
		}
		return null;
	}

	public void decreaseCarrot(Player p) {
		for(ItemStack item1 : p.getInventory().getContents()) {
			if(item1!=null) {
				if(item1.isSimilar(item.enderCarrot())) {
					item1.setAmount(item1.getAmount()-1);
					break;
				}
			}
		}
		boolean addedArrow = false;
		for(ItemStack item1 : p.getInventory().getContents()) {
			if(item1!=null) {
				if(item1.getType().equals(Material.ARROW)) {
					item1.setAmount(item1.getAmount()+1);
					addedArrow = true;
					break;
				}
			}
		}
		if(!addedArrow) {
			p.getInventory().addItem(new ItemStack(Material.ARROW));
		}
	}
	public boolean hasCarrot(Player p) {
		for(ItemStack item1 : p.getInventory().getContents()) {
			if(item1!=null) {
				if(item1.isSimilar(item.enderCarrot())) {
					return true;
				}
			}
		}
		return false;
	}
}
